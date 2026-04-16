package com.github.agadar.nationstates.domain.region;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * A nation's total RMB likes given over a previously specified period of time.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class MostLikedRank implements Comparable<MostLikedRank> {

    /**
     * Name of the nation.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * Number of likes given by this nation.
     */
    @XmlElement(name = "LIKED")
    private int liked;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
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
        final MostLikedRank other = (MostLikedRank) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int compareTo(MostLikedRank o) {
        if (this.liked > o.liked) {
            return -1;
        } else if (this.liked < o.liked) {
            return 1;
        }
        return 0;
    }

}
