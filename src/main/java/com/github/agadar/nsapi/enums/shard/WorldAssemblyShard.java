package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for World Assembly requests. These shards have a 1:1
 * correspondence with the variable fields in WorldAssembly.java.
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public enum WorldAssemblyShard implements Shard
{
    /** The number of member nations. Same for both councils. */
    NumberOfMembers("NUMNATIONS"),
    /** The number of delegates. Same for both councils. */
    NumberOfDelegates("NUMDELEGATES"),
    /** The list of delegates. Same for both councils. */
    Delegates("DELEGATES"),
    /** The list of member nations. Same for both councils. */
    Members("MEMBERS"),
    /** Most recent happenings. Same for both councils. */
    RecentHappenings("HAPPENINGS"),
    /** Most recent member log entries. Same for both councils. */
    RecentMemberLog("MEMBERLOG"),
    /** Current proposed resolutions. */
    CurrentProposals("PROPOSALS"),
    /** The current resolution at vote, or a specific one if an id is supplied. */
    Resolution("RESOLUTION"),
    // The following shards have to be implemented once a resolution is at vote,
    // so that we know what kind of XML they yield.
//    VOTETRACK,
//    DELLOG,
//    DELVOTES,
    /** Brief description of the end result of the last proposed resolution. */
    LastResolutionResult("LASTRESOLUTION");

    /** The underlying shard name */
    private final String shardName;

    /**
     * Instantiate a new entry with the given shard name.
     *
     * @param shardName the name of the underlying shard
     */
    private WorldAssemblyShard(String shardName)
    {
        this.shardName = shardName;
    }

    @Override
    public String shardName()
    {
        return shardName;
    }
}
