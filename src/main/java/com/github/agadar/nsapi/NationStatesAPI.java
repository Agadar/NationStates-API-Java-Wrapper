package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.domain.region.Region;
import com.github.agadar.nsapi.domain.wa.WorldAssembly;
import com.github.agadar.nsapi.domain.world.World;
import com.github.agadar.nsapi.enums.*;
import com.github.agadar.nsapi.enums.shard.NationShard;
import com.github.agadar.nsapi.enums.shard.RegionShard;
import com.github.agadar.nsapi.enums.shard.WorldAssemblyShard;
import com.github.agadar.nsapi.enums.shard.WorldShard;
import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * The Java wrapper for the NationStates API.
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public class NationStatesAPI
{
    /** Available resources for the NS api */
    private enum NSResource
    {
        Nation("nation"),
        Region("region"),
        WorldAssembly("wa"),
        World(""),
        Version("a");
        
        /** The underlying resource string */
        private final String resourceString;

        /**
         * Instantiate a new entry with the given resource string.
         * 
         * @param shardName the name of the resource string
         */
        private NSResource(String resourceString) 
        {
            this.resourceString = resourceString;
        }

        /**
         * Return the resource string
         * 
         * @return the resource string
         */
        public String getResourceString() 
        {
            return resourceString;
        }
    }
    
    /** Base URL to the Nation API. */
    private static final String BASE_URL = "http://www.nationstates.net/cgi-bin/api.cgi?";
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
    private final JAXBContext jc;
    
    /** Constructor, sets up the JAXBContext. */
    public NationStatesAPI()
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
        verifyVersion();
    }

    /**
     * Makes a Nation request.
     *
     * @param nationName the name of the nation.
     * @param shards     the shards to return. If no shards are supplied, then a
     *                   compendium of the most commonly sought shards are
     *                   returned.
     * @return the data of the retrieved nation, or null if it wasn't found
     */
    public Nation nation(String nationName, NationShard... shards)
    {
        String url = buildURL(NSResource.Nation, nationName, shards);
        return makeRequest(url, Nation.class);
    }

    /**
     * Makes a Region request.
     *
     * @param regionName the name of the region.
     * @param shards     the shards to return. If no shards are supplied, then a
     *                   compendium of the most commonly sought shards are
     *                   returned.
     * @return the data of the retrieved region, or null if it wasn't found
     */
    public Region region(String regionName, RegionShard... shards)
    {       
        String url = buildURL(NSResource.Region, regionName, shards);
        return makeRequest(url, Region.class);
    }

    /**
     * Makes a World request.
     *
     * @param shards the shards to return. At least one shard must be supplied.
     * @return the requested world data
     */
    public World world(WorldShard... shards)
    {
        // Check whether at least 1 shard is supplied
        if (shards == null || shards.length == 0)
        {
            throw new IllegalArgumentException("'shards' may not be empty!");
        }
        String url = buildURL(NSResource.World, null, shards);
        return makeRequest(url, World.class);
    }

    /**
     * Makes a World Assembly request.
     *
     * @param council the council to request data from.
     * @param shards  the shards to return.
     * @return the requested WA data
     */
    public WorldAssembly worldAssembly(Council council, WorldAssemblyShard... shards)
    {
        // Check whether council is not null
        if (council == null)
        {
            throw new IllegalArgumentException("'council' may not be null!");
        }

        String url = buildURL(NSResource.WorldAssembly, 
                              String.valueOf(council.getCouncilNumber()), shards);
        return makeRequest(url, WorldAssembly.class);
    }
    
    /**
     * Verifies whether the latest live NationStates API version equals the
     * version this library intents to consume. The possible outcomes are:
     * 
     * 1. The versions are the same: everything is fine;
     * 2. The live version is one count higher: a warning is printed, but
     * otherwise everything is fine;
     * 3. The live version is two or more counts higher, or less: this library
     * will likely not work properly, and thus an exception is thrown.
     */
    private void verifyVersion()
    {
        // Build url and make call.
        String url = buildURL(NSResource.Version, "version");
        String liveVersionStr = makeRequest(url).trim();
        int liveVersion = Integer.valueOf(liveVersionStr);
        
        switch (liveVersion)
        {
            case API_VERSION:
                break;
            case API_VERSION + 1:
                Logger.getLogger(NationStatesAPI.class).warning("This library "
                    + "wants to consume NationStates API version '" + API_VERSION 
                    + "' but the latest live version is '" + liveVersionStr + "'. "
                    + "This library will work, but it is advised to update it.");
                break;
            default:
                throw new NationStatesAPIException("This library wants to consume "
                    + "NationStates API version '" + API_VERSION + "' but this"
                    + " version is no longer live. Please update this library.");
        }
    }
    
    /**
     * Makes a GET request to the NationStates API.
     *
     * @param urlStr the url to make the request to
     * @return the retrieved data
     */
    private String makeRequest(String urlStr)
    {
        return makeRequest(urlStr, null);
    }

    /**
     * Makes a GET request to the NationStates API.
     *
     * @param urlStr the url to make the request to
     * @param responseType the type of the returned data. If null, this method returns a String.
     * @return the retrieved data
     */
    private <T> T makeRequest(String urlStr, Class<T> responseType)
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

            if (responseType != null && !responseType.equals(String.class))
            {
                // Read and convert the response body.
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                StreamSource xml = new StreamSource(conn.getInputStream());
                JAXBElement<T> je1 = unmarshaller.unmarshal(xml, responseType);
                T converted = je1.getValue();
                
                // Close connection and return.
                conn.disconnect();
                return converted;
            }
            else
            {
                // Read input stream.
                Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                
                // Return retrieved string.
                conn.disconnect();
                return (T) result;
            }
        }
        catch (IOException ex)
        {
            throw new NationStatesAPIException("Request to NationStates API failed!", ex);
        }
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException("Failed to convert retrieved XML to type " 
                                               + responseType.getSimpleName(), ex);
        }
    }

    /**
     * Generates a String URL based on the supplied parameters.
     *
     * @param resource the resource to consume
     * @param resourceValue the id value for the resource (nation name, region name...)
     * @param shards the shards to consume
     * @return the generated String URL
     */
    private static String buildURL(NSResource resource, String resourceValue,
            Enum... shards)
    {
        // Ensure resource is not null
        if (resource == null)
        {
            throw new IllegalArgumentException("'resource' may not be null or empty!");
        }
        
        // Start out by concatenating base url and API version number
        String url = BASE_URL + "v=" + API_VERSION;
        
        // If we're not using the top resource, then append resource and resourceValue
        if (!resource.getResourceString().isEmpty())
        {
            // Ensure resourceValue is not null or empty           
            if (resourceValue == null || resourceValue.isEmpty())
            {
                throw new IllegalArgumentException("'resourceValue' may not be "
                        + "null or empty when not using the top level resource!");
            }
            
            // Append resource and resourceValue
            url += "&" + resource.getResourceString() + "=" + resourceValue.replace(' ', '_');
        }
        
        // If there are shards present, then append them to url
        if (shards != null && shards.length > 0)
        {
            url += "&q=" + shards[0].toString();

            for (int i = 1; i < shards.length; i++)
            {
                url += "+" + shards[i].toString();
            }
        }
        
        // Finally, return the generated url
        return url;
    }

}
