package com.github.agadar.nationstates.domain.common;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * A regional poll.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POLL")
public class Poll {

    /**
     * The poll's id.
     */
    @XmlAttribute(name = "id")
    private int id;

    /**
     * The poll's title.
     */
    @XmlElement(name = "TITLE")
    private String title = "";

    /**
     * The poll's description.
     */
    @XmlElement(name = "TEXT")
    private String description = "";

    /**
     * The name of the region the poll is in.
     */
    @XmlElement(name = "REGION")
    private String region = "";

    /**
     * UNIX timestamp on which the poll opened.
     */
    @XmlElement(name = "START")
    private long openedOn;

    /**
     * UNIX timestamp on which the poll will close.
     */
    @XmlElement(name = "STOP")
    private long closingOn;

    /**
     * Name of the nation that opened the poll.
     */
    @XmlElement(name = "AUTHOR")
    private String authorName = "";

    /**
     * The possible options for this poll.
     */
    @XmlElementWrapper(name = "OPTIONS")
    @XmlElement(name = "OPTION")
    private Collection<PollOption> options = new LinkedHashSet<PollOption>();

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Poll other = (Poll) obj;
        return this.id == other.id;
    }

}
