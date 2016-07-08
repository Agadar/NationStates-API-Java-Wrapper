package com.github.agadar.nsapi.domain.wa;

import com.github.agadar.nsapi.adapter.IntToCouncilAdapter;
import com.github.agadar.nsapi.enums.Council;
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
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESOLUTION")
public class Resolution 
{
    /** This resolution's category. */
    @XmlElement(name = "CATEGORY")
    public String Category;
    
    /** Which council this resolution was send to. */
    @XmlElement(name = "COUNCIL")
    @XmlJavaTypeAdapter(IntToCouncilAdapter.class)
    public Council Council;
    
    /** This resolution's id as it is known to the specific Council it's in. */
    @XmlElement(name = "COUNCILID")
    public int CouncilId;
    
    /** UNIX timestamp of when this proposal was created. */
    @XmlElement(name = "CREATED")
    public long CreatedOn;
    
    /** This resolution's textual content. */
    @XmlElement(name = "DESC")
    public String Text;
    
    /** UNIX timestamp of when this proposal was implemented, if at all. */
    @XmlElement(name = "IMPLEMENTED")
    public long ImplementedOn;
    
    /** This resolution's name. */
    @XmlElement(name = "NAME")
    public String Name;
    
    /** The option given for this resolution. Possible values depends on Category. */
    @XmlElement(name = "OPTION")
    public String Option;
    
    /** Name of the nation that created this resolution. */
    @XmlElement(name = "PROPOSED_BY")
    public String ProposedBy;
    
    /** This resolution's id as it is known to the World Assembly as a whole. */
    @XmlElement(name = "RESID")
    public int Id;
    
    /** Number of nations that voted FOR. */
    @XmlElement(name = "TOTAL_VOTES_FOR")
    public int VotesFor;
    
    /** Number of nations that voted AGAINST. */
    @XmlElement(name = "TOTAL_VOTES_AGAINST")
    public int VotesAgainst;
    
    /**
     * A log containing when what delegates voted, and what for, during the
     * resolution currently at vote.
     */
    @XmlElementWrapper(name = "DELLOG")
    @XmlElement(name = "ENTRY")
    public List<DelLogEntry> DelegateLog;
    
    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate
     * that voted FOR.
     */
    @XmlElementWrapper(name = "DELVOTES_FOR")
    @XmlElement(name = "DELEGATE")
    public List<DelLogEntry> DelegateVotesFor;
    
    /**
     * Same as DelegateLog, but only contains the LAST action for each delegate
     * that voted AGAINST.
     */
    @XmlElementWrapper(name = "DELVOTES_AGAINST")
    @XmlElement(name = "DELEGATE")
    public List<DelLogEntry> DelegateVotesAgainst;
    
    /**
     * A track record of the total FOR votes of the resolution currently at vote.
     */
    @XmlElementWrapper(name = "VOTE_TRACK_FOR")
    @XmlElement(name = "N")
    public List<Integer> VoteTrackFor;
        
    /**
     * A track record of the total AGAINST votes of the resolution currently at vote.
     */
    @XmlElementWrapper(name = "VOTE_TRACK_AGAINST")
    @XmlElement(name = "N")
    public List<Integer> VoteTrackAgainst;
}
