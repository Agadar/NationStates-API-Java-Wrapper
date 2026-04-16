package com.github.agadar.nationstates.domain.world;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * A world's descriptions of the current or selected census.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSDESC")
public class CensusDescriptions {

    /**
     * Id of the census to which these descriptions belong.
     */
    @XmlAttribute(name = "id")
    private int id;

    /**
     * Description for nations, e.g. 'The following nations have...'
     */
    @XmlElement(name = "NDESC")
    private String descriptionForNations = "";

    /**
     * Description for regions, e.g. 'The following regions have...'
     */
    @XmlElement(name = "RDESC")
    private String descriptionForRegions = "";

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
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
        final CensusDescriptions other = (CensusDescriptions) obj;
        return this.id == other.id;
    }

}
