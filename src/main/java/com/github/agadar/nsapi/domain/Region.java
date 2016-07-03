package com.github.agadar.nsapi.domain;

import com.github.agadar.nsapi.adapter.AuthCodesToAuthNamesAdapter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a region.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGION")
public class Region 
{
    /** This region's census scale scores */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public List<CensusScale> Census;
    
    /** The census scale scores of this region's nations */
    @XmlElement(name = "CENSUSRANKS")
    public CensusRanks CensusRanks;
    
    /** 
     * The name of the nation that is the region's world assembly delegate.
     * Returns '0' if no delegate exists.
     */  
    @XmlElement(name = "DELEGATE")
    public String Delegate;
    
    /** The authorities granted to the region's world assembly delegate */
    @XmlElement(name = "DELEGATEAUTH")
    @XmlJavaTypeAdapter(AuthCodesToAuthNamesAdapter.class)
    public List<String> DelegateAuthorities;
    
    /** The number of endorsements the region's world assembly delegate has */
    @XmlElement(name = "DELEGATEVOTES")
    public int DelegateEndorsements;
    
    /** The region's embassies */
    @XmlElementWrapper(name = "EMBASSIES")
    @XmlElement(name = "EMBASSY")
    public List<Embassy> Embassies;
    
    /** 
     * Regional Message Board permissions for regions with which this region exchanges embassies.
     * Descriptions of the codes:
     * 
     * '0': No embassy posting;
     * 'con': Delegates & Founders of embassy regions;
     * 'off': Officers of embassy regions;
     * 'com': Officers of embassy regions with Communications authority;
     * 'all': All residents of embassy regions.
     */
    @XmlElement(name = "EMBASSYRMB")
    public String EmbassiesRMBPerms;
    
    /** The complete mark-up of the regional factbook */
    @XmlElement(name = "FACTBOOK")
    public String Factbook;
    
    /** URL to the region's flag image */
    @XmlElement(name = "FLAG")
    public String FlagUrl;
    
    /** The name of the region's founding nation. Returns '0' if none exists. */
    @XmlElement(name = "FOUNDER")
    public String Founder;
    
    /** The authorities granted to the region's founder */
    @XmlElement(name = "FOUNDERAUTH")
    @XmlJavaTypeAdapter(AuthCodesToAuthNamesAdapter.class)
    public List<String> FounderAuthorities;
}
