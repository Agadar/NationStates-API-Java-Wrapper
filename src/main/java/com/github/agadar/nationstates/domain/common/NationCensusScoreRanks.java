package com.github.agadar.nationstates.domain.common;

import java.util.SortedSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lists the census scores of a region's top 20 scoring nations, or the census
 * scores of the world's top 20 scoring nations.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSRANKS")
public class NationCensusScoreRanks {

    /**
     * The id of the scale
     */
    @XmlAttribute(name = "id")
    public int id;

    /**
     * The nation scores
     */
    @XmlElementWrapper(name = "NATIONS")
    @XmlElement(name = "NATION")
    public SortedSet<NationCensusScore> nations;

    @Override
    public int hashCode() {
        int hash = 5;
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
        final NationCensusScoreRanks other = (NationCensusScoreRanks) obj;
        return this.id == other.id;
    }

}
