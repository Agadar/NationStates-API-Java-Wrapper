package com.github.agadar.nationstates.domain.region;

import com.github.agadar.nationstates.enumerator.Authority;

import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A regional officer.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "OFFICER")
public class Officer {

    /**
     * Name of the nation that is fulfilling this officer position.
     */
    @XmlElement(name = "NATION")
    public String nationName;

    /**
     * Name of the officer position.
     */
    @XmlElement(name = "OFFICE")
    public String officeName;

    /**
     * The authorities granted to this officer.
     */
    @XmlElement(name = "AUTHORITY")
    @XmlJavaTypeAdapter(Authority.Adapter.class)
    public Set<Authority> authorities;

    /**
     * UNIX timestamp of when this officer position was assigned.
     */
    @XmlElement(name = "TIME")
    public long assignedOn;

    /**
     * Name of the nation by which this officer position was assigned.
     */
    @XmlElement(name = "BY")
    public String assignedBy;

    /**
     * Order of this officer position in the regional factbook.
     */
    @XmlElement(name = "ORDER")
    public int order;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.nationName);
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
        final Officer other = (Officer) obj;
        return Objects.equals(this.nationName, other.nationName);
    }

}
