package com.github.agadar.nationstates.domain.region;

import com.github.agadar.nationstates.adapter.ColonSeparatedToSetAdapter;
import com.github.agadar.nationstates.enumerator.RegionalMessageStatus;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents a message on a regional message board.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POST")
public class RegionalMessage implements Comparable<RegionalMessage> {

    /**
     * The message's id
     */
    @XmlAttribute(name = "id")
    public long id;

    /**
     * UNIX timestamp of when this message was posted
     */
    @XmlElement(name = "TIMESTAMP")
    public long timestamp;

    /**
     * Name of the nation that posted the message
     */
    @XmlElement(name = "NATION")
    public String author = "";

    /**
     * The message's status. Can be any of the following:
     */
    @XmlElement(name = "STATUS")
    @XmlJavaTypeAdapter(RegionalMessageStatus.Adapter.class)
    public RegionalMessageStatus status = RegionalMessageStatus.NULL;

    /**
     * Name of the nation that suppressed this post. Only set if the post is
     * suppressed and done so by a non-moderator (status 1).
     */
    @XmlElement(name = "SUPPRESSOR")
    public String suppressor = "";

    /**
     * The number of likes this message received
     */
    @XmlElement(name = "LIKES")
    public int numberOfLikes;

    /**
     * List of nations that liked this message
     */
    @XmlElement(name = "LIKERS")
    @XmlJavaTypeAdapter(ColonSeparatedToSetAdapter.class)
    public Set<String> likedBy = new HashSet<String>();

    /**
     * The text of the message
     */
    @XmlElement(name = "MESSAGE")
    public String text = "";

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
