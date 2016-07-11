package com.github.agadar.nsapi.domain.nation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a nation's freedom scores.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "FREEDOM")
public class Freedom 
{
    /** The nation's civil rights score */
    @XmlElement(name = "CIVILRIGHTS")
    public String CivilRights;
    
    /** The nation's economy score */
    @XmlElement(name = "ECONOMY")
    public String Economy;
    
    /** The nation's political freedoms score */
    @XmlElement(name = "POLITICALFREEDOM")
    public String PoliticalFreedom;
}
