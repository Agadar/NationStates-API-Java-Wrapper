package com.github.agadar.nsapi.domain.world;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * The scale of the current or selected census.
 *  
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSSCALE")
public class CensusScale 
{
    /** Id of this census scale. */
    @XmlAttribute(name = "id")
    public int Id;
    
    /** Scale name of this census. */
    @XmlValue
    public String Scale;   
}
