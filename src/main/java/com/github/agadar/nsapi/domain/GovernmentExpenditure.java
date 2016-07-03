package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a government's expenditure. All values are percentages.
 * All percentages together should naturally equal 100.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GOVT")
public class GovernmentExpenditure 
{
    @XmlElement(name = "ADMINISTRATION")
    public double Administration;
    
    @XmlElement(name = "DEFENCE")
    public double Defence;
    
    @XmlElement(name = "EDUCATION")
    public double Education;
    
    @XmlElement(name = "ENVIRONMENT")
    public double Environment;
    
    @XmlElement(name = "HEALTHCARE")
    public double Healthcare;
    
    @XmlElement(name = "COMMERCE")
    public double Commerce;
    
    @XmlElement(name = "INTERNATIONALAID")
    public double InternationalAid;
    
    @XmlElement(name = "LAWANDORDER")
    public double LawAndOrder;
    
    @XmlElement(name = "PUBLICTRANSPORT")
    public double PublicTransport;
    
    @XmlElement(name = "SOCIALEQUALITY")
    public double SocialEquality;
    
    @XmlElement(name = "SPIRITUALITY")
    public double Spirituality;
    
    @XmlElement(name = "WELFARE")
    public double Welfare;
}
