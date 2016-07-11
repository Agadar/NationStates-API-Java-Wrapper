package com.github.agadar.nsapi.domain.region;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The region's votes for the current General Assembly or Security Council resolution.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GAVOTE")
public class WorldAssemblyVote 
{
    /** Number of nations that voted FOR. */
    @XmlElement(name = "FOR")
    public int For;
    
    /** Number of nations that voted AGAINST. */
    @XmlElement(name = "AGAINST")
    public int Against;
}
