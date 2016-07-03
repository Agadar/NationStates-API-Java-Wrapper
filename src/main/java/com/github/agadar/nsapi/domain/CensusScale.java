package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of a census scale.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SCALE")
public class CensusScale 
{
    @XmlAttribute(name = "id")
    public int Id;
    
    @XmlElement(name = "SCORE")
    public double Score;
    
    @XmlElement(name = "RANK")
    public int WorldRank;
    
    @XmlElement(name = "RRANK")
    public int RegionalRank;
}
