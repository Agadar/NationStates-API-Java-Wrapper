package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.RateLimiter;
import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.shard.Shard;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * A query to the NationStates API.
 * 
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <S> the shard type the child class accepts
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class NSQuery<Q extends NSQuery, S extends Shard, R>
{
    /** Base URL to the Nation API. */
    private static final String BASE_URL = "https://www.nationstates.net/cgi-bin/api.cgi?";
    
    /** The user agent with which this library makes requests. */
    private static final String USER_AGENT = "Agadar's Wrapper "
            + "(https://github.com/Agadar/NationStates-API-Java-Wrapper)";
    
    /** The NationStates API version this wrapper uses. */
    private static final int API_VERSION = 8;
    
    /** 
     * The rate limiter for normal API calls. The mandated rate limit is 50
     * requests per 30 seconds. To make sure we're on the safe side, we reduce 
     * this to 45 requests per 30 seconds. To get a spread-like pattern instead
     * of a burst-like pattern, we make this into 9 requests per 6 seconds.
     */ 
    private static final RateLimiter rateLimiter = new RateLimiter(3, 2000);
    
    /** The JAXBContext for this API. */
    private static final JAXBContext jc;
    
    /** If we're debugging, we do some prints here and there. */
    private static final boolean DEBUG = true;
    
    /** Static 'constructor'. Sets up the JAXBContext and verifies the version. */
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
        
        // Verify the NationStates API version this library intents to consume.
        //verifyVersion();
    }
    
    /**
     * Starts building a nation query.
     * 
     * @param nationName name of the nation to query
     * @return a new nation query
     */
    public static NationQuery nation(String nationName)
    {
        return new NationQuery(nationName);
    }
    
    /**
     * Starts building a region query.
     * 
     * @param regionName name of the region to query
     * @return a new region query
     */
    public static RegionQuery region(String regionName)
    {
        return new RegionQuery(regionName);
    }
    
    /**
     * Starts building a world query.
     * 
     * @return a new world query
     */
//    public static WorldQuery world()
//    {
//        return new WorldQuery();
//    }
    
    /**
     * Starts building a World Assembly query.
     * 
     * @return a new World Assembly query
     */
//    public static WAQuery wa()
//    {
//        return new WAQuery();
//    }
    
    /** 
     * The resource value, e.g. the nation's or region's name. 
     * Set by the constructor.
     */
    protected final String resourceValue;
    
    /** See shards(...). */
    protected S[] shards;
    
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
     * Sets the shards.
     * 
     * @param shards the shards to set
     * @return this
     */
    public final Q shards(S... shards)
    {        
        this.shards = shards;
        return (Q) this;
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
        
        // Print generated url for testing purposes
        if (DEBUG)
            System.out.println("------------ Generated URL ------------" + 
                System.lineSeparator() + url);
        
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
            
            // Retrieve XML string from stream
            Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String xml = s.hasNext() ? s.next() : "";
            
            // Print retrieved xml if we're debugging
            if (DEBUG)
                System.out.println("------------ Retrieved XML ------------" + 
                    System.lineSeparator() + xml);
            
            
            // Discover our return type.
            Class classOfThis = ((Class) ((ParameterizedType) this.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[2]);

            if (classOfThis != null && !classOfThis.equals(String.class))
            {
                // Read and convert the response body.
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                StreamSource xmlstream = new StreamSource(new StringReader(xml));
                JAXBElement<R> je1 = unmarshaller.unmarshal(xmlstream, classOfThis);
                R converted = je1.getValue();
                
                // Close connection and return.
                conn.disconnect();
                return converted;
            }
            else
            {
                // Return retrieved string.
                conn.disconnect();
                return (R) xml;
            }
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException("Request to NationStates API failed!", ex);
        }
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException("Failed to convert retrieved XML!", ex);
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
        String url = BASE_URL + "v=" + API_VERSION;
        
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
        
        // If there are shards present, then append them to url
        if (shards != null && shards.length > 0)
        {
            url += "&q=" + shards[0].shardName();

            for (int i = 1; i < shards.length; i++)
            {
                url += "+" + shards[i].shardName();
            }
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
}
