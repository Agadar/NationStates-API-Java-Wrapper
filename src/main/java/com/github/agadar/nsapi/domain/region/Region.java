package com.github.agadar.nsapi.domain.region;

import com.github.agadar.nsapi.adapter.CharsToAuthNamesAdapter;
import com.github.agadar.nsapi.adapter.ColonSeparatedToListAdapter;
import com.github.agadar.nsapi.domain.shared.CensusScore;
import com.github.agadar.nsapi.domain.shared.Happening;
import com.github.agadar.nsapi.domain.shared.NationCensusScoreRanks;
import com.github.agadar.nsapi.domain.shared.Poll;
import com.github.agadar.nsapi.domain.shared.ZombieInfo;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a region. This class's fields have a 1:1 correspondence 
 * with the shards in RegionShard.java.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGION")
public class Region 
{
    /** This region's census scale scores. */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public List<CensusScore> Census;
    
    /** The census scale scores of this region's nations. */
    @XmlElement(name = "CENSUSRANKS")
    public NationCensusScoreRanks CensusRanks;
    
    /** 
     * The name of the nation that is the region's world assembly delegate.
     * Returns '0' if no delegate exists.
     */  
    @XmlElement(name = "DELEGATE")
    public String Delegate;
    
    /** The authorities granted to the region's world assembly delegate. */
    @XmlElement(name = "DELEGATEAUTH")
    @XmlJavaTypeAdapter(CharsToAuthNamesAdapter.class)
    public List<String> DelegateAuthorities;
    
    /** The number of endorsements the region's world assembly delegate has. */
    @XmlElement(name = "DELEGATEVOTES")
    public int DelegateEndorsements;
    
    /** The region's embassies. */
    @XmlElementWrapper(name = "EMBASSIES")
    @XmlElement(name = "EMBASSY")
    public List<Embassy> Embassies;
    
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
    @XmlElement(name = "EMBASSYRMB")
    public String EmbassiesRMBPerms;
    
    /** The complete mark-up of the regional factbook. */
    @XmlElement(name = "FACTBOOK")
    public String Factbook;
    
    /** URL to the region's flag image. */
    @XmlElement(name = "FLAG")
    public String FlagUrl;
    
    /** Description of when this region was founded. */
    @XmlElement(name = "FOUNDED")
    public String Founded;
    
    /** The name of the region's founding nation. Returns '0' if none exists. */
    @XmlElement(name = "FOUNDER")
    public String Founder;
    
    /** The authorities granted to the region's founder. */
    @XmlElement(name = "FOUNDERAUTH")
    @XmlJavaTypeAdapter(CharsToAuthNamesAdapter.class)
    public List<String> FounderAuthorities;
    
    /** The region's votes for the current General Assembly resolution. */
    @XmlElement(name = "GAVOTE")
    public WorldAssemblyVote GeneralAssemblyVote;
    
    /** List of the most recent of this nation's happenings. */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public List<Happening> RecentHappenings;
    
    /** List of the most recent history. Not sure what these represent. */
    @XmlElementWrapper(name = "HISTORY")
    @XmlElement(name = "EVENT")
    public List<Happening> History;
    
    /** List of 10 most recent regional messages. */
    @XmlElementWrapper(name = "MESSAGES")
    @XmlElement(name = "POST")
    public List<RegionalMessage> RegionalMessages;
    
    /** The region's name. */
    @XmlElement(name = "NAME")
    public String Name;
    
    /** List of nations that inhabit this region. */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(ColonSeparatedToListAdapter.class)
    public List<String> NationNames;
    
    /** The number of nations that inhabit this region. */
    @XmlElement(name = "NUMNATIONS")
    public int NumberOfNations;
    
    /** List of regional officers. */
    @XmlElementWrapper(name = "OFFICERS")
    @XmlElement(name = "OFFICER")
    public List<Officer> Officers;
    
    /** The regional poll that is currently being conducted. */
    @XmlElement(name = "POLL")
    public Poll CurrentPoll;
    
    /** The region's power. */
    @XmlElement(name = "POWER")
    public String Power;
    
    /** The region's votes for the current Security Council resolution. */
    @XmlElement(name = "SCVOTE")
    public WorldAssemblyVote SecurityCouncilVote;
    
    /** The region's tags. */
    @XmlElementWrapper(name = "TAGS")
    @XmlElement(name = "TAG")
    public List<String> Tags;
    
    /** This region's statistics of the current or last zombie event. */
    @XmlElement(name = "ZOMBIE")
    public ZombieInfo ZombieInfo;
}
