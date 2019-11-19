package com.github.agadar.nationstates;

import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.exception.NationStatesAPIException;
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

import java.util.function.Predicate;

/**
 * The starting point for consumers of this Java wrapper for the NationStates
 * API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public interface NationStates {

    /**
     * Sets the User Agent. NationStates moderators should be able to identify you
     * and your script via your User Agent. As such, try providing at least your
     * nation name, and preferably include your e-mail address, a link to a website
     * you own, or something else that can help them contact you if needed.
     *
     * @param userAgent the User Agent to use for API calls
     * @throws IllegalArgumentException if userAgent is null or empty.
     */
    public void setUserAgent(String userAgent);

    /**
     * Performs a simple version check, logging the results in the console.
     * 
     * @throws NationStatesAPIException If something failed during the version
     *                                  check.
     */
    public void doVersionCheck() throws NationStatesAPIException;

    /**
     * Adds the given classes to the JAXB context so that they can be parsed to from
     * retrieved XML responses and files. Classes that inherit any of the classes in
     * the domain-package don't need any xml-annotations. Classes that do no inherit
     * those classes, do need xml-annotations.
     *
     * @param types the classes to add to the JAXB context
     * @throws NationStatesAPIException If adding the types to the JAXB context
     *                                  failed.
     */
    public void registerTypes(Class<?>... types) throws NationStatesAPIException;

    /**
     * Starts building a nation query, using the given nation name.
     *
     * @param nationName name of the nation to query
     * @return a new nation query
     */
    public NationQuery getNation(String nationName);

    /**
     * Starts building a region query, using the given region name.
     *
     * @param regionName name of the region to query
     * @return a new region query
     */
    public RegionQuery getRegion(String regionName);

    /**
     * Starts building a world query, using the selected shards.
     *
     * @param shards the selected shards
     * @return a new world query
     */
    public WorldQuery getWorld(WorldShard... shards);

    /**
     * Starts building a World Assembly query, using the selected council type.
     *
     * @param council the council type to query
     * @return a new World Assembly query
     */
    public WorldAssemblyQuery getWorldAssembly(Council council);

    /**
     * Starts building a query that retrieves the version number of the latest live
     * NationStates API.
     *
     * @return a new version query
     */
    public VersionQuery getVersion();

    /**
     * Starts building a query that verifies a nation.
     *
     * @param nation   the nation to verify
     * @param checksum the verification checksum
     * @return a new verify query
     */
    public VerifyQuery verifyNation(String nation, String checksum);

    /**
     * Starts building a query that sends (a) telegram(s).
     *
     * @param clientKey  the client key
     * @param telegramId the telegram id
     * @param secretKey  the telegram's secret key
     * @param nations    the nation(s) to send the telegram to
     * @return a new telegram query
     */
    public TelegramQuery sendTelegrams(String clientKey, String telegramId, String secretKey, String... nations);

    /**
     * Starts building a query that retrieves the daily region dump.
     *
     * @param mode   the daily dump mode to use
     * @param filter filter used for selecting regions
     * @return a new daily region dump query
     */
    public RegionDumpQuery getRegionDump(DailyDumpMode mode, Predicate<Region> filter);

    /**
     * Starts building a query that retrieves the daily nation dump.
     *
     * Warning: reading the XML file and parsing it to objects may cause a
     * java.lang.OutOfMemoryError on older machines due to the sheer number of
     * Nation objects being created from parsing the retrieved XML file.
     *
     * @param mode   the daily dump mode to use
     * @param filter filter used for selecting nations
     * @return a new daily nation dump query
     */
    public NationDumpQuery getNationDump(DailyDumpMode mode, Predicate<Nation> filter);
}
