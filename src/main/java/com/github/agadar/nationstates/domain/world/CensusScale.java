package com.github.agadar.nationstates.domain.world;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.Getter;
import lombok.Setter;

/**
 * The scale of the current or selected census.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSSCALE")
public class CensusScale {

    /**
     * Id of this census scale.
     */
    @XmlAttribute(name = "id")
    private int id;

    /**
     * Scale name of this census.
     */
    @XmlValue
    private String scale = "";

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final CensusScale other = (CensusScale) obj;
        return this.id == other.id;
    }

}
