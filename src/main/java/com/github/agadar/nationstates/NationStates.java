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
import com.github.agadar.nationstates.query.WorldAssemblyQuery;
import com.github.agadar.nationstates.query.WorldQuery;
import com.github.agadar.nationstates.ratelimiter.DependantRateLimiter;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

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
    private final int apiVersion = 9;

    /**
     * Base URL for all NationStates calls.
     */
    private final String baseUrl = "https://www.nationstates.net/";

    /**
     * The general rate limiter for all API calls. The mandated rate limit is 50
     * requests per 30 seconds. To make sure we're on the safe side, we reduce
     * this to 50 requests per 30.05 seconds. To get a spread-like pattern
     * instead of a burst-like pattern, we make this into 10 requests per 6.01
     * seconds.
     */
    private final IRateLimiter generalRateLimiter = new RateLimiter(10, 6010);

    /**
     * Rate limiter for API calls when scraping. Reduces the rate limit further
     * to just 1 request per second, as suggested by the official documentation.
     */
    private final IRateLimiter scrapingRateLimiter = new DependantRateLimiter(generalRateLimiter, 1, 1000);

    /**
     * The rate limiter for normal telegrams. The mandated rate limit is 1
     * telegram per 30 seconds. To make sure we're on the safe side, we reduce
     * this to 1 telegram per 30.05 seconds.
     */
    private final IRateLimiter telegramRateLimiter = new DependantRateLimiter(generalRateLimiter, 1, 30050);

    /**
     * The rate limiter for recruitment telegrams. The mandated rate limit is 1
     * telegram per 180 seconds. To make sure we're on the safe side, we reduce
     * this to 1 telegram per 180.05 seconds.
     */
    private final IRateLimiter recruitmentTelegramRateLimiter = new DependantRateLimiter(telegramRateLimiter, 1, 180050);

    /**
     * For converting xml input from the API to domain classes.
     */
    public final IXmlConverter xmlConverter = new XmlConverter();

    /**
     * The default directory for dump files retrieved from the API.
     */
    private final String defaultDumpDirectory;

    /**
     * The user agent with which this library makes requests.
     */
    private String userAgent;

    /**
     * Instantiates a new service and sets the User Agent. NationStates
     * moderators should be able to identify you and your script via your User
     * Agent. As such, try providing at least your nation name, and preferably
     * include your e-mail address, a link to a website you own, or something
     * else that can help them contact you if needed.
     *
     * @param userAgent
     */
    public NationStates(String userAgent) {
        this.setUserAgent(userAgent);
        this.xmlConverter.registerTypes(DailyDumpNations.class, DailyDumpRegions.class,
                World.class, WorldAssembly.class);

        try {
            final CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
            final File jarFile = new File(codeSource.getLocation().toURI().getPath());
            defaultDumpDirectory = jarFile.getParentFile().getPath();
        } catch (URISyntaxException ex) {
            throw new NationStatesAPIException(ex);
        }
    }

    /**
     * Sets the User Agent. NationStates moderators should be able to identify
     * you and your script via your User Agent. As such, try providing at least
     * your nation name, and preferably include your e-mail address, a link to a
     * website you own, or something else that can help them contact you if
     * needed.
     *
     * @param userAgent the User Agent to use for API calls
     * @throws IllegalArgumentException if userAgent is null or empty.
     */
    public void setUserAgent(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            throw new IllegalArgumentException("User Agent may not be null or empty");
        }
        this.userAgent = userAgent;
    }

    /**
     * Performs a simple version check, logging the results in the console.
     */
    public void versionCheck() {
        int liveVersion = version().execute();

        // Validate live version and log appropriate messages.
        Logger logger = Logger.getLogger(NationStates.class.getName());
        String start = String.format("Version check: wrapper wants to "
                + "use version '%s', latest live version is"
                + " '%s'.", apiVersion, liveVersion);

        switch (liveVersion) {
            case apiVersion:
                logger.log(Level.INFO, "{0} Wrapper should work correctly.", start);
                break;
            case apiVersion + 1:
                logger.log(Level.WARNING, "{0} Wrapper may fail to load daily "
                        + "dumps. Please update the wrapper.", start);
                break;
            default:
                logger.log(Level.SEVERE, "{0} Wrapper may not work correctly. Please"
                        + " update the wrapper.", start);
        }
    }

    /**
     * Starts building a nation query, using the given nation name.
     *
     * @param nationName name of the nation to query
     * @return a new nation query
     */
    public NationQuery nation(String nationName) {
        return new NationQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, nationName);
    }

    /**
     * Starts building a region query, using the given region name.
     *
     * @param regionName name of the region to query
     * @return a new region query
     */
    public RegionQuery region(String regionName) {
        return new RegionQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, regionName);
    }

    /**
     * Starts building a world query, using the selected shards.
     *
     * @param shards the selected shards
     * @return a new world query
     */
    public WorldQuery world(WorldShard... shards) {
        return new WorldQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, shards);
    }

    /**
     * Starts building a World Assembly query, using the selected council type.
     *
     * @param council the council type to query
     * @return a new World Assembly query
     */
    public WorldAssemblyQuery worldAssembly(Council council) {
        return new WorldAssemblyQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, council);
    }

    /**
     * Starts building a query that retrieves the version number of the latest
     * live NationStates API.
     *
     * @return a new version query
     */
    public VersionQuery version() {
        return new VersionQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion);
    }

    /**
     * Starts building a query that verifies a nation.
     *
     * @param nation the nation to verify
     * @param checksum the verification checksum
     * @return a new verify query
     */
    public VerifyQuery verify(String nation, String checksum) {
        return new VerifyQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, nation, checksum);
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
    public TelegramQuery telegram(String clientKey, String telegramId, String secretKey, String... nations) {
        return new TelegramQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, telegramRateLimiter, recruitmentTelegramRateLimiter,
                baseUrl, userAgent, apiVersion, clientKey, telegramId, secretKey, nations);
    }

    /**
     * Starts building a query that retrieves the daily region dump.
     *
     * @param mode the daily dump mode to use
     * @return a new daily region dump query
     */
    public RegionDumpQuery regiondump(DailyDumpMode mode) {
        return new RegionDumpQuery(xmlConverter, baseUrl, userAgent, defaultDumpDirectory, mode);
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
    public NationDumpQuery nationdump(DailyDumpMode mode) {
        return new NationDumpQuery(xmlConverter, baseUrl, userAgent, defaultDumpDirectory, mode);
    }
}
