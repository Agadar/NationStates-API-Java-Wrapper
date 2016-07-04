package com.github.agadar.nsapi.domain.region;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A regional poll.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POLL")
public class Poll 
{
    /** The poll's id. */
    @XmlAttribute(name = "id")
    public int Id;
    
    /** The poll's title. */
    @XmlElement(name = "TITLE")
    public String Title;
    
    /** The poll's description. */
    @XmlElement(name = "TEXT")
    public String Description;
    
    /** The name of the region the poll is in. */
    @XmlElement(name = "REGION")
    public String Region;
    
    /** UNIX timestamp on which the poll opened. */
    @XmlElement(name = "START")
    public long OpenedOn;
    
    /** UNIX timestamp on which the poll will close. */
    @XmlElement(name = "STOP")
    public long ClosingOn;
    
    /** Name of the nation that opened the poll. */
    @XmlElement(name = "AUTHOR")
    public String AuthorName;
    
    /** The possible options for this poll. */
    @XmlElementWrapper(name = "OPTIONS")
    @XmlElement(name = "OPTION")
    public List<PollOption> Options;
}
