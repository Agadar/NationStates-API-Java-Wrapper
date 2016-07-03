package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contains a nation's or region's statistics of the current or last zombie event.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ZOMBIE")
public class ZombieInfo 
{
    @XmlElement(name = "ZACTION")
    public String Action;
    
    @XmlElement(name = "SURVIVORS")
    public int Survivors;
    
    @XmlElement(name = "ZOMBIES")
    public int Zombies;
    
    @XmlElement(name = "DEAD")
    public int Dead;
}
