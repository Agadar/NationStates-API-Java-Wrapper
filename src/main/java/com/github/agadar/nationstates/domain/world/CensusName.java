package com.github.agadar.nationstates.domain.world;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Name of the current or selected census.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUS")
public class CensusName {

    /**
     * Id of the census to which this name belong.
     */
    @XmlAttribute(name = "id")
    public int id;

    /**
     * Name of this census.
     */
    @XmlValue
    public String name;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final CensusName other = (CensusName) obj;
        return this.id == other.id;
    }

}
