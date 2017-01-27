package com.github.agadar.nsapi.domain;

import com.github.agadar.nsapi.domain.region.Region;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of regions retrieved from the daily dump.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGIONS")
public class DailyDumpRegions {

    @XmlElement(name = "REGION")
    public List<Region> Regions;
}
