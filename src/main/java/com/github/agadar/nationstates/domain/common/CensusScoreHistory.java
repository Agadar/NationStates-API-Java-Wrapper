package com.github.agadar.nationstates.domain.common;

import java.time.Instant;

import com.github.agadar.nationstates.adapter.StringToInstantAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

/**
 * Entry in a CensusScore's history list.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POINT")
public class CensusScoreHistory implements Comparable<CensusScoreHistory> {

    /**
     * The UNIX timestamp of this record.
     */
    @XmlElement(name = "TIMESTAMP")
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant timestamp;

    /**
     * What was scored that timestamp.
     */
    @XmlElement(name = "SCORE")
    private String score = "";

    @Override
    public int hashCode() {
        int hash = 5;
        long epochSeconds = this.timestamp.getEpochSecond();
        hash = 71 * hash + (int) (epochSeconds ^ (epochSeconds >>> 32));
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
        return this.timestamp.getEpochSecond() == other.timestamp.getEpochSecond();
    }

    @Override
    public int compareTo(CensusScoreHistory o) {
    	var epochSeconds1 = timestamp.getEpochSecond();
    	var epochSeconds2 = o.timestamp.getEpochSecond();
    	
        if (epochSeconds1 > epochSeconds2) {
            return -1;
        } else if (epochSeconds1 < epochSeconds2) {
            return 1;
        }
        return 0;
    }

}
