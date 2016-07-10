package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.NSAPI;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.DailyDumpNations;
import com.github.agadar.nsapi.domain.DailyDumpRegions;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * Top parent class for all Queries to NationStates in general.
 * 
 * @author Agadar <https://github.com/Agadar/>
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class AbstractQuery<Q extends AbstractQuery, R>
{
    /** The logger for this object. */
    protected static final Logger logger = Logger.getLogger(AbstractQuery.class.getName());
    
    /** Base URL to NationStates. */
    private static final String BASE_URL = "https://www.nationstates.net/";
    
    /** The JAXBContext for this API. */
    private static final JAXBContext jc;
    
    /** Static 'constructor' that sets up the JAXBContext. */
    static
    {
        // Try setting up the JAXBContext.
        try
        {
            jc = JAXBContext.newInstance(DailyDumpNations.class, 
                DailyDumpRegions.class, World.class, WorldAssembly.class);
        }
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException(ex);
        }
    }
    
    /**
     * Validates the query parameters before executing the query. If the query
     * parameters are invalid, an exception is thrown. Child classes will want
     * to override this class (making sure to still call
     * super.validateQueryParameters()) if they have additional parameters to 
     * validate.
     */
    protected void validateQueryParameters()
    {
        String userAgent = NSAPI.getUserAgent();
        
        if (userAgent == null || userAgent.isEmpty())
        {
            throw new NationStatesAPIException("No User Agent set!");
        }
    }
    
    /**
     * Generates a String URL based on the supplied values. Subclasses
     * might want to override this method to build the String URL further to
     * accommodate for additional shards/modes/options/etc.
     *
     * @return the generated String URL
     */
    protected String buildURL()
    {
        return BASE_URL;
    }
    
    /**
     * Executes this query, returning any result. Child classes may want to
     * override this if they want to use rate limiters and such.
     * 
     * @return the result
     */
    public R execute()
    {
        validateQueryParameters();
        return makeRequest(buildURL().replace(' ', '_'));
    }
    
    /**
     * Makes a GET request to the NationStates API. Throws exceptions if the call
     * failed. If the requested nation/region/etc. simply wasn't found, it returns null.
     *
     * @param urlStr the url to make the request to
     * @return the retrieved data, or null if the resource wasn't found
     */
    protected final R makeRequest(String urlStr)
    {     
        // Prepare request, then make it
        HttpURLConnection conn = null;
        try
        {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", NSAPI.getUserAgent());
            int responseCode = conn.getResponseCode();
            String response = String.format("NationStates API returned: '%s' from URL: %s", 
                responseCode + " " + conn.getResponseMessage(), urlStr);
            
            // Depending on whether or not an error was returned, either throw
            // it or continue as planned.
            InputStream stream = conn.getErrorStream();
            if (stream == null) 
            {
                logger.log(Level.INFO, response);
                return translateResponse(conn.getInputStream());
            }
            else
            {
                // If the resource simply wasn't found, just return null.
                if (responseCode == 404)
                {
                    logger.log(Level.WARNING, response);
                    return null;
                }
                
                // Else, something worse is going on. Throw an exception.
                throw new NationStatesAPIException(response);
            }
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException(ex);
        }
        finally
        {
            // Always close the HttpURLConnection
            if (conn != null) 
            {
                conn.disconnect();
            }
        }
    }
    
    /**
     * Translates the stream response to the object this Query wishes to return
     * via its execute() function. The standard way to translate is via JAXB,
     * which assumes the stream is in a valid XML-format. Child classes might
     * want to override this function if they wish to return primitives or
     * something else.
     * 
     * @param response the response to translate
     * @return the translated response
     */
    protected R translateResponse(InputStream response)
    {
        // Discover our return type.
        Class classOfThis = ((Class) ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[1]);
        
        // Read and convert the response body.
        return (R) xmlToObject(response, classOfThis);
    }
    
    /**
     * Uses JAXB to parse the supplied XML stream to an instance of the
     * specified type.
     * 
     * @param <T> the type to parse to
     * @param xml the XML stream
     * @param toType the type to parse to
     * @return instance of the specified type
     */
    protected final static <T> T xmlToObject(InputStream xml, Class<T> toType)
    {
        try 
        {
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource xmlstream = new StreamSource(xml);
            JAXBElement<T> je1 = unmarshaller.unmarshal(xmlstream, toType);
            return je1.getValue();
        } 
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException("Failed to parse XML!", ex);
        }
    }
}
