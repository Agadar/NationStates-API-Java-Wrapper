package com.github.agadar.nationstates.domain.region;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * A nation's total RMB likes received over a previously specified period of
 * time.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class MostLikesRank implements Comparable<MostLikesRank> {

    /**
     * Name of the nation.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * Number of likes received by this nation.
     */
    @XmlElement(name = "LIKES")
    private int likes;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.name);
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
        final MostLikesRank other = (MostLikesRank) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int compareTo(MostLikesRank o) {
        if (this.likes > o.likes) {
            return -1;
        } else if (this.likes < o.likes) {
            return 1;
        }
        return 0;
    }

}
