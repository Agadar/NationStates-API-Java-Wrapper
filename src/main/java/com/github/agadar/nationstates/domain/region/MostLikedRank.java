package com.github.agadar.nationstates.domain.region;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's total RMB likes given over a previously specified period of time.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class MostLikedRank implements Comparable<MostLikedRank> {

    /**
     * Name of the nation.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * Number of likes given by this nation.
     */
    @XmlElement(name = "LIKED")
    public int liked;

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
