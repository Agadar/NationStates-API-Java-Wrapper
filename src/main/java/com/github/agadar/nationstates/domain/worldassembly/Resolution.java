package com.github.agadar.nationstates.domain.worldassembly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.enumerator.Council;

import lombok.Getter;
import lombok.Setter;

/**
 * A resolution by either of the World Assembly councils.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESOLUTION")
public class Resolution {

    /**
     * This resolution's category.
     */
    @XmlElement(name = "CATEGORY")
    private String category = "";

    /**
     * Which council this resolution was send to. Has no value if this resolution is
     * currently at vote.
     */
    @XmlElement(name = "COUNCIL")
    @XmlJavaTypeAdapter(Council.Adapter.class)
    private Council council = Council.GENERAL_ASSEMBLY;

    /**
     * This resolution's id as it is known to the specific Council it's in. Has no
     * value if this resolution is currently at vote.
     */
    @XmlElement(name = "COUNCILID")
    private int councilId;

    /**
     * UNIX timestamp of when this proposal was created.
     */
    @XmlElement(name = "CREATED")
    private long createdOn;

    /**
     * This resolution's textual content.
     */
    @XmlElement(name = "DESC")
    private String text = "";

    /**
     * UNIX timestamp of when this proposal was implemented.
     */
    @XmlElement(name = "IMPLEMENTED")
    private long implementedOn;

    /**
     * This resolution's name.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * The option given for this resolution. Possible values depends on Category.
     */
    @XmlElement(name = "OPTION")
    private String option = "";

    /**
     * UNIX timestamp of when this resolution was promoted.
     */
    @XmlElement(name = "PROMOTED")
    private long promotedOn;

    /**
     * Name of the nation that created this resolution.
     */
    @XmlElement(name = "PROPOSED_BY")
    private String proposedBy = "";

    /**
     * The council-specific id of the resolution that repealed this resolution.
     */
    @XmlElement(name = "REPEALED_BY")
    private int repealedByCouncilId;

    /**
     * The id of the resolution that repealed this resolution.
     */
    @XmlElement(name = "REPEALED")
    private int repealedById;

    /**
     * The council-specific id of the resolution repealed by this resolution.
     */
    @XmlElement(name = "REPEALS_COUNCILID")
    private int repealsCouncilId;

    /**
     * The id of the resolution repealed by this resolution.
     */
    @XmlElement(name = "REPEALS_RESID")
    private int repealsId;

    /**
     * This resolution's id as it is known to the World Assembly as a whole. Has no
     * value if this resolution is currently at vote.
     */
    @XmlElement(name = "RESID")
    private int id;

    /**
     * Total number of nations that voted FOR.
     */
    @XmlElement(name = "TOTAL_NATIONS_FOR")
    private int nationsFor;

    /**
     * Total number of nations that voted AGAINST.
     */
    @XmlElement(name = "TOTAL_NATIONS_AGAINST")
    private int nationsAgainst;

    /**
     * Total number of FOR votes.
     */
    @XmlElement(name = "TOTAL_VOTES_FOR")
    private int votesFor;

    /**
     * Total number of AGAINST votes.
     */
    @XmlElement(name = "TOTAL_VOTES_AGAINST")
    private int votesAgainst;

    /**
     * A log containing when what delegates voted, and what for, during the
     * resolution currently at vote.
     */
    @XmlElementWrapper(name = "DELLOG")
    @XmlElement(name = "ENTRY")
    private Collection<DelegateLogsEntry> delegateLog = new LinkedHashSet<DelegateLogsEntry>();

    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate that
     * voted FOR.
     */
    @XmlElementWrapper(name = "DELVOTES_FOR")
    @XmlElement(name = "DELEGATE")
    private Collection<DelegateLogsEntry> delegateVotesFor = new LinkedHashSet<DelegateLogsEntry>();

    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate that
     * voted AGAINST.
     */
    @XmlElementWrapper(name = "DELVOTES_AGAINST")
    @XmlElement(name = "DELEGATE")
    private Collection<DelegateLogsEntry> delegateVotesAgainst = new LinkedHashSet<DelegateLogsEntry>();

    /**
     * A track record of the total FOR votes of the resolution currently at vote.
     */
    @XmlElementWrapper(name = "VOTE_TRACK_FOR")
    @XmlElement(name = "N")
    private List<Integer> voteTrackFor = new ArrayList<Integer>();

    /**
     * A track record of the total AGAINST votes of the resolution currently at
     * vote.
     */
    @XmlElementWrapper(name = "VOTE_TRACK_AGAINST")
    @XmlElement(name = "N")
    private List<Integer> voteTrackAgainst = new ArrayList<Integer>();
}
