package com.github.agadar.nationstates.shard;

/**
 * Shards available for Region requests. These shards have a 1:1 correspondence
 * with the variable fields in Region.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum RegionShard implements Shard {

    /**
     * This region's census scale scores.
     */
    CENSUS("CENSUS"),
    /**
     * The census scale scores of this region's nations.
     */
    CENSUS_RANKS("CENSUSRANKS"),
    /**
     * The name of the nation that is the region's world assembly delegate.
     * Returns '0' if no delegate exists.
     */
    DELEGATE("DELEGATE"),
    /**
     * The authorities granted to the region's world assembly delegate.
     */
    DELEGATE_AUTHORITIES("DELEGATEAUTH"),
    /**
     * The number of endorsements the region's world assembly delegate has.
     */
    DELEGATE_ENDORSEMENTS("DELEGATEVOTES"),
    /**
     * The region's embassies.
     */
    EMBASSIES("EMBASSIES"),
    /**
     * Regional Message Board permissions for regions with which this region
     * exchanges embassies.
     */
    EMBASSIES_RMB_PERMISSIONS("EMBASSYRMB"),
    /**
     * The complete mark-up of the regional factbook.
     */
    FACTBOOK("FACTBOOK"),
    /**
     * URL to the region's flag image.
     */
    FLAG_URL("FLAG"),
    /**
     * Description of when this region was founded.
     */
    FOUNDED_DESCRIPTION("FOUNDED"),
    /**
     * UNIX timestamp of when this region was founded.
     */
    FOUNDED("FOUNDEDTIME"),
    /**
     * The name of the region's founding nation. Returns '0' if none exists.
     */
    FOUNDER("FOUNDER"),
    /**
     * The authorities granted to the region's founder.
     */
    FOUNDER_AUTHORITIES("FOUNDERAUTH"),
    /**
     * The region's votes for the current General Assembly resolution.
     */
    GENERAL_ASSEMBLY_VOTE("GAVOTE"),
    /**
     * List of the most recent of this region's happenings.
     */
    RECENT_HAPPENINGS("HAPPENINGS"),
    /**
     * List of history. Not sure what these are.
     */
    HISTORY("HISTORY"),
    /**
     * List of 10 most recent regional messages.
     */
    REGIONAL_MESSAGES("MESSAGES"),
    /**
     * Ranking of nations with most RMB likes given.
     */
    MOST_LIKED("MOSTLIKED"),
    /**
     * Ranking of nations with most RMB likes received.
     */
    MOST_LIKES("MOSTLIKES"),
    /**
     * Ranking of nations with most RMB posts made.
     */
    MOST_POSTS("MOSTPOSTS"),
    /**
     * The region's name.
     */
    NAME("NAME"),
    /**
     * List of nations that inhabit this region.
     */
    NATION_NAMES("NATIONS"),
    /**
     * The number of nations that inhabit this region.
     */
    NUMBER_OF_NATIONS("NUMNATIONS"),
    /**
     * List of regional officers.
     */
    OFFICERS("OFFICERS"),
    /**
     * The regional poll that is currently being conducted.
     */
    CURRENT_POLL("POLL"),
    /**
     * The region's power.
     */
    POWER("POWER"),
    /**
     * The region's votes for the current Security Council resolution.
     */
    SECURITY_COUNCIL_VOTE("SCVOTE"),
    /**
     * The region's tags.
     */
    TAGS("TAGS"),
    /**
     * This region's statistics of the current or last zombie event.
     */
    ZOMBIE_INFO("ZOMBIE");

    /**
     * The underlying shard name
     */
    private final String shardName;

    /**
     * Instantiate a new entry with the given shard name.
     *
     * @param shardName the name of the underlying shard
     */
    private RegionShard(String shardName) {
        this.shardName = shardName;
    }

    @Override
    public String shardName() {
        return shardName;
    }
}
