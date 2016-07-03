package com.github.agadar.nsapi.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a nation's freedom values.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FREEDOM")
public class Freedom 
{
    @XmlElement(name = "CIVILRIGHTS")
    public String CivilRights;
    
    @XmlElement(name = "ECONOMY")
    public String Economy;
    
    @XmlElement(name = "POLITICALFREEDOM")
    public String PoliticalFreedom;
}
