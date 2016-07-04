package com.github.agadar.nsapi.domain.region;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The region's votes for the current General Assembly or Security Council resolution.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GAVOTE")
public class WorldAssemblyVote 
{
    @XmlElement(name = "FOR")
    public int For;
    
    @XmlElement(name = "AGAINST")
    public int Against;
}
