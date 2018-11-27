package com.github.agadar.nationstates.domain.nation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a nation's freedom scores, expressed in words. See also
 * FreedomScores.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FREEDOM")
public class Freedom {

    /**
     * The nation's civil rights score
     */
    @XmlElement(name = "CIVILRIGHTS")
    private String civilRights = "";

    /**
     * The nation's economy score
     */
    @XmlElement(name = "ECONOMY")
    private String economy = "";

    /**
     * The nation's political freedoms score
     */
    @XmlElement(name = "POLITICALFREEDOM")
    private String politicalFreedom = "";
}
