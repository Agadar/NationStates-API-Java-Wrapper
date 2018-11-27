package com.github.agadar.nationstates.domain.nation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a government's expenditure. All values are percentages. All
 * percentages together should naturally equal 100.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GOVT")
public class GovernmentExpenditure {

    /**
     * Percentage spent on the administration
     */
    @XmlElement(name = "ADMINISTRATION")
    private double administration;

    /**
     * Percentage spent on defence
     */
    @XmlElement(name = "DEFENCE")
    private double defence;

    /**
     * Percentage spent on education
     */
    @XmlElement(name = "EDUCATION")
    private double education;

    /**
     * Percentage spent on the environment
     */
    @XmlElement(name = "ENVIRONMENT")
    private double environment;

    /**
     * Percentage spent on healthcare
     */
    @XmlElement(name = "HEALTHCARE")
    private double healthcare;

    /**
     * Percentage spent on commerce
     */
    @XmlElement(name = "COMMERCE")
    private double commerce;

    /**
     * Percentage spent on international aid
     */
    @XmlElement(name = "INTERNATIONALAID")
    private double internationalAid;

    /**
     * Percentage spent on law and order
     */
    @XmlElement(name = "LAWANDORDER")
    private double lawAndOrder;

    /**
     * Percentage spent on public transport
     */
    @XmlElement(name = "PUBLICTRANSPORT")
    private double publicTransport;

    /**
     * Percentage spent on social equality
     */
    @XmlElement(name = "SOCIALEQUALITY")
    private double socialEquality;

    /**
     * Percentage spent on spirituality
     */
    @XmlElement(name = "SPIRITUALITY")
    private double spirituality;

    /**
     * Percentage spent on welfare
     */
    @XmlElement(name = "WELFARE")
    private double welfare;
}
