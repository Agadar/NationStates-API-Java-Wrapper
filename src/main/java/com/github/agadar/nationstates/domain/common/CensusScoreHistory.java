package com.github.agadar.nationstates.domain.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entry in a CensusScore's history list.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POINT")
public class CensusScoreHistory implements Comparable<CensusScoreHistory> {

    /**
     * The UNIX timestamp of this record.
     */
    @XmlElement(name = "TIMESTAMP")
    public long timestamp;

    /**
     * What was scored that timestamp.
     */
    @XmlElement(name = "SCORE")
    public String score;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.timestamp ^ (this.timestamp >>> 32));
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
        final CensusScoreHistory other = (CensusScoreHistory) obj;
        return this.timestamp == other.timestamp;
    }

    @Override
    public int compareTo(CensusScoreHistory o) {
        if (this.timestamp > o.timestamp) {
            return -1;
        } else if (this.timestamp < o.timestamp) {
            return 1;
        }
        return 0;
    }

}
