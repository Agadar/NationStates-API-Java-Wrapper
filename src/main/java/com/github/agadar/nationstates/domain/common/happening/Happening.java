package com.github.agadar.nationstates.domain.common.happening;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a generic happening.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EVENT")
public class Happening implements Comparable<Happening> {

    /**
     * Id of this happening. Not always available.
     */
    @XmlAttribute(name = "id")
    public long id;

    /**
     * UNIX timestamp of when this happening took place.
     */
    @XmlElement(name = "TIMESTAMP")
    public long timestamp;

    /**
     * Raw text description of the happening.
     */
    @XmlElement(name = "TEXT")
    public String description;

    public Happening() {
    }

    public Happening(long id, long timestamp, String description) {
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.description = description;
    }

    @Override
    public int compareTo(Happening o) {
        if (this.timestamp > o.timestamp) {
            return -1;
        } else if (this.timestamp < o.timestamp) {
            return 1;
        }
        return 0;
    }
}
