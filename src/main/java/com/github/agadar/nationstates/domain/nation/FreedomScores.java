package com.github.agadar.nationstates.domain.nation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a nation's freedom scores, expressed in numbers. See also Freedom.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FREEDOMSCORES")
public class FreedomScores {

    /**
     * The nation's civil rights score
     */
    @XmlElement(name = "CIVILRIGHTS")
    private int civilRights;

    /**
     * The nation's economy score
     */
    @XmlElement(name = "ECONOMY")
    private int economy;

    /**
     * The nation's political freedoms score
     */
    @XmlElement(name = "POLITICALFREEDOM")
    private int politicalFreedom;
}
