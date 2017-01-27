package com.github.agadar.nsapi.domain.world;

import com.github.agadar.nsapi.adapter.CommaSeparatedToListAdapter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * The Regions tag can be found up to two times within the World root tag: once
 * for when all regions are retrieved (Regions) and once for when specific
 * regions are selected (SelectedRegions). In order to properly handle parsing
 * these to their corresponding fields, this wrapper is necessary.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGIONS")
public class RegionList {

    /**
     * This wrapper's list of regions.
     */
    @XmlValue
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> Regions;
}
