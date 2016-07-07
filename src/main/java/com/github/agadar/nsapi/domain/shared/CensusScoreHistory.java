package com.github.agadar.nsapi.domain.shared;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entry in a CensusScore's history list.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POINT")
public class CensusScoreHistory 
{
    /** The UNIX timestamp of this record. */
    @XmlElement(name = "TIMESTAMP")
    public long Timestamp;
    
    /** What was scored that timestamp. */
    @XmlElement(name = "SCORE")
    public long Score = 1;
}
