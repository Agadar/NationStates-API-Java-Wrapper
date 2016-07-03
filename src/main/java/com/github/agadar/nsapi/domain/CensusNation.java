package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's statistics in the census ranks.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class CensusNation 
{
    /** The nation's name */
    @XmlAttribute(name = "NAME")
    public String NationName;
    
    /** The nation's regional or world-wide rank on the census scale */
    @XmlAttribute(name = "RANK")
    public String Rank;
    
    /** The nation's score on the scale */
    @XmlAttribute(name = "SCORE")
    public double Score;
}
