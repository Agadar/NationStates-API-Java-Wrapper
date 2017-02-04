package com.github.agadar.nationstates.domain.worldassembly;

import com.github.agadar.nationstates.enumerator.Council;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A resolution by either of the World Assembly councils.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESOLUTION")
public class Resolution {

    /**
     * This resolution's category.
     */
    @XmlElement(name = "CATEGORY")
    public String category;

    /**
     * Which council this resolution was send to. Has no value if this
     * resolution is currently at vote.
     */
    @XmlElement(name = "COUNCIL")
    @XmlJavaTypeAdapter(Council.Adapter.class)
    public Council council;

    /**
     * This resolution's id as it is known to the specific Council it's in. Has
     * no value if this resolution is currently at vote.
     */
    @XmlElement(name = "COUNCILID")
    public int councilId;

    /**
     * UNIX timestamp of when this proposal was created.
     */
    @XmlElement(name = "CREATED")
    public long createdOn;

    /**
     * This resolution's textual content.
     */
    @XmlElement(name = "DESC")
    public String text;

    /**
     * UNIX timestamp of when this proposal was implemented.
     */
    @XmlElement(name = "IMPLEMENTED")
    public long implementedOn;

    /**
     * This resolution's name.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * The option given for this resolution. Possible values depends on
     * Category.
     */
    @XmlElement(name = "OPTION")
    public String option;

    /**
     * UNIX timestamp of when this resolution was promoted.
     */
    @XmlElement(name = "PROMOTED")
    public long promotedOn;

    /**
     * Name of the nation that created this resolution.
     */
    @XmlElement(name = "PROPOSED_BY")
    public String proposedBy;

    /**
     * The council-specific id of the resolution that repealed this resolution.
     */
    @XmlElement(name = "REPEALED_BY")
    public int repealedByCouncilId;

    /**
     * The id of the resolution that repealed this resolution.
     */
    @XmlElement(name = "REPEALED")
    public int repealedById;

    /**
     * The council-specific id of the resolution repealed by this resolution.
     */
    @XmlElement(name = "REPEALS_COUNCILID")
    public int repealsCouncilId;

    /**
     * The id of the resolution repealed by this resolution.
     */
    @XmlElement(name = "REPEALS_RESID")
    public int repealsId;

    /**
     * This resolution's id as it is known to the World Assembly as a whole. Has
     * no value if this resolution is currently at vote.
     */
    @XmlElement(name = "RESID")
    public int id;

    /**
     * Total number of nations that voted FOR.
     */
    @XmlElement(name = "TOTAL_NATIONS_FOR")
    public int nationsFor;

    /**
     * Total number of nations that voted AGAINST.
     */
    @XmlElement(name = "TOTAL_NATIONS_AGAINST")
    public int nationsAgainst;

    /**
     * Total number of FOR votes.
     */
    @XmlElement(name = "TOTAL_VOTES_FOR")
    public int votesFor;

    /**
     * Total number of AGAINST votes.
     */
    @XmlElement(name = "TOTAL_VOTES_AGAINST")
    public int votesAgainst;

    /**
     * A log containing when what delegates voted, and what for, during the
     * resolution currently at vote.
     */
    @XmlElementWrapper(name = "DELLOG")
    @XmlElement(name = "ENTRY")
    public List<DelegateLogsEntry> delegateLog;

    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate
     * that voted FOR.
     */
    @XmlElementWrapper(name = "DELVOTES_FOR")
    @XmlElement(name = "DELEGATE")
    public List<DelegateLogsEntry> delegateVotesFor;

    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate
     * that voted AGAINST.
     */
    @XmlElementWrapper(name = "DELVOTES_AGAINST")
    @XmlElement(name = "DELEGATE")
    public List<DelegateLogsEntry> delegateVotesAgainst;

    /**
     * A track record of the total FOR votes of the resolution currently at
     * vote.
     */
    @XmlElementWrapper(name = "VOTE_TRACK_FOR")
    @XmlElement(name = "N")
    public List<Integer> voteTrackFor;

    /**
     * A track record of the total AGAINST votes of the resolution currently at
     * vote.
     */
    @XmlElementWrapper(name = "VOTE_TRACK_AGAINST")
    @XmlElement(name = "N")
    public List<Integer> voteTrackAgainst;
}
