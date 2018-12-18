package com.github.agadar.nationstates.domain.region;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.ColonSeparatedToLinkedHashSetAdapter;
import com.github.agadar.nationstates.enumerator.RegionalMessageStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a message on a regional message board.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POST")
public class RegionalMessage implements Comparable<RegionalMessage> {

    /**
     * The message's id
     */
    @XmlAttribute(name = "id")
    private long id;

    /**
     * UNIX timestamp of when this message was posted
     */
    @XmlElement(name = "TIMESTAMP")
    private long timestamp;

    /**
     * Name of the nation that posted the message
     */
    @XmlElement(name = "NATION")
    private String author = "";

    /**
     * The message's status. Can be any of the following:
     */
    @XmlElement(name = "STATUS")
    @XmlJavaTypeAdapter(RegionalMessageStatus.Adapter.class)
    private RegionalMessageStatus status = RegionalMessageStatus.NULL;

    /**
     * Name of the nation that suppressed this post. Only set if the post is
     * suppressed and done so by a non-moderator (status 1).
     */
    @XmlElement(name = "SUPPRESSOR")
    private String suppressor = "";

    /**
     * The number of likes this message received
     */
    @XmlElement(name = "LIKES")
    private int numberOfLikes;

    /**
     * List of nations that liked this message
     */
    @XmlElement(name = "LIKERS")
    @XmlJavaTypeAdapter(ColonSeparatedToLinkedHashSetAdapter.class)
    private Collection<String> likedBy = new LinkedHashSet<String>();

    /**
     * The text of the message
     */
    @XmlElement(name = "MESSAGE")
    private String text = "";

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final RegionalMessage other = (RegionalMessage) obj;
        return this.id == other.id;
    }

    @Override
    public int compareTo(RegionalMessage o) {
        if (this.timestamp > o.timestamp) {
            return -1;
        } else if (this.timestamp < o.timestamp) {
            return 1;
        }
        return 0;
    }

}
