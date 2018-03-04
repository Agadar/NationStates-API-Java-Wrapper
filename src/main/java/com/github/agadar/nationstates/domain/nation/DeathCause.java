package com.github.agadar.nationstates.domain.nation;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Representation of a cause of death.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CAUSE")
public class DeathCause {

    /**
     * Description of the death cause
     */
    @XmlAttribute(name = "type")
    public String description;

    /**
     * The percentage of the population that dies of this death cause
     */
    @XmlValue
    public double percentage;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.description);
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
        final DeathCause other = (DeathCause) obj;
        return Objects.equals(this.description, other.description);
    }

}
