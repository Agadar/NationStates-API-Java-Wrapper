package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for World requests. These shards have a 1:1 correspondence
 * with the variable fields in World.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum WorldShard implements Shard {

    /**
     * The world's census scores.
     */
    Census("CENSUS"),
    /**
     * Id of the current census.
     */
    CensusId("CENSUSID"),
    /**
     * Descriptions of the current or selected census.
     */
    CensusDescriptions("CENSUSDESC"),
    /**
     * Name of the current or selected census.
     */
    CensusName("CENSUSNAME"),
    /**
     * The census scores of the world's nations.
     */
    CensusRanks("CENSUSRANKS"),
    /**
     * The scale of the current or selected census.
     */
    CensusScale("CENSUSSCALE"),
    /**
     * The title of the current or selected census.
     */
    CensusTitle("CENSUSTITLE"),
    /**
     * The selected dispatch. Includes the dispatch text. An id must be
     * supplied.
     */
    SelectedDispatch("DISPATCH"),
    /**
     * The latest hottest or selected dispatches. Does not include dispatches'
     * texts.
     */
    Dispatches("DISPATCHLIST"),
    /**
     * Name of today's featured region.
     */
    FeaturedRegion("FEATUREDREGION"),
    /**
     * List of the most recent or selected happenings.
     */
    Happenings("HAPPENINGS"),
    /**
     * List of all nations in the world.
     */
    Nations("NATIONS"),
    /**
     * List of newest nations.
     */
    NewestNations("NEWNATIONS"),
    /**
     * The number of nations in the world.
     */
    NumberOfNations("NUMNATIONS"),
    /**
     * The number of regions in the world.
     */
    NumberOfRegions("NUMREGIONS"),
    /**
     * The selected poll.
     */
    SelectedPoll("POLL"),
    /**
     * List of all regions in the world.
     */
    Regions("REGIONS"),
    /**
     * List of regions selected by tag. Tags must be supplied.
     */
    RegionsByTag("REGIONSBYTAG");

    /**
     * The underlying shard name
     */
    private final String shardName;

    /**
     * Instantiate a new entry with the given shard name.
     *
     * @param shardName the name of the underlying shard
     */
    private WorldShard(String shardName) {
        this.shardName = shardName;
    }

    @Override
    public String shardName() {
        return shardName;
    }
}
