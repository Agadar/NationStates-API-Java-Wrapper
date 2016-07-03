package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a happening.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EVENT")
public class Happening 
{
    @XmlElement(name = "TIMESTAMP")
    public long Timestamp;
    
    @XmlElement(name = "TEXT")
    public String Text;
}
