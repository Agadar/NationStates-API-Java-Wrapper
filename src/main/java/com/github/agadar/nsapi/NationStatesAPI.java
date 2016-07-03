package com.github.agadar.nsapi;

import com.github.agadar.nsapi.domain.Nation;
import com.github.agadar.nsapi.domain.Region;
import com.github.agadar.nsapi.enums.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 * The Java wrapper for the NationStates API.
 *
 * @author Martin
 */
public class NationStatesAPI
{
    /** URL to the Nation API */
    private static final String NATION_URL = "http://www.nationstates.net/cgi-bin/api.cgi?nation=";
    /** URL to the Region API */
    private static final String REGION_URL = "http://www.nationstates.net/cgi-bin/api.cgi?region=";
    /** URL to the World API */
    private static final String WORLD_URL = "http://www.nationstates.net/cgi-bin/api.cgi?";
    /** URL to the World Assembly API */
    private static final String WA_URL = "http://www.nationstates.net/cgi-bin/api.cgi?wa=";
    /** The user agent with which this library makes requests */
    private static final String USER_AGENT = "Agadar's Wrapper (https://github.com/Agadar/NationStates-API-Java-Wrapper)";
    /** The NationStates API version this wrapper uses */
    private static final String API_VERSION = "&v=8";
    /** The JAXBContext for this API. */
    private final JAXBContext jc;
    
    /** Constructor, sets up the JAXBContext. */
    public NationStatesAPI()
    {
        try
        {
            jc = JAXBContext.newInstance(Nation.class, Region.class);
        }
        catch (JAXBException ex)
        {
            throw new NationStatesAPIException("Failed to initialize NationStatesAPI instance!", ex);
        }
    }

    /**
     * Makes a Nation request.
     *
     * @param nationName the name of the nation. Should not be empty or null.
     * @param shards     the shards to return. If no shards are supplied, then a
     *                   compendium of the most commonly sought shards are
     *                   returned.
     * @return the data of the retrieved nation, or null if it wasn't found
     */
    public Nation nation(String nationName, NationShard... shards)
    {
        // Empty check on nation name
        if (nationName == null || nationName.isEmpty())
        {
            throw new IllegalArgumentException(
                    "Nation name may not be null or empty!");
        }

        // Make GET request, then convert XML to Nation and return it
        String url = NATION_URL + spacesToUnderscores(nationName)
                             + encodeShards("&", shards) + API_VERSION;
        return makeRequest(url, Nation.class);
    }

    /**
     * Makes a Region request.
     *
     * @param regionName the name of the region. Should not be empty or null.
     * @param shards     the shards to return. If no shards are supplied, then a
     *                   compendium of the most commonly sought shards are
     *                   returned.
     * @return the data of the retrieved region, or null if it wasn't found
     */
    public Region region(String regionName, RegionShard... shards)
    {
        // Empty check on region name
        if (regionName == null || regionName.isEmpty())
        {
            throw new IllegalArgumentException(
                    "Region name may not be null or empty!");
        }

        // Make GET request, then convert XML to Region and return it
        String url = REGION_URL + spacesToUnderscores(regionName)
                             + encodeShards("&", shards) + API_VERSION;
        return makeRequest(url, Region.class);
    }

    /**
     * Makes a World request.
     *
     * @param shards the shards to return. At least one shard must be supplied.
     */
    public void world(WorldShard... shards)
    {
        if (shards.length == 0)
        {
            throw new IllegalArgumentException("'shards' may not be empty!");
        }

        String url = WORLD_URL + encodeShards("", shards) + API_VERSION;
        //String raw = makeRequest(url);
    }

    /**
     * Makes a World Assembly request.
     *
     * @param council the council to request data from. May not be null.
     * @param shards  the shards to return.
     */
    public void worldAssembly(Council council, WorldAssemblyShard... shards)
    {
        if (council == null)
        {
            throw new IllegalArgumentException("'council' may not be null!");
        }

        String url = WA_URL + council.getCouncilNumber() + encodeShards("&", shards) + API_VERSION;
        //String raw = makeRequest(url);
    }

    /**
     * Makes the GET request to the NationStates API.
     *
     * @param urlStr the url to make the request to
     * @return the retrieved data, in XML-format
     */
    private <T> T makeRequest(String urlStr, Class<T> responseType)
    {
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
            // If something went wrong while processing the request, throw error
            else if (responseCode != 200)
            {
                throw new NationStatesAPIException("Request to NationStates API failed : HTTP error code : " + conn.getResponseCode());
            }

            // Read and convert the response body.
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource xml = new StreamSource(conn.getInputStream());
            JAXBElement<T> je1 = unmarshaller.unmarshal(xml, responseType);
            T converted = je1.getValue();
            
            // Close connection and return.
            conn.disconnect();
            return converted;
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
     * Replaces each space in the String with an underscore.
     *
     * @param s the String to encode
     * @return the encoded String
     */
    private static String spacesToUnderscores(String s)
    {
        return s.replace(' ', '_');
    }

    /**
     * Generates a String based on the supplied shards. Returns an empty string
     * if shards is empty.
     *
     * @param prefix the character to prefix the generated string with.
     * @param shards the shards to generate a String of.
     * @return the generated String
     */
    private static String encodeShards(String prefix, Enum... shards)
    {
        if (shards.length == 0)
        {
            return "";
        }
        
        String gen = prefix + "q=" + shards[0].toString();

        for (int i = 1; i < shards.length; i++)
        {
            gen += "+" + shards[i].toString();
        }

        return gen;
    }

}
