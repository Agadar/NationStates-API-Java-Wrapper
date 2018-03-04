package com.github.agadar.nationstates.domain.region;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's total RMB posts over a previously specified period of time.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class MostPostsRank implements Comparable<MostPostsRank> {

    /**
     * Name of the nation.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * Number of posts made by this nation.
     */
    @XmlElement(name = "POSTS")
    public int posts;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final MostPostsRank other = (MostPostsRank) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int compareTo(MostPostsRank o) {
        if (this.posts > o.posts) {
            return -1;
        } else if (this.posts < o.posts) {
            return 1;
        }
        return 0;
    }

}
