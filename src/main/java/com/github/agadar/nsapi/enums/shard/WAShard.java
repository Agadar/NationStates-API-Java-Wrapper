package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for World Assembly requests. These shards have a 1:1
 * correspondence with the variable fields in WorldAssembly.java, except for
 * VoteTrack and DelegateVotes: VoteTrack fills the fields VoteTrackFor and
 * VoteTrackAgainst, and DelegateVotes fills the fields DelegateVotesFor and
 * DelegateVotesAgainst.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum WAShard implements Shard {

    /**
     * The number of member nations. Same for both councils.
     */
    NUMBER_OF_MEMBERS("NUMNATIONS"),
    /**
     * The number of delegates. Same for both councils.
     */
    NUMBER_OF_DELEGATES("NUMDELEGATES"),
    /**
     * The list of delegates. Same for both councils.
     */
    DELEGATES("DELEGATES"),
    /**
     * The list of member nations. Same for both councils.
     */
    MEMBERS("MEMBERS"),
    /**
     * Most recent happenings. Same for both councils.
     */
    RECENT_HAPPENINGS("HAPPENINGS"),
    /**
     * Most recent member log entries. Same for both councils.
     */
    RECENT_MEMBER_LOG("MEMBERLOG"),
    /**
     * Current proposed resolutions.
     */
    CURRENT_PROPOSALS("PROPOSALS"),
    /**
     * The current resolution at vote, or a specific one if an id is supplied.
     */
    RESOLUTION("RESOLUTION"),
    /**
     * A track record of the total votes of the resolution currently at vote.
     * Only works in conjunction with the Resolution shard.
     */
    VOTE_TRACK("VOTETRACK"),
    /**
     * A log containing when what delegates voted, and what for, during the
     * resolution currently at vote. Only works in conjunction with the
     * Resolution shard.
     */
    DELEGATE_LOG("DELLOG"),
    /**
     * Same as DelegateLog, but only contains the last action for each delegate.
     * Only works in conjunction with the Resolution shard.
     */
    DELEGATE_VOTES("DELVOTES"),
    /**
     * Brief description of the end result of the last proposed resolution.
     */
    LAST_RESOLUTION_RESULT("LASTRESOLUTION");

    /**
     * The underlying shard name
     */
    private final String shardName;

    /**
     * Instantiate a new entry with the given shard name.
     *
     * @param shardName the name of the underlying shard
     */
    private WAShard(String shardName) {
        this.shardName = shardName;
    }

    @Override
    public String shardName() {
        return shardName;
    }
}
