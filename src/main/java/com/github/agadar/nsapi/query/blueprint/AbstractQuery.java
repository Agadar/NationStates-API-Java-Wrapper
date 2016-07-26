package com.github.agadar.nsapi.query.blueprint;

import com.github.agadar.nsapi.NSAPI;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.XmlConverter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Top parent class for all Queries to NationStates in general.
 * 
 * @author Agadar (https://github.com/Agadar/)
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
    
    /** The return type of this Query's execute()-method. */
    private final Class<R> returnType;
    
    /**
     * Constructor, setting the returnType.
     */
    protected AbstractQuery()
    {
        returnType = ((Class) ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[1]);
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
     * Executes this query, returning any result.
     * 
     * @return the result
     */
    public final R execute()
    {
        return execute(returnType);
    }
    
    /**
     * Executes this query, returning any result. Child classes may want to
     * override this if they want to use rate limiters and such.
     * 
     * @param <T> the type to return
     * @param type the type to return
     * @return the result, of the given type
     */
    public <T extends R> T execute(Class<T> type)
    {
        validateQueryParameters();
        return makeRequest(buildURL().replace(' ', '_'), type);
    }
    
    /**
     * Makes a GET request to the NationStates API. Throws exceptions if the call
     * failed. If the requested nation/region/etc. simply wasn't found, it returns null.
     *
     * @param <T> type to parse to
     * @param urlStr the url to make the request to
     * @param type type to parse to
     * @return the retrieved data, or null if the resource wasn't found
     */
    protected final <T extends R> T makeRequest(String urlStr, Class<T> type)
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
                stream = conn.getInputStream();
                T result = translateResponse(stream, type);
                closeInputStreamQuietly(stream);
                return result;
            }
            else
            {
                logger.log(Level.WARNING, response);
                closeInputStreamQuietly(stream);
                
                // If the resource simply wasn't found, just return null.
                if (responseCode == 404)
                {
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
     * @param <T> type to parse to
     * @param response the response to translate
     * @param type type to parse to
     * @return the translated response
     */
    protected <T extends R> T translateResponse(InputStream response, Class<T> type)
    {
        // Read and convert the response body.
        return XmlConverter.xmlToObject(response, type);
    }
    
    /**
     * Closes the given InputStream. If an exception occurs, the exception is
     * logged, not thrown or returned. Use this if you don't particularly care
     * about catching exceptions thrown by closing InputStreams.
     * 
     * @param stream the InputStream to close
     */
    protected final static void closeInputStreamQuietly(InputStream stream)
    {
        try
        {
            stream.close();
        }
        catch (IOException ex)
        {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
