package com.github.agadar.nationstates.domain.region;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * The region's votes for the current General Assembly or Security Council
 * resolution.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GAVOTE")
public class WorldAssemblyVote {

    /**
     * Number of nations that voted FOR.
     */
    @XmlElement(name = "FOR")
    private int votesFor;

    /**
     * Number of nations that voted AGAINST.
     */
    @XmlElement(name = "AGAINST")
    private int votesAgainst;
}
