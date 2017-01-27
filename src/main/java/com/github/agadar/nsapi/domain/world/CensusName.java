package com.github.agadar.nsapi.domain.world;

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
    public int Id;

    /**
     * Name of this census.
     */
    @XmlValue
    public String Name;
}
