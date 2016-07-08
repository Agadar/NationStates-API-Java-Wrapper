package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NSAPI;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.RateLimiter;
import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * Top parent class for all Queries to the NationStates API.
 * 
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class NSQuery<Q extends NSQuery, R>
{
    /** Base URL to the Nation API. */
    private static final String BASE_URL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    
    /** The user agent with which this library makes requests. */
    private static final String USER_AGENT = "Agadar's Wrapper "
            + "(https://github.com/Agadar/NationStates-API-Java-Wrapper)";
    
    /** 
     * The rate limiter for normal API calls. The mandated rate limit is 50
     * requests per 30 seconds. To make sure we're on the safe side, we reduce 
     * this to 45 requests per 30 seconds. To get a spread-like pattern instead
     * of a burst-like pattern, we make this into 9 requests per 6 seconds.
     */ 
    private static final RateLimiter rateLimiter = new RateLimiter(3, 2000);
    
    /** The JAXBContext for this API. */
    private static final JAXBContext jc;
    
    /** The logger for this object. */
    private static final Logger logger = Logger.getLogger(NSQuery.class.getName());
    
    /** Static 'constructor' that sets up the JAXBContext. */
    static
    {
        // Try setting up the JAXBContext.
        try
        {
            jc = JAXBContext.newInstance(Nation.class, Region.class, World.class,
                WorldAssembly.class);
        }
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException("Failed to initialize NationStatesAPI instance!", ex);
        }
    }
    
    /** 
     * The resource value, e.g. the nation's or region's name. 
     * Set by the constructor.
     */
    protected String resourceValue;
    
    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     * 
     * @param resourceValue the resource value
     */
    protected NSQuery(String resourceValue)
    {
        this.resourceValue = resourceValue;
    }
    
    /**
     * Executes this query, returning any result.
     * 
     * @return the result
     */
    public final R execute()
    {
        validateQueryParameters();
        String url = buildURL();
        logger.log(Level.INFO, "Generated URL: {0}", url);
        return makeRequest(url);
    }
    
    /**
     * Makes a GET request to the NationStates API.
     *
     * @param urlStr the url to make the request to
     * @return the retrieved data
     */
    private R makeRequest(String urlStr)
    {
        // Enforce rate limit on API calls.
        rateLimiter.Await();
        
        try
        {
            // Prepare request, then make it
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = conn.getResponseCode();
            
            // If the resource was not found, return null.
            if (responseCode == 404)
            {
                return null;
            }
            // If we messed up and violated the rate limit, throw an exception.
            else if (responseCode == 429)
            {
                throw new NationStatesAPIException("Too many requests: rate limit"
                        + " was violated! We're forced to wait up to 15 "
                        + "minutes before we can send a request again!");
            }
            // If something went wrong while processing the request, throw error
            else if (responseCode != 200)
            {
                throw new NationStatesAPIException("Request to NationStates API "
                    + "failed! HTTP message: " + conn.getResponseMessage());
            }
            
            // Retrieve string from stream
            Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String response = s.hasNext() ? s.next().trim() : "";
            
            // Close connection and return translated response.
            conn.disconnect();
            return translateResponse(response);
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException("Request to NationStates API failed!", ex);
        }
    }
    
    /**
     * Generates a String URL based on the supplied values. Subclasses
     * might want to override this method to build the String URL further to
     * accommodate for additional modes/options/etc.
     *
     * @return the generated String URL
     */
    protected String buildURL()
    {
        // Start out by concatenating base url and API version number
        String url = BASE_URL + "v=" + NSAPI.API_VERSION;
        
        // If we're not using the top resource, then append resource and resourceValue
        if (!resourceString().isEmpty())
        {
            // Ensure resourceValue is not null or empty           
            if (resourceValue == null || resourceValue.isEmpty())
            {
                throw new IllegalArgumentException("'resourceValue' may not be "
                        + "null or empty when not using the top level resource!");
            }
            
            // Append resource and resourceValue
            url += "&" + resourceString() + "=" + resourceValue.replace(' ', '_');
        }
        
        // Finally, return the generated url
        return url;
    }
    
    /**
     * Validates the query parameters before executing the query.
     * If the query parameters are invalid, an exception should be thrown.
     * 
     * @throws NationStatesAPIException if the query parameters are invalid
     */
    protected abstract void validateQueryParameters() throws NationStatesAPIException;
    
    /**
     * Gives the resource string of this Query, e.g. 'nation', 'region', etc.
     * 
     * @return the resource string of this Query
     */
    protected abstract String resourceString();
    
    /**
     * Translates the string response to the object this Query wishes to return
     * via its execute() function. The standard way to translate is via JAXB,
     * which assumes the string is in a valid XML-format. Child classes might
     * want to override this function if they wish to return primitives or
     * something else.
     * 
     * @param response the response to translate
     * @return the translated response
     */
    protected R translateResponse(String response)
    {
        // Discover our return type.
        Class classOfThis = ((Class) ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[1]);
        
        // Read and convert the response body.
        try 
        {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource xmlstream = new StreamSource(new StringReader(response));
            JAXBElement<R> je1 = unmarshaller.unmarshal(xmlstream, classOfThis);
            return je1.getValue();
        } 
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException("Failed to convert retrieved XML!", ex);
        }
    }
}
