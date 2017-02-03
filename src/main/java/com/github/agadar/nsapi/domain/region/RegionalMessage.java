package com.github.agadar.nsapi.domain.region;

import com.github.agadar.nsapi.adapter.ColonSeparatedToListAdapter;
import com.github.agadar.nsapi.adapter.IntToRegionalMessageStatusAdapter;
import com.github.agadar.nsapi.enums.RegionalMessageStatus;

import java.util.List;

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
public class RegionalMessage {

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
    public String author;

    /**
     * The message's status. Can be any of the following:
     */
    @XmlElement(name = "STATUS")
    @XmlJavaTypeAdapter(IntToRegionalMessageStatusAdapter.class)
    public RegionalMessageStatus status;

    /**
     * Name of the nation that suppressed this post. Only set if the post is
     * suppressed and done so by a non-moderator (status 1).
     */
    @XmlElement(name = "SUPPRESSOR")
    public String suppressor;

    /**
     * The number of likes this message received
     */
    @XmlElement(name = "LIKES")
    public int numberOfLikes;

    /**
     * List of nations that liked this message
     */
    @XmlElement(name = "LIKERS")
    @XmlJavaTypeAdapter(ColonSeparatedToListAdapter.class)
    public List<String> likedBy;

    /**
     * The text of the message
     */
    @XmlElement(name = "MESSAGE")
    public String text;
}
