package com.github.agadar.nationstates.domain.world;

import com.github.agadar.nationstates.adapter.CommaSeparatedToListAdapter;
import com.github.agadar.nationstates.adapter.CommaSeparatedToSetAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.Dispatch;
import com.github.agadar.nationstates.domain.common.NationCensusScoreRanks;
import com.github.agadar.nationstates.domain.common.Poll;
import com.github.agadar.nationstates.domain.common.happening.Happening;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of the world. This class' fields have a 1:1 correspondence
 * with the shards in WorldShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "WORLD")
public class World {

    /**
     * The world's census scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public Set<CensusScore> census;

    /**
     * Id of the current census.
     */
    @XmlElement(name = "CENSUSID")
    public int censusId;

    /**
     * Descriptions of the current or selected census.
     */
    @XmlElement(name = "CENSUSDESC")
    public CensusDescriptions censusDescriptions;

    /**
     * Name of the current or selected census.
     */
    @XmlElement(name = "CENSUS")
    public CensusName censusName;

    /**
     * The census scores of the world's nations.
     */
    @XmlElement(name = "CENSUSRANKS")
    public NationCensusScoreRanks censusRanks;

    /**
     * The scale of the current or selected census.
     */
    @XmlElement(name = "CENSUSSCALE")
    public CensusScale censusScale;

    /**
     * The title of the current or selected census.
     */
    @XmlElement(name = "CENSUSTITLE")
    public CensusTitle censusTitle;

    /**
     * The selected dispatch. Includes the dispatch text.
     */
    @XmlElement(name = "DISPATCH")
    public Dispatch selectedDispatch;

    /**
     * The newest or selected dispatches. Does not include dispatches' texts.
     */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    public Set<Dispatch> dispatches;

    /**
     * Name of today's featured region.
     */
    @XmlElement(name = "FEATUREDREGION")
    public String featuredRegion;

    /**
     * List of the most recent or selected happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public SortedSet<Happening> happenings;

    /**
     * List of all nations in the world.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(CommaSeparatedToSetAdapter.class)
    public Set<String> nations;

    /**
     * List of newest nations.
     */
    @XmlElement(name = "NEWNATIONS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> newestNations;

    /**
     * The number of nations in the world.
     */
    @XmlElement(name = "NUMNATIONS")
    public int numberOfNations;

    /**
     * The number of regions in the world.
     */
    @XmlElement(name = "NUMREGIONS")
    public int numberOfRegions;

    /**
     * The selected poll.
     */
    @XmlElement(name = "POLL")
    public Poll selectedPoll;

    /**
     * List of all regions in the world (index 0), or the regions selected by tag
     * (index 0 or 1).
     */
    @XmlElement(name = "REGIONS")
    private List<RegionList> regions;

    /**
     * If the Regions shard was used alone or together with RegionsByTag, then this
     * returns the retrieved Regions. If ONLY the RegionsByTag shard was used, this
     * returns the retrieved RegionsByTag. Else, returns an empty set.
     *
     * @return region names
     */
    public Set<String> regions() {
        return (regions == null || regions.isEmpty()) ? new HashSet<String>() : regions.get(0).regions;
    }

    /**
     * If the RegionsByTag shard was used alone or together with Regions, then this
     * returns the retrieved RegionsByTag. If ONLY the Regions shard was used, this
     * returns the retrieved Regions. Else, returns an empty set.
     *
     * @return region names
     */
    public Set<String> regionsByTag() {
        Set<String> tmp = regions();
        return (tmp == null || regions.size() < 2) ? tmp : regions.get(1).regions;
    }

    /**
     * Executed after JAXB finishes unmmarshalling.
     * 
     * @param unmarshaller
     * @param parent
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.happenings = HappeningSpecializationHelper.specializeHappenings(this.happenings);
    }
}
