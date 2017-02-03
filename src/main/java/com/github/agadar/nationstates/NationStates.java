package com.github.agadar.nationstates;

import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.domain.DailyDumpNations;
import com.github.agadar.nationstates.domain.DailyDumpRegions;
import com.github.agadar.nationstates.domain.worldassembly.WorldAssembly;
import com.github.agadar.nationstates.domain.world.World;
import com.github.agadar.nationstates.shard.WorldShard;
import com.github.agadar.nationstates.query.NationDumpQuery;
import com.github.agadar.nationstates.query.NationQuery;
import com.github.agadar.nationstates.query.RegionDumpQuery;
import com.github.agadar.nationstates.query.RegionQuery;
import com.github.agadar.nationstates.query.TelegramQuery;
import com.github.agadar.nationstates.query.VerifyQuery;
import com.github.agadar.nationstates.query.VersionQuery;
import com.github.agadar.nationstates.query.WAQuery;
import com.github.agadar.nationstates.query.WorldQuery;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The starting point for consumers of this Java wrapper for the NationStates
 * API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class NationStates {

    /**
     * The NationStates API version this wrapper uses.
     */
    public static final int API_VERSION = 9;

    /**
     * The user agent with which this library makes requests.
     */
    private static String USER_AGENT;

    /**
     * Static 'constructor' that sets up the initial JAXBContext.
     */
    static {
        XmlConverter.registerTypes(DailyDumpNations.class, DailyDumpRegions.class,
                World.class, WorldAssembly.class);
    }

    /**
     * Sets the User Agent. If this is the first time the User Agent is set,
     * then a version check is automatically done as well.
     *
     * NationStates moderators should be able to identify you and your script
     * via your User Agent. As such, try providing at least your nation name,
     * and preferably include your e-mail address, a link to a website you own,
     * or something else that can help them contact you if needed.
     *
     * @param userAgent the User Agent to use for API calls
     * @throws IllegalArgumentException if userAgent is null or empty.
     */
    public static void setUserAgent(String userAgent) {
        // Make sure the new value is not null or empty.
        if (userAgent == null || userAgent.isEmpty()) {
            throw new IllegalArgumentException("User Agent may not be null or empty");
        }

        // If all is well, set the user agent and do a version check.
        final boolean isNotSetYet = USER_AGENT == null || USER_AGENT.isEmpty();
        USER_AGENT = userAgent;

        // Do version check if this is the first time the User Agent is set.
        if (isNotSetYet) {
            int liveVersion = version().execute();

            // Validate live version and log appropriate messages.
            Logger logger = Logger.getLogger(NationStates.class.getName());
            String start = String.format("Version check: wrapper wants to "
                    + "use version '%s', latest live version is"
                    + " '%s'.", API_VERSION, liveVersion);

            switch (liveVersion) {
                case API_VERSION:
                    logger.log(Level.INFO, "{0} Wrapper should work correctly.", start);
                    break;
                case API_VERSION + 1:
                    logger.log(Level.WARNING, "{0} Wrapper may fail to load daily "
                            + "dumps. Please update the wrapper.", start);
                    break;
                default:
                    logger.log(Level.SEVERE, "{0} Wrapper may not work correctly. Please"
                            + " update the wrapper.", start);
            }
        }
    }

    /**
     * Getter for the User Agent.
     *
     * @return the User Agent
     */
    public static String getUserAgent() {
        return USER_AGENT;
    }

    /**
     * Starts building a nation query, using the given nation name.
     *
     * @param nationName name of the nation to query
     * @return a new nation query
     */
    public static NationQuery nation(String nationName) {
        return new NationQuery(nationName);
    }

    /**
     * Starts building a region query, using the given region name.
     *
     * @param regionName name of the region to query
     * @return a new region query
     */
    public static RegionQuery region(String regionName) {
        return new RegionQuery(regionName);
    }

    /**
     * Starts building a world query, using the selected shards.
     *
     * @param shards the selected shards
     * @return a new world query
     */
    public static WorldQuery world(WorldShard... shards) {
        return new WorldQuery(shards);
    }

    /**
     * Starts building a World Assembly query, using the selected council type.
     *
     * @param council the council type to query
     * @return a new World Assembly query
     */
    public static WAQuery wa(Council council) {
        return new WAQuery(council);
    }

    /**
     * Starts building a query that retrieves the version number of the latest
     * live NationStates API.
     *
     * @return a new version query
     */
    public static VersionQuery version() {
        return new VersionQuery();
    }

    /**
     * Starts building a query that verifies a nation.
     *
     * @param nation the nation to verify
     * @param checksum the verification checksum
     * @return a new verify query
     */
    public static VerifyQuery verify(String nation, String checksum) {
        return new VerifyQuery(nation, checksum);
    }

    /**
     * Starts building a query that sends (a) telegram(s).
     *
     * @param clientKey the client key
     * @param telegramId the telegram id
     * @param secretKey the telegram's secret key
     * @param nations the nation(s) to send the telegram to
     * @return a new telegram query
     */
    public static TelegramQuery telegram(String clientKey, String telegramId, String secretKey, String... nations) {
        return new TelegramQuery(clientKey, telegramId, secretKey, nations);
    }

    /**
     * Starts building a query that retrieves the daily region dump.
     *
     * @param mode the daily dump mode to use
     * @return a new daily region dump query
     */
    public static RegionDumpQuery regiondump(DailyDumpMode mode) {
        return new RegionDumpQuery(mode);
    }

    /**
     * Starts building a query that retrieves the daily nation dump.
     *
     * Warning: reading the XML file and parsing it to objects may cause a
     * java.lang.OutOfMemoryError on older machines due to the sheer number of
     * Nation objects being created from parsing the retrieved XML file.
     *
     * @param mode the daily dump mode to use
     * @return a new daily nation dump query
     */
    public static NationDumpQuery nationdump(DailyDumpMode mode) {
        return new NationDumpQuery(mode);
    }
}
