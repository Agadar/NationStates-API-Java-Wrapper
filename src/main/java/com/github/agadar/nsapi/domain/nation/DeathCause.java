package com.github.agadar.nsapi.domain.nation;

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
    public String Description;

    /**
     * The percentage of the population that dies of this death cause
     */
    @XmlValue
    public double Percentage;
}
