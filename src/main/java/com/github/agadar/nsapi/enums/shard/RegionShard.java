package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for Region requests. These shards have a 1:1 correspondence 
 * with the variable fields in Region.java.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public enum RegionShard implements Shard
{
    /** This region's census scale scores. */
    Census("CENSUS"),
    /** The census scale scores of this region's nations. */
    CensusRanks("CENSUSRANKS"),
    /** 
     * The name of the nation that is the region's world assembly delegate.
     * Returns '0' if no delegate exists.
     */    
    Delegate("DELEGATE"),
    /** The authorities granted to the region's world assembly delegate. */
    DelegateAuthorities("DELEGATEAUTH"),
    /** The number of endorsements the region's world assembly delegate has. */
    DelegateEndorsements("DELEGATEVOTES"),
    /** The region's embassies. */
    Embassies("EMBASSIES"),
    /** 
     * Regional Message Board permissions for regions with which this region exchanges embassies.
     * Descriptions of the codes:
     * 
     * '0': No embassy posting;
     * 'con': Delegates and Founders of embassy regions;
     * 'off': Officers of embassy regions;
     * 'com': Officers of embassy regions with Communications authority;
     * 'all': All residents of embassy regions.
     */
    EmbassiesRMBPerms("EMBASSYRMB"),
    /** The complete mark-up of the regional factbook. */
    Factbook("FACTBOOK"),
    /** URL to the region's flag image. */
    FlagUrl("FLAG"),
    /** Description of when this region was founded. */
    Founded("FOUNDED"),
    /** UNIX timestamp of when this region was founded. */
    FoundedTime("FOUNDEDTIME"),
    /** The name of the region's founding nation. Returns '0' if none exists. */
    Founder("FOUNDER"),
    /** The authorities granted to the region's founder. */
    FounderAuthorities("FOUNDERAUTH"),
    /** The region's votes for the current General Assembly resolution. */
    GeneralAssemblyVote("GAVOTE"),
    /** List of the most recent of this region's happenings. */
    RecentHappenings("HAPPENINGS"),
    /** List of history. Not sure what these are. */
    History("HISTORY"),
    /** List of 10 most recent regional messages. */
    RegionalMessages("MESSAGES"),
    /** The region's name. */
    Name("NAME"),
    /** List of nations that inhabit this region. */
    NationNames("NATIONS"),
    /** The number of nations that inhabit this region. */
    NumberOfNations("NUMNATIONS"),
    /** List of regional officers. */
    Officers("OFFICERS"),
    /** The regional poll that is currently being conducted. */
    CurrentPoll("POLL"),
    /** The region's power. */
    Power("POWER"),
    /** The region's votes for the current Security Council resolution. */
    SecurityCouncilVote("SCVOTE"),
    /** The region's tags. */
    Tags("TAGS"),
    /** This region's statistics of the current or last zombie event. */
    ZombieInfo("ZOMBIE");
    
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

    @Override
    public String shardName()
    {
        return shardName;
    }
}
