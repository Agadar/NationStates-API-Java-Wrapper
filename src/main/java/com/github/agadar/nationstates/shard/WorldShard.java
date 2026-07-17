package com.github.agadar.nationstates.shard;

/**
 * Shards available for World requests. These shards have a 1:1 correspondence
 * with the variable fields in World.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum WorldShard implements Shard {

    /**
     * Gets the nation banners with the given id's.
     */
    BANNER("BANNER"),
    /**
     * The world's census scores.
     */
    CENSUS("CENSUS"),
    /**
     * Id of the current census.
     */
    CENSUS_ID("CENSUSID"),
    /**
     * Descriptions of the current or selected census.
     */
    CENSUS_DESCRIPTIONS("CENSUSDESC"),
    /**
     * Name of the current or selected census.
     */
    CENSUS_NAME("CENSUSNAME"),
    /**
     * The census scores of the world's nations.
     */
    CENSUS_RANKS("CENSUSRANKS"),
    /**
     * The scale of the current or selected census.
     */
    CENSUS_SCALE("CENSUSSCALE"),
    /**
     * The title of the current or selected census.
     */
    CENSUS_TITLE("CENSUSTITLE"),
    /**
     * The selected dispatch. Includes the dispatch text. An id must be
     * supplied.
     */
    SELECTED_DISPATCH("DISPATCH"),
    /**
     * The latest hottest or selected dispatches. Does not include dispatches'
     * texts.
     */
    DISPATCHES("DISPATCHLIST"),
    /**
     * An N-Day faction. Only available during N-Day, when nations are conducting nuclear exchanges.
     */
    FACTION("FACTION"),
    /**
     * All N-Day factions. Only available during N-Day, when nations are conducting nuclear exchanges.
     */
    FACTIONS("FACTIONS"),
    /**
     * Name of today's featured region.
     */
    FEATURED_REGION("FEATUREDREGION"),
    /**
     * List of the most recent or selected happenings.
     */
    HAPPENINGS("HAPPENINGS"),
    /**
     * ID of the last event.
     */
    LAST_EVENT_ID("LASTEVENTID"),
    /**
     * List of all nations in the world.
     */
    NATIONS("NATIONS"),
    /**
     * List of newest nations.
     */
    NEWEST_NATIONS("NEWNATIONS"),
    /**
     * Details of the newest nations.
     */
    NEWEST_NATIONS_DETAILS("NEWNATIONDETAILS"),
    /**
     * The number of nations in the world.
     */
    NUMBER_OF_NATIONS("NUMNATIONS"),
    /**
     * The number of regions in the world.
     */
    NUMBER_OF_REGIONS("NUMREGIONS"),
    /**
     * The selected poll.
     */
    SELECTED_POLL("POLL"),
    /**
     * List of all regions in the world.
     */
    REGIONS("REGIONS"),
    /**
     * List of regions selected by tag. Tags must be supplied.
     */
    REGIONS_BY_TAG("REGIONSBYTAG"),
    /**
     * Information about the current telegram queue.
     */
    TELEGRAM_QUEUE("TGQUEUE");

    /**
     * The underlying shard name
     */
    private final String shardName;

    /**
     * Instantiate a new entry with the given shard name.
     *
     * @param shardName the name of the underlying shard
     */
    WorldShard(String shardName) {
        this.shardName = shardName;
    }

    @Override
    public String shardName() {
        return shardName;
    }
}
