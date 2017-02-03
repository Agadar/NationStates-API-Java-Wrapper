package com.github.agadar.nationstates.domain.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entry in a CensusScore's history list.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POINT")
public class CensusScoreHistory {

    /**
     * The UNIX timestamp of this record.
     */
    @XmlElement(name = "TIMESTAMP")
    public long timestamp;

    /**
     * What was scored that timestamp.
     */
    @XmlElement(name = "SCORE")
    public String score;
}
