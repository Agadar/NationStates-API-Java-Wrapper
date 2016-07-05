package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for World requests. These shards have a 1:1 correspondence 
 * with the variable fields in World.java.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public enum WorldShard
{
    /** The world's census scale scores. */
    Census("CENSUS"),
    /** Id of the current census. */
    CensusId("CENSUSID"),
    /** Descriptions of the current or selected census. */
    CensusDescriptions("CENSUSDESC"),
    /** Name of the current or selected census. */
    CensusName("CENSUSNAME"),
    /** The census scale scores of the world's nations. */
    CensusRanks("CENSUSRANKS"),
    e("CENSUSSCALE"),
    f("CENSUSTITLE"),
    g("DISPATCH"),
    h("DISPATCHLIST"),
    i("FEATUREDREGION"),
    j("HAPPENINGS"),
    k("NATIONS"),
    l("NEWNATIONS"),
    m("NUMNATIONS"),
    n("NUMREGIONS"),
    o("POLL"),
    p("REGIONS"),
    q("REGIONSBYTAG");
    
    /** The underlying shard name */
    private final String shardName;
    
    /**
     * Instantiate a new entry with the given shard name.
     * 
     * @param shardName the name of the underlying shard
     */
    private WorldShard(String shardName) 
    {
        this.shardName = shardName;
    }

    /**
     * Return the underlying shard name.
     * 
     * @return the underlying shard name
     */
    @Override
    public String toString() 
    {
        return shardName;
    }
}
