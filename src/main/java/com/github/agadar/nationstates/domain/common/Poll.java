package com.github.agadar.nationstates.domain.common;

import java.time.Instant;
import java.util.LinkedHashSet;

import com.github.agadar.nationstates.adapter.StringToInstantAdapter;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant openedOn;

    /**
     * UNIX timestamp on which the poll will close.
     */
    @XmlElement(name = "STOP")
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant closingOn;

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
    private LinkedHashSet<PollOption> options = new LinkedHashSet<>();

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
