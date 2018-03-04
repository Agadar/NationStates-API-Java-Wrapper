package com.github.agadar.nationstates.domain.nation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a nation's freedom scores, expressed in numbers. See also Freedom.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FREEDOMSCORES")
public class FreedomScores {

    /**
     * The nation's civil rights score
     */
    @XmlElement(name = "CIVILRIGHTS")
    public int civilRights;

    /**
     * The nation's economy score
     */
    @XmlElement(name = "ECONOMY")
    public int economy;

    /**
     * The nation's political freedoms score
     */
    @XmlElement(name = "POLITICALFREEDOM")
    public int politicalFreedom;
}
