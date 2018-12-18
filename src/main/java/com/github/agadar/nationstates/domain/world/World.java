package com.github.agadar.nationstates.domain.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.CommaSeparatedToLinkedHashSetAdapter;
import com.github.agadar.nationstates.adapter.CommaSeparatedToListAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.Dispatch;
import com.github.agadar.nationstates.domain.common.NationCensusScoreRanks;
import com.github.agadar.nationstates.domain.common.Poll;
import com.github.agadar.nationstates.domain.common.happening.Happening;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Representation of the world. This class' fields have a 1:1 correspondence
 * with the shards in WorldShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WORLD")
public class World {

    /**
     * The world's census scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    private Collection<CensusScore> census = new LinkedHashSet<CensusScore>();

    /**
     * Id of the current census.
     */
    @XmlElement(name = "CENSUSID")
    private int censusId;

    /**
     * Descriptions of the current or selected census.
     */
    @XmlElement(name = "CENSUSDESC")
    private CensusDescriptions censusDescriptions = new CensusDescriptions();

    /**
     * Name of the current or selected census.
     */
    @XmlElement(name = "CENSUS")
    private CensusName censusName = new CensusName();

    /**
     * The census scores of the world's nations.
     */
    @XmlElement(name = "CENSUSRANKS")
    private NationCensusScoreRanks censusRanks = new NationCensusScoreRanks();

    /**
     * The scale of the current or selected census.
     */
    @XmlElement(name = "CENSUSSCALE")
    private CensusScale censusScale = new CensusScale();

    /**
     * The title of the current or selected census.
     */
    @XmlElement(name = "CENSUSTITLE")
    private CensusTitle censusTitle = new CensusTitle();

    /**
     * The selected dispatch. Includes the dispatch text.
     */
    @XmlElement(name = "DISPATCH")
    private Dispatch selectedDispatch = new Dispatch();

    /**
     * The newest or selected dispatches. Does not include dispatches' texts.
     */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    private Collection<Dispatch> dispatches = new LinkedHashSet<Dispatch>();

    /**
     * Name of today's featured region.
     */
    @XmlElement(name = "FEATUREDREGION")
    private String featuredRegion = "";

    /**
     * List of the most recent or selected happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    private List<Happening> happenings = new ArrayList<Happening>();

    /**
     * List of all nations in the world.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(CommaSeparatedToLinkedHashSetAdapter.class)
    private Collection<String> nations = new LinkedHashSet<String>();

    /**
     * List of newest nations.
     */
    @XmlElement(name = "NEWNATIONS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    private List<String> newestNations = new ArrayList<String>();

    /**
     * The number of nations in the world.
     */
    @XmlElement(name = "NUMNATIONS")
    private int numberOfNations;

    /**
     * The number of regions in the world.
     */
    @XmlElement(name = "NUMREGIONS")
    private int numberOfRegions;

    /**
     * The selected poll.
     */
    @XmlElement(name = "POLL")
    private Poll selectedPoll = new Poll();

    /**
     * List of all regions in the world (index 0), or the regions selected by tag
     * (index 0 or 1).
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @XmlElement(name = "REGIONS")
    private List<RegionList> regions = new ArrayList<RegionList>();

    /**
     * If the Regions shard was used alone or together with RegionsByTag, then this
     * returns the retrieved Regions. If ONLY the RegionsByTag shard was used, this
     * returns the retrieved RegionsByTag. Else, returns an empty set.
     *
     * @return region names
     */
    public Collection<String> getRegions() {
        return regions.isEmpty() ? new HashSet<String>() : regions.get(0).getRegions();
    }

    /**
     * If the RegionsByTag shard was used alone or together with Regions, then this
     * returns the retrieved RegionsByTag. If ONLY the Regions shard was used, this
     * returns the retrieved Regions. Else, returns an empty set.
     *
     * @return region names
     */
    public Collection<String> getRegionsByTag() {
        return regions.size() < 2 ? getRegions() : regions.get(1).getRegions();
    }

    /**
     * Executed after JAXB finishes unmarshalling.
     * 
     * @param unmarshaller
     * @param parent
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.happenings = HappeningSpecializationHelper.specializeHappenings(this.happenings);
    }
}
