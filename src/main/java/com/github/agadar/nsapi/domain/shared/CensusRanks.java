package com.github.agadar.nsapi.domain.shared;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lists the regional census scale scores of a region's top 20 scoring nations, 
 * or the world-wide census scale scores of the world's top 20 scoring nations.
 * 
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSRANKS")
public class CensusRanks 
{
    /** The id of the scale */
    @XmlAttribute(name = "id")
    public int Id;
    
    /** The nation scores */
    @XmlElementWrapper(name = "NATIONS")
    @XmlElement(name = "NATION")
    public List<CensusNation> Nations;
}
