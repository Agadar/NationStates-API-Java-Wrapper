package com.github.agadar.nsapi.domain.region;

import com.github.agadar.nsapi.adapter.ColonSeparatedToListAdapter;
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
public class RegionalMessage 
{
    /** The message's id */
    @XmlAttribute(name = "id")
    public long Id;
    
    /** UNIX timestamp of when this message was posted */
    @XmlElement(name = "TIMESTAMP")
    public long Timestamp;
    
    /** Name of the nation that posted the message */
    @XmlElement(name = "NATION")
    public String Author;
    
    /** The message's status. Can be any of the following:
     * 
     * 0: A regular post;
     * 1: Post is suppressed but viewable. In this case, there will also be a 
     * SUPPRESSOR field with the name of the nation who suppressed the post;
     * 2: Post was deleted by the author and is not viewable;
     * 9: Post was suppressed by a moderator and is not viewable.
     */
    @XmlElement(name = "STATUS")
    public int Status;
    
    /** Name of the nation that suppressed this post. Only set if the post is 
     *  suppressed and done so by a non-moderator (status 1). */
    @XmlElement(name = "SUPPRESSOR")
    public String Suppressor;
    
    /** The number of likes this message received */
    @XmlElement(name = "LIKES")
    public int NumberOfLikes;
    
    /** List of nations that liked this message */
    @XmlElement(name = "LIKERS")
    @XmlJavaTypeAdapter(ColonSeparatedToListAdapter.class)
    public List<String> LikedBy;
    
    /** The text of the message */
    @XmlElement(name = "MESSAGE")
    public String Text;
}
