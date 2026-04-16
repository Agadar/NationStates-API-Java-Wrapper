package com.github.agadar.nationstates.domain.world;

import java.util.Collection;
import java.util.LinkedHashSet;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;

import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    private Collection<String> regions = new LinkedHashSet<>();
}
