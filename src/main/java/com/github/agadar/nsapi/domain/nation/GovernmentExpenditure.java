package com.github.agadar.nsapi.domain.nation;

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
    public double Administration;

    /**
     * Percentage spent on defence
     */
    @XmlElement(name = "DEFENCE")
    public double Defence;

    /**
     * Percentage spent on education
     */
    @XmlElement(name = "EDUCATION")
    public double Education;

    /**
     * Percentage spent on the environment
     */
    @XmlElement(name = "ENVIRONMENT")
    public double Environment;

    /**
     * Percentage spent on healthcare
     */
    @XmlElement(name = "HEALTHCARE")
    public double Healthcare;

    /**
     * Percentage spent on commerce
     */
    @XmlElement(name = "COMMERCE")
    public double Commerce;

    /**
     * Percentage spent on international aid
     */
    @XmlElement(name = "INTERNATIONALAID")
    public double InternationalAid;

    /**
     * Percentage spent on law and order
     */
    @XmlElement(name = "LAWANDORDER")
    public double LawAndOrder;

    /**
     * Percentage spent on public transport
     */
    @XmlElement(name = "PUBLICTRANSPORT")
    public double PublicTransport;

    /**
     * Percentage spent on social equality
     */
    @XmlElement(name = "SOCIALEQUALITY")
    public double SocialEquality;

    /**
     * Percentage spent on spirituality
     */
    @XmlElement(name = "SPIRITUALITY")
    public double Spirituality;

    /**
     * Percentage spent on welfare
     */
    @XmlElement(name = "WELFARE")
    public double Welfare;
}
