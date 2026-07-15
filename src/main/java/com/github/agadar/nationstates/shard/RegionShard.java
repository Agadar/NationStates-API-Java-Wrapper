package com.github.agadar.nationstates.shard;

/**
 * Shards available for Region requests. These shards have a 1:1 correspondence
 * with the variable fields in Region.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum RegionShard implements Shard {
	
	/**
	 * List of banned nations.
	 */
	BANLIST("BANLIST"),
	/**
	 * Id of the regional banner.
	 */
	BANNER("BANNER"),
	/**
	 * Name of the nation that set the regional banner.
	 */
	BANNER_BY("BANNERBY"),
	/**
	 * Full path of the regional banner.
	 */
	BANNER_URL("BANNERURL"),
    /**
     * This region's census scale scores.
     */
    CENSUS("CENSUS"),
    /**
     * The census scale scores of this region's nations.
     */
    CENSUS_RANKS("CENSUSRANKS"),
    /**
     * Database id of the region.
     */
    DBID("DBID"),
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
     * The unique identifiers of this region's pinned dispatches. 
     */
    DISPATCHES("DISPATCHES"),
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
     * If this region is a frontier region or not.
     */
    FRONTIER("FRONTIER"),
    /**
     * The region's votes for the current General Assembly resolution.
     */
    GENERAL_ASSEMBLY_VOTE("GAVOTE"),
    /**
     * The region's governor. Returns '0' if none exists.
     */
    GOVERNOR("GOVERNOR"),
    /**
     * The region's governor's title. Empty if no regional governor exists.
     */
    GOVERNOR_TITLE("GOVERNORTITLE"),
    /**
     * List of the most recent of this region's happenings.
     */
    RECENT_HAPPENINGS("HAPPENINGS"),
    /**
     * List of history.
     */
    HISTORY("HISTORY"),
    /**
     * UNIX timestamp of when this region was last updated.
     */
    LAST_UPDATE("LASTUPDATE"),
    /**
     * UNIX timestamp of when this region was last majorly updated.
     */
    LAST_MAJOR_UPDATE("LASTMAJORUPDATE"),
    /**
     * UNIX timestamp of when this region was last minorly updated.
     */
    LAST_MINOR_UPDATE("LASTMINORUPDATE"),
    /**
     * The region's magnetism. See https://www.nationstates.net/page=faq#magnetism.
     */
    MAGNETISM("MAGNETISM"),
    /**
     * List of x (default: 10) most recent regional messages.
     */
    REGIONAL_MESSAGES("MESSAGES"),
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
     * The region's nations that are members of the World Assembly.
     */
    WORLD_ASSEMBLY_NATIONS("WANATIONS"),
    /**
     * The number of the region's nations that are members of the World Assembly.
     */
    NUMBER_OF_WORLD_ASSEMBLY_NATIONS("NUMWANATIONS"),
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
     * The nations that are allowed to send recruitment telegrams for the region.
     */
    RECRUITERS("RECRUITERS"),
    /**
     * The region's votes for the current Security Council resolution.
     */
    SECURITY_COUNCIL_VOTE("SCVOTE"),
    /**
     * The region's tags.
     */
    TAGS("TAGS"),
    /**
     * The World Assembly badges granted to this region by the Security Council.
     */
    WORLD_ASSEMBLY_BADGES("WABADGES"),
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
    RegionShard(String shardName) {
        this.shardName = shardName;
    }

    @Override
    public String shardName() {
        return shardName;
    }
}
