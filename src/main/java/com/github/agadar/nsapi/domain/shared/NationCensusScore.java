package com.github.agadar.nsapi.domain.shared;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's statistics in the census ranks.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class NationCensusScore 
{
    /** The nation's name */
    @XmlElement(name = "NAME")
    public String NationName;
    
    /** The nation's regional or world-wide rank on the census scale */
    @XmlElement(name = "RANK")
    public String Rank;
    
    /** The nation's score on the scale */
    @XmlElement(name = "SCORE")
    public String Score;
}
