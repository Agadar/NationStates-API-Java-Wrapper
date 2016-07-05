package com.github.agadar.nsapi.domain.world;

import com.github.agadar.nsapi.domain.shared.CensusScore;
import com.github.agadar.nsapi.domain.shared.NationCensusScoreRanks;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representation of the world. This class's fields have a 1:1 correspondence 
 * with the shards in WorldShard.java.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WORLD")
public class World 
{
    /** The world's census scale scores. */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public List<CensusScore> Census;
    
    /** Id of the current census. */
    @XmlElement(name = "CENSUSID")
    public int CensusId;
    
    /** Descriptions of the current or selected census. */
    @XmlElement(name = "CENSUSDESC")
    public CensusDescriptions CensusDescriptions;
    
    /** Name of the current or selected census. */
    @XmlElement(name = "CENSUS")
    public CensusName CensusName;
    
    /** The census scale scores of the world's nations. */
    @XmlElement(name = "CENSUSRANKS")
    public NationCensusScoreRanks CensusRanks;
}
