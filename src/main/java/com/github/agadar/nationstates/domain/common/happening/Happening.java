package com.github.agadar.nationstates.domain.common.happening;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a generic happening.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EVENT")
public class Happening implements Comparable<Happening> {

    /**
     * Id of this happening. Not always available.
     */
    @XmlAttribute(name = "id")
    private long id;

    /**
     * UNIX timestamp of when this happening took place.
     */
    @XmlElement(name = "TIMESTAMP")
    private long timestamp;

    /**
     * Raw text description of the happening.
     */
    @XmlElement(name = "TEXT")
    private String description;

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
