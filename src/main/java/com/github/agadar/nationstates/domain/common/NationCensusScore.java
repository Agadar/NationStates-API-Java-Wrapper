package com.github.agadar.nationstates.domain.common;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * A nation's statistics in the census ranks.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class NationCensusScore implements Comparable<NationCensusScore> {

    /**
     * The nation's name
     */
    @XmlElement(name = "NAME")
    private String nationName = "";

    /**
     * The nation's regional or world-wide rank on the census scale
     */
    @XmlElement(name = "RANK")
    private int rank;

    /**
     * The nation's score on the scale
     */
    @XmlElement(name = "SCORE")
    private double score;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nationName);
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
        final NationCensusScore other = (NationCensusScore) obj;
        return Objects.equals(this.nationName, other.nationName);
    }

    @Override
    public int compareTo(NationCensusScore o) {

        if (this.rank != 0 && o.rank != 0) {
            if (this.rank > o.rank) {
                return 1;
            } else if (this.rank < o.rank) {
                return -1;
            }
            return 0;
        }

        if (this.score > o.score) {
            return -1;
        } else if (this.score < o.score) {
            return 1;
        }
        return 0;
    }

}
