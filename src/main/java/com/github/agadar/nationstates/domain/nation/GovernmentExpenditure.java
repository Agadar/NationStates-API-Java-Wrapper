package com.github.agadar.nationstates.domain.nation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a government's expenditure. All values are percentages. All
 * percentages together should naturally equal 100.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GOVT")
public class GovernmentExpenditure {

    /**
     * Percentage spent on the administration
     */
    @XmlElement(name = "ADMINISTRATION")
    public double administration;

    /**
     * Percentage spent on defence
     */
    @XmlElement(name = "DEFENCE")
    public double defence;

    /**
     * Percentage spent on education
     */
    @XmlElement(name = "EDUCATION")
    public double education;

    /**
     * Percentage spent on the environment
     */
    @XmlElement(name = "ENVIRONMENT")
    public double environment;

    /**
     * Percentage spent on healthcare
     */
    @XmlElement(name = "HEALTHCARE")
    public double healthcare;

    /**
     * Percentage spent on commerce
     */
    @XmlElement(name = "COMMERCE")
    public double commerce;

    /**
     * Percentage spent on international aid
     */
    @XmlElement(name = "INTERNATIONALAID")
    public double internationalAid;

    /**
     * Percentage spent on law and order
     */
    @XmlElement(name = "LAWANDORDER")
    public double lawAndOrder;

    /**
     * Percentage spent on public transport
     */
    @XmlElement(name = "PUBLICTRANSPORT")
    public double publicTransport;

    /**
     * Percentage spent on social equality
     */
    @XmlElement(name = "SOCIALEQUALITY")
    public double socialEquality;

    /**
     * Percentage spent on spirituality
     */
    @XmlElement(name = "SPIRITUALITY")
    public double spirituality;

    /**
     * Percentage spent on welfare
     */
    @XmlElement(name = "WELFARE")
    public double welfare;
}
