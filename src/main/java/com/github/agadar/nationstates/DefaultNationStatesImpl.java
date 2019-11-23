package com.github.agadar.nationstates;

import java.io.File;
import java.security.CodeSource;
import java.util.function.Predicate;

import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.domain.world.World;
import com.github.agadar.nationstates.domain.worldassembly.WorldAssembly;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.exception.NationStatesAPIException;
import com.github.agadar.nationstates.query.NationDumpQuery;
import com.github.agadar.nationstates.query.NationQuery;
import com.github.agadar.nationstates.query.QueryDependencies;
import com.github.agadar.nationstates.query.RegionDumpQuery;
import com.github.agadar.nationstates.query.RegionQuery;
import com.github.agadar.nationstates.query.TelegramQuery;
import com.github.agadar.nationstates.query.VerifyQuery;
import com.github.agadar.nationstates.query.VersionQuery;
import com.github.agadar.nationstates.query.WorldAssemblyQuery;
import com.github.agadar.nationstates.query.WorldQuery;
import com.github.agadar.nationstates.ratelimiter.DependantRateLimiter;
import com.github.agadar.nationstates.ratelimiter.NormalRateLimiter;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import com.github.agadar.nationstates.shard.WorldShard;
import com.github.agadar.nationstates.xmlconverter.XmlConverter;
import com.github.agadar.nationstates.xmlconverter.XmlConverterImpl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * The default starting point for consumers of this Java wrapper for the
 * NationStates API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Slf4j
public class DefaultNationStatesImpl implements NationStates {

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
     * requests per 30 seconds. To make sure we're on the safe side, we reduce this
     * to 50 requests per 30.05 seconds. To get a spread-like pattern instead of a
     * burst-like pattern, we make this into 10 requests per 6.01 seconds.
     */
    private final RateLimiter generalRateLimiter = new NormalRateLimiter(10, 6010);

    /**
     * Rate limiter for API calls when scraping. Reduces the rate limit further to
     * just 1 request per second, as suggested by the official documentation.
     */
    private final RateLimiter scrapingRateLimiter = new DependantRateLimiter(generalRateLimiter, 1, 1000);

    /**
     * The rate limiter for normal telegrams. The mandated rate limit is 1 telegram
     * per 30 seconds. To make sure we're on the safe side, we reduce this to 1
     * telegram per 30.05 seconds.
     */
    private final RateLimiter telegramRateLimiter = new DependantRateLimiter(generalRateLimiter, 1, 30050);

    /**
     * The rate limiter for recruitment telegrams. The mandated rate limit is 1
     * telegram per 180 seconds. To make sure we're on the safe side, we reduce this
     * to 1 telegram per 180.05 seconds.
     */
    private final RateLimiter recruitmentTelegramRateLimiter = new DependantRateLimiter(telegramRateLimiter, 1, 180050);

    /**
     * For converting xml input from the API to domain classes.
     */
    private final XmlConverter xmlConverter = new XmlConverterImpl();

    /**
     * The default directory for dump files retrieved from the API.
     */
    private final String defaultDumpDirectory;

    /**
     * The user agent with which this library makes requests.
     */
    private String userAgent;

    /**
     * Instantiates a new service and sets the User Agent. NationStates moderators
     * should be able to identify you and your script via your User Agent. As such,
     * try providing at least your nation name, and preferably include your e-mail
     * address, a link to a website you own, or something else that can help them
     * contact you if needed.
     *
     * @param userAgent the User Agent to use for API calls
     * @throws NationStatesAPIException If registering the relevant classes to the
     *                                  JAXB context or deriving the default dump
     *                                  directory failed.
     */
    public DefaultNationStatesImpl(@NonNull String userAgent) throws NationStatesAPIException {
        this.setUserAgent(userAgent);
        this.xmlConverter.registerTypes(Nation.class, Region.class, World.class, WorldAssembly.class);

        try {
            CodeSource codeSource = this.getClass().getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            defaultDumpDirectory = jarFile.getParentFile().getPath();
        } catch (Exception ex) {
            log.error("An error occured while deriving the default dump directory", ex);
            throw new NationStatesAPIException(ex);
        }
    }

    @Override
    public void setUserAgent(@NonNull String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public void doVersionCheck() throws NationStatesAPIException {
        int version = getVersion().execute();
        logNationStatesApiVersion(version);
    }

    @Override
    public final void registerTypes(@NonNull Class<?>... types) throws NationStatesAPIException {
        xmlConverter.registerTypes(types);
    }

    @Override
    public NationQuery getNation(@NonNull String nationName) {
        return new NationQuery(collectDependencies(), nationName);
    }

    @Override
    public RegionQuery getRegion(@NonNull String regionName) {
        return new RegionQuery(collectDependencies(), regionName);
    }

    @Override
    public WorldQuery getWorld(@NonNull WorldShard... shards) {
        return new WorldQuery(collectDependencies(), shards);
    }

    @Override
    public WorldAssemblyQuery getWorldAssembly(@NonNull Council council) {
        return new WorldAssemblyQuery(collectDependencies(), council);
    }

    @Override
    public VersionQuery getVersion() {
        return new VersionQuery(collectDependencies());
    }

    @Override
    public VerifyQuery verifyNation(@NonNull String nation, @NonNull String checksum) {
        return new VerifyQuery(collectDependencies(), nation, checksum);
    }

    @Override
    public TelegramQuery sendTelegrams(@NonNull String clientKey, @NonNull String telegramId, @NonNull String secretKey,
            @NonNull String... nations) {
        return new TelegramQuery(collectDependencies(), telegramRateLimiter, recruitmentTelegramRateLimiter, clientKey,
                telegramId, secretKey, nations);
    }

    @Override
    public RegionDumpQuery getRegionDump(@NonNull DailyDumpMode mode, @NonNull Predicate<Region> filter) {
        return new RegionDumpQuery(baseUrl, userAgent, defaultDumpDirectory, mode, filter);
    }

    @Override
    public NationDumpQuery getNationDump(@NonNull DailyDumpMode mode, @NonNull Predicate<Nation> filter) {
        return new NationDumpQuery(baseUrl, userAgent, defaultDumpDirectory, mode, filter);
    }

    private QueryDependencies collectDependencies() {
        return QueryDependencies.builder()
                .apiVersion(apiVersion)
                .baseUrl(baseUrl)
                .generalRateLimiter(generalRateLimiter)
                .scrapingRateLimiter(scrapingRateLimiter)
                .userAgent(userAgent)
                .xmlConverter(xmlConverter).build();
    }

    private void logNationStatesApiVersion(int version) {
        String logText = String.format("Version check: wrapper wants to use version '%s', latest live version is '%s'.",
                apiVersion, version);

        switch (version) {
            case apiVersion:
                log.info("{} Wrapper should work correctly.", logText);
                break;
            case apiVersion + 1:
                log.warn("{} Wrapper may fail to load daily dumps. Please update the wrapper.", logText);
                break;
            default:
                log.error("{} Wrapper may not work correctly. Please update the wrapper.", logText);
                break;
        }
    }
}
