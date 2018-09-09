package com.github.agadar.nationstates;

import com.github.agadar.nationstates.xmlconverter.XmlConverter;
import com.github.agadar.nationstates.xmlconverter.IXmlConverter;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.domain.region.Region;
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

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The starting point for consumers of this Java wrapper for the NationStates
 * API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationStates implements INationStates {

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
    private final IXmlConverter xmlConverter = new XmlConverter();

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
     * @param userAgent the User Agent to use for API calls
     */
    public NationStates(String userAgent) {
        this.setUserAgent(userAgent);
        this.xmlConverter.registerTypes(
                Nation.class,
                Region.class,
                World.class,
                WorldAssembly.class);

        try {
            final CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
            final File jarFile = new File(codeSource.getLocation().toURI().getPath());
            defaultDumpDirectory = jarFile.getParentFile().getPath();
        } catch (URISyntaxException ex) {
            throw new NationStatesAPIException(ex);
        }
    }

    @Override
    public void setUserAgent(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            throw new IllegalArgumentException("User Agent may not be null or empty");
        }
        this.userAgent = userAgent;
    }

    @Override
    public void doVersionCheck() {
        int liveVersion = getVersion().execute();

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

    @Override
    public final void registerTypes(Class<?>... types) {
        xmlConverter.registerTypes(types);
    }

    @Override
    public NationQuery getNation(String nationName) {
        return new NationQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, nationName);
    }

    @Override
    public RegionQuery getRegion(String regionName) {
        return new RegionQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, regionName);
    }

    @Override
    public WorldQuery getWorld(WorldShard... shards) {
        return new WorldQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, shards);
    }

    @Override
    public WorldAssemblyQuery getWorldAssembly(Council council) {
        return new WorldAssemblyQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, council);
    }

    @Override
    public VersionQuery getVersion() {
        return new VersionQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion);
    }

    @Override
    public VerifyQuery verifyNation(String nation, String checksum) {
        return new VerifyQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, nation, checksum);
    }

    @Override
    public TelegramQuery sendTelegrams(String clientKey, String telegramId, String secretKey, String... nations) {
        return new TelegramQuery(xmlConverter, generalRateLimiter, scrapingRateLimiter, telegramRateLimiter, recruitmentTelegramRateLimiter,
                baseUrl, userAgent, apiVersion, clientKey, telegramId, secretKey, nations);
    }

    @Override
    public RegionDumpQuery getRegionDump(DailyDumpMode mode, Predicate<Region> filter) {
        return new RegionDumpQuery(baseUrl, userAgent, defaultDumpDirectory, mode, filter);
    }

    @Override
    public NationDumpQuery getNationDump(DailyDumpMode mode, Predicate<Nation> filter) {
        return new NationDumpQuery(baseUrl, userAgent, defaultDumpDirectory, mode, filter);
    }
}
