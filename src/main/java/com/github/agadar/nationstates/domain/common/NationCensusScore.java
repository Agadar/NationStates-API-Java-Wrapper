package com.github.agadar.nationstates.domain.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A nation's statistics in the census ranks.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class NationCensusScore {

    /**
     * The nation's name
     */
    @XmlElement(name = "NAME")
    public String nationName;

    /**
     * The nation's regional or world-wide rank on the census scale
     */
    @XmlElement(name = "RANK")
    public int rank;

    /**
     * The nation's score on the scale
     */
    @XmlElement(name = "SCORE")
    public String score;
}
