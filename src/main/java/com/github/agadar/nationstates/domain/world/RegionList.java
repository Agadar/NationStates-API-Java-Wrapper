package com.github.agadar.nationstates.domain.world;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;

import lombok.Getter;
import lombok.Setter;

/**
 * The Regions tag can be found up to two times within the World root tag: once
 * for when all regions are retrieved (Regions) and once for when specific
 * regions are selected (SelectedRegions). In order to properly handle parsing
 * these to their corresponding fields, this wrapper is necessary.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGIONS")
public class RegionList {

    /**
     * This wrapper's list of regions.
     */
    @XmlValue
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private Collection<String> regions = new LinkedHashSet<String>();
}
