package com.github.agadar.nsapi.domain.shared;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a nation's, region's, or the world's census statistics.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SCALE")
public class CensusScore 
{
    /** The id of the scale. Supplied by default. */
    @XmlAttribute(name = "id")
    public int Id;
    
    /** 
     * The nation's individual score, or the region's or world's average score.
     * Supplied by default.
     */ 
    @XmlElement(name = "SCORE")
    public String Score;
    
    /** 
     * The nation's or region's world-wide rank on the scale. e.g. '334' means 
     * '334th'. Inapplicable for world. Supplied by default.
     */
    @XmlElement(name = "RANK")
    public int WorldRank;
    
    /** 
     * The nation's regional rank on the scale. e.g. '334' means '334th'. 
     * Inapplicable for regions and world. Supplied by default.
     */ 
    @XmlElement(name = "RRANK")
    public int RegionalRank;
    
    /**
     * The nation's or region's world-wide rank on the scale, as a percentage.
     * e.g. '15' means 'Top 15%'. Inapplicable for world. Not supplied by default.
     */
    @XmlElement(name = "PRANK")
    public int WorldRankPercentage;
    
    /**
     * The nation's regional rank on the scale, as a percentage. e.g. '15' means 
     * 'Top 15%'. Inapplicable for regions and world. Not supplied by default.
     */
    @XmlElement(name = "PRRANK")
    public int RegionalRankPercentage;
    
    /**
     * The history of this census score. If this is requested, all other
     * fields save for Id will not be retrieved.
     */
    @XmlElement(name = "POINT")
    public List<CensusScoreHistory> History;
}
