package com.github.agadar.nationstates.domain.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Representation of a nation's, region's, or the world's census statistics.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SCALE")
public class CensusScore {

    /**
     * The id of the scale. Supplied by default.
     */
    @XmlAttribute(name = "id")
    private int id;

    /**
     * The nation's individual score, or the region's or world's average score.
     * Supplied by default.
     */
    @XmlElement(name = "SCORE")
    private String score = "";

    /**
     * The nation's or region's world-wide rank on the scale. e.g. '334' means
     * '334th'. Inapplicable for world. Supplied by default.
     */
    @XmlElement(name = "RANK")
    private int worldRank;

    /**
     * The nation's regional rank on the scale. e.g. '334' means '334th'.
     * Inapplicable for regions and world. Supplied by default.
     */
    @XmlElement(name = "RRANK")
    private int regionalRank;

    /**
     * The nation's or region's world-wide rank on the scale, as a percentage. e.g.
     * '15' means 'Top 15%'. Inapplicable for world. Not supplied by default.
     */
    @XmlElement(name = "PRANK")
    private int worldRankPercentage;

    /**
     * The nation's regional rank on the scale, as a percentage. e.g. '15' means
     * 'Top 15%'. Inapplicable for regions and world. Not supplied by default.
     */
    @XmlElement(name = "PRRANK")
    private int regionalRankPercentage;

    /**
     * The history of this census score. If this is requested, all other fields save
     * for Id will not be retrieved.
     */
    @XmlElement(name = "POINT")
    private List<CensusScoreHistory> history = new ArrayList<CensusScoreHistory>();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CensusScore other = (CensusScore) obj;
        return this.id == other.id;
    }

}
