package com.github.agadar.nsapi;

import com.github.agadar.nsapi.enums.*;
import com.github.agadar.nsapi.enums.shard.WorldShard;
import com.github.agadar.nsapi.query.DailyDumpQuery;
import com.github.agadar.nsapi.query.NationQuery;
import com.github.agadar.nsapi.query.RegionQuery;
import com.github.agadar.nsapi.query.TelegramQuery;
import com.github.agadar.nsapi.query.VerifyQuery;
import com.github.agadar.nsapi.query.VersionQuery;
import com.github.agadar.nsapi.query.WAQuery;
import com.github.agadar.nsapi.query.WorldQuery;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The starting point for consumers of this Java wrapper for the NatioNStates API.
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public final class NSAPI
{
    /** The NationStates API version this wrapper uses. */
    public static final int API_VERSION = 8;
    
    /** The user agent with which this library makes requests. */
    private static String USER_AGENT;
    
    /**
     * Sets the User Agent, and then immediately verifies the version. Trying to
     * set the User Agent a second time is illegal.
     * 
     * NationStates moderators should be able to identify you and your script
     * via your User Agent. As such, try providing at least your nation name,
     * and preferably include your e-mail address, a link to a website you own, 
     * or something else that can help them contact you if needed.
     * 
     * @param userAgent the User Agent to use for API calls
     */
    public static void setUserAgent(String userAgent)
    {
        // Make sure we're not setting the user agent again.
        if (USER_AGENT != null && !USER_AGENT.isEmpty())
        {
            throw new NationStatesAPIException("User Agent is already set!");
        }
        
        // Make sure the new value is not null or empty.
        if (userAgent == null || userAgent.isEmpty())
        {
            throw new NationStatesAPIException("Tried to set null or empty User Agent!");
        }
        
        // If all is well, set the user agent and do a version check.
        USER_AGENT = userAgent;
        int liveVersion = version().execute();
        
        // Validate live version and log appropriate messages.
        Logger logger = Logger.getLogger(NSAPI.class.getName());
        String start = String.format("Version check: wrapper wants to "
                + "use version '%s', latest live version is"
                + " '%s'.", API_VERSION, liveVersion);
        
        switch (liveVersion)
        {
            case API_VERSION:
                logger.log(Level.INFO, "{0} Wrapper should work correctly.", start);
                break;
            case API_VERSION + 1:
                logger.log(Level.WARNING, "{0} Wrapper should work correctly, but it"
                        + " is advised to update the wrapper.", start);
                break;
            default:
                logger.log(Level.SEVERE, "{0} Wrapper may not work correctly. Please"
                        + " update the wrapper.", start);
        }
    }
    
    /**
     * Getter for the User Agent.
     * 
     * @return the User Agent
     */
    public static String getUserAgent()
    {
        return USER_AGENT;
    }
    
    /**
     * Starts building a nation query, using the given nation name.
     * 
     * @param nationName name of the nation to query
     * @return a new nation query
     */
    public static NationQuery nation(String nationName)
    {
        return new NationQuery(nationName);
    }
    
    /**
     * Starts building a region query, using the given region name.
     * 
     * @param regionName name of the region to query
     * @return a new region query
     */
    public static RegionQuery region(String regionName)
    {
        return new RegionQuery(regionName);
    }
    
    /**
     * Starts building a world query, using the selected shards.
     * 
     * @param shards the selected shards
     * @return a new world query
     */
    public static WorldQuery world(WorldShard... shards)
    {
        return new WorldQuery(shards);
    }
    
    /**
     * Starts building a World Assembly query, using the selected council type.
     * 
     * @param council the council type to query
     * @return a new World Assembly query
     */
    public static WAQuery wa(Council council)
    {
        return new WAQuery(council);
    }
    
    /**
     * Starts building a query that retrieves the version number of the latest 
     * live NationStates API.
     * 
     * @return a new version query
     */
    public static VersionQuery version()
    {
        return new VersionQuery();
    }
    
    /**
     * Starts building a query that verifies a nation.
     * 
     * @param nation the nation to verify
     * @param checksum the verification checksum
     * @return a new verify query
     */
    public static VerifyQuery verify(String nation, String checksum)
    {
        return new VerifyQuery(nation, checksum);
    }
    
    /**
     * Starts building a query that sends a telegram.
     * 
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param nation the nation to send the telegram to
     * @return a new telegram query
     */
    public static TelegramQuery telegram(String clientKey, String telegramId, String secretKey, String nation)
    {
        return new TelegramQuery(clientKey, telegramId, secretKey, nation);
    }
    
    /**
     * Starts building a query that retrieves the daily data dump.
     * 
     * @param mode the daily dump mode to use
     * @return a new version query
     */
    public static DailyDumpQuery dailydump(DailyDumpMode mode)
    {
        return new DailyDumpQuery(mode);
    }
}
