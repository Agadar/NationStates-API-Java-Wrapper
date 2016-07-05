package com.github.agadar.nsapi.domain.shared;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a nation's, region's, or the world's census statistics.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SCALE")
public class CensusScore 
{
    /** The id of the scale. */
    @XmlAttribute(name = "id")
    public int Id;
    
    /** The nation's individual score, or the region's or world's average score. */
    @XmlElement(name = "SCORE")
    public double Score;
    
    /** The nation's or region's world-wide rank on the scale. Inapplicable for world. */
    @XmlElement(name = "RANK")
    public int WorldRank;
    
    /** The nation's regional rank on the scale. Inapplicable for regions and world. */ 
    @XmlElement(name = "RRANK")
    public int RegionalRank;
}
