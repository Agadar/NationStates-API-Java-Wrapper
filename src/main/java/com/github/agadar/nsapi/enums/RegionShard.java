package com.github.agadar.nsapi.enums;

/**
 * Shards available for Region requests. These shards have a 1:1 correspondence 
 * with the variable fields in Region.class.
 * 
 * @author Martin
 */
public enum RegionShard
{
    /** This region's census scale scores */
    Census("CENSUS"),
    /** The census scale scores of this region's nations */
    CensusRanks("CENSUSRANKS"),
    /** 
     * The name of the nation that is the region's world assembly delegate.
     * Returns '0' if no delegate exists.
     */    
    Delegate("DELEGATE"),
    /** The authorities granted to the region's world assembly delegate */
    DelegateAuthorities("DELEGATEAUTH"),
    /** The number of endorsements the region's world assembly delegate has */
    DelegateEndorsements("DELEGATEVOTES"),
    /** The region's embassies */
    Embassies("EMBASSIES"),
    /** 
     * Regional Message Board permissions for regions with which this region exchanges embassies.
     * Descriptions of the codes:
     * 
     * '0': No embassy posting;
     * 'con': Delegates & Founders of embassy regions;
     * 'off': Officers of embassy regions;
     * 'com': Officers of embassy regions with Communications authority;
     * 'all': All residents of embassy regions.
     */
    EmbassiesRMBPerms("EMBASSYRMB"),
    /** The complete mark-up of the regional factbook */
    Factbook("FACTBOOK"),
    /** URL to the region's flag image */
    FlagUrl("FLAG"),
    /** The name of the region's founding nation. Returns '0' if none exists. */
    Founder("FOUNDER"),
    /** The authorities granted to the region's founder */
    FounderAuthorities("FOUNDERAUTH");
//    GAVOTE,
//    HAPPENINGS,
//    HISTORY,
//    MESSAGES,
//    NAME,
//    NATIONS,
//    NUMNATIONS,
//    OFFICERS,
//    POLL,
//    POWER,
//    SCVOTE,
//    TAGS,
//    ZOMBIE;
    
    /** The underlying shard name */
    private final String shardName;
    
    /**
     * Instantiate a new entry with the given shard name.
     * 
     * @param shardName the name of the underlying shard
     */
    private RegionShard(String shardName) 
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
