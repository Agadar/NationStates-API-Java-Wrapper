package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a nation's economy sectors. All values are percentages.
 * All values together should naturally equal 100.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SECTORS")
public class EconomySectors 
{
    @XmlElement(name = "BLACKMARKET")
    public double BlackMarket;
    
    @XmlElement(name = "GOVERNMENT")
    public double Government;
    
    @XmlElement(name = "INDUSTRY")
    public double PrivateIndustry;
    
    @XmlElement(name = "PUBLIC")
    public double StateOwnedIndustry;
}
