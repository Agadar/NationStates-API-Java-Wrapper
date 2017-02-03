package com.github.agadar.nsapi.domain.wa;

import com.github.agadar.nsapi.adapter.CommaSeparatedToListAdapter;
import com.github.agadar.nsapi.domain.shared.Happening;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of the World Assembly. This class's fields have a 1:1
 * correspondence with the shards in WorldAssemblyShard.java, except for
 * VoteTrackFor, VoteTrackAgainst, DelegateVotesFor, and DelegateVotesAgainst:
 * the former two are both filled by the VoteTrack shard, and the latter two are
 * filled by the DelegateVotes shard.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WA")
public class WorldAssembly {

    /**
     * The number of member nations. Same for both councils.
     */
    @XmlElement(name = "NUMNATIONS")
    public int numberOfMembers;

    /**
     * The number of delegates. Same for both councils.
     */
    @XmlElement(name = "NUMDELEGATES")
    public int numberOfDelegates;

    /**
     * The list of delegates. Same for both councils.
     */
    @XmlElement(name = "DELEGATES")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> delegates;

    /**
     * The list of member nations. Same for both councils.
     */
    @XmlElement(name = "MEMBERS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> members;

    /**
     * Most recent happenings. Same for both councils.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public List<Happening> recentHappenings;

    /**
     * Most recent member log entries. Same for both councils.
     */
    @XmlElementWrapper(name = "MEMBERLOG")
    @XmlElement(name = "EVENT")
    public List<Happening> recentMemberLog;

    /**
     * Current proposed resolutions.
     */
    @XmlElementWrapper(name = "PROPOSALS")
    @XmlElement(name = "PROPOSAL")
    public List<Proposal> currentProposals;

    /**
     * The current resolution at vote, or a specific one if an id is supplied.
     */
    @XmlElement(name = "RESOLUTION")
    public Resolution resolution;

    /**
     * Brief description of the end result of the last proposed resolution.
     */
    @XmlElement(name = "LASTRESOLUTION")
    public String lastResolutionResult;

    /**
     * A log containing when what delegates voted, and what for, during the
     * resolution currently at vote.
     *
     * @return log containing when what delegates voted, and what for
     */
    public List<DelegateLogsEntry> delegateLog() {
        return (resolution == null) ? null : resolution.delegateLog;
    }

    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate
     * that voted FOR.
     *
     * @return log containing when what delegates voted
     */
    public List<DelegateLogsEntry> delegateVotesFor() {
        return (resolution == null) ? null : resolution.delegateVotesFor;
    }

    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate
     * that voted AGAINST.
     *
     * @return log containing when what delegates voted
     */
    public List<DelegateLogsEntry> delegateVotesAgainst() {
        return (resolution == null) ? null : resolution.delegateVotesAgainst;
    }

    /**
     * A track record of the total FOR votes of the resolution currently at
     * vote.
     *
     * @return track record of the total FOR votes
     */
    public List<Integer> voteTrackFor() {
        return (resolution == null) ? null : resolution.voteTrackFor;
    }

    /**
     * A track record of the total AGAINST votes of the resolution currently at
     * vote.
     *
     * @return track record of the total AGAINST votes
     */
    public List<Integer> voteTrackAgainst() {
        return (resolution == null) ? null : resolution.voteTrackAgainst;
    }
}
