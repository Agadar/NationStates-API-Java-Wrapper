package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a nation's or region's census scale statistics.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SCALE")
public class CensusScale 
{
    /** The id of the scale */
    @XmlAttribute(name = "id")
    public int Id;
    
    /** The nation's individual score or the region's average score on the scale */
    @XmlElement(name = "SCORE")
    public double Score;
    
    /** The nation's or region's world-wide rank on the scale */
    @XmlElement(name = "RANK")
    public int WorldRank;
    
    /** The nation's regional rank on the scale. Not used for regions */ 
    @XmlElement(name = "RRANK")
    public int RegionalRank;
}
