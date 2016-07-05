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
 * Representation of the World Assembly. This class's fields have a 1:1 correspondence 
 * with the shards in WorldAssemblyShard.java.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WA")
public class WorldAssembly 
{
    /** The number of member nations. Same for both councils. */
    @XmlElement(name = "NUMNATIONS")
    public int NumberOfMembers;
    
    /** The number of delegates. Same for both councils. */
    @XmlElement(name = "NUMDELEGATES")
    public int NumberOfDelegates;
    
    /** The list of delegates. Same for both councils. */
    @XmlElement(name = "DELEGATES")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> Delegates;
    
    /** The list of member nations. Same for both councils. */
    @XmlElement(name = "MEMBERS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> Members;
    
    /** Most recent happenings. Same for both councils. */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public List<Happening> RecentHappenings;
    
    /** Most recent member log entries. Same for both councils. */
    @XmlElementWrapper(name = "MEMBERLOG")
    @XmlElement(name = "EVENT")
    public List<Happening> RecentMemberLog;
    
    /** Current proposed resolutions. */
    @XmlElementWrapper(name = "PROPOSALS")
    @XmlElement(name = "PROPOSAL")
    public List<Proposal> CurrentProposals;
    
    /** The current resolution at vote, or a specific one if an id is supplied. */
    @XmlElement(name = "RESOLUTION")
    public Resolution Resolution;
    
    /** Brief description of the end result of the last proposed resolution. */
    @XmlElement(name = "LASTRESOLUTION")
    public String LastResolutionResult;
}
