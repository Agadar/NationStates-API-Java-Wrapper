package com.github.agadar.nsapi.domain.shared;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a happening.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EVENT")
public class Happening {

    /**
     * Id of this happening.
     */
    @XmlAttribute(name = "id")
    public long Id;

    /**
     * UNIX timestamp of when this happening took place
     */
    @XmlElement(name = "TIMESTAMP")
    public long Timestamp;

    /**
     * Description of the happening
     */
    @XmlElement(name = "TEXT")
    public String Description;
}
