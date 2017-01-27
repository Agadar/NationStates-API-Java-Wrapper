package com.github.agadar.nsapi.domain.world;

import com.github.agadar.nsapi.adapter.CommaSeparatedToListAdapter;
import com.github.agadar.nsapi.domain.shared.CensusScore;
import com.github.agadar.nsapi.domain.shared.Dispatch;
import com.github.agadar.nsapi.domain.shared.Happening;
import com.github.agadar.nsapi.domain.shared.NationCensusScoreRanks;
import com.github.agadar.nsapi.domain.shared.Poll;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of the world. This class's fields have a 1:1 correspondence
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
    public List<CensusScore> Census;

    /**
     * Id of the current census.
     */
    @XmlElement(name = "CENSUSID")
    public int CensusId;

    /**
     * Descriptions of the current or selected census.
     */
    @XmlElement(name = "CENSUSDESC")
    public CensusDescriptions CensusDescriptions;

    /**
     * Name of the current or selected census.
     */
    @XmlElement(name = "CENSUS")
    public CensusName CensusName;

    /**
     * The census scores of the world's nations.
     */
    @XmlElement(name = "CENSUSRANKS")
    public NationCensusScoreRanks CensusRanks;

    /**
     * The scale of the current or selected census.
     */
    @XmlElement(name = "CENSUSSCALE")
    public CensusScale CensusScale;

    /**
     * The title of the current or selected census.
     */
    @XmlElement(name = "CENSUSTITLE")
    public CensusTitle CensusTitle;

    /**
     * The selected dispatch. Includes the dispatch text.
     */
    @XmlElement(name = "DISPATCH")
    public Dispatch SelectedDispatch;

    /**
     * The newest or selected dispatches. Does not include dispatches' texts.
     */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    public List<Dispatch> Dispatches;

    /**
     * Name of today's featured region.
     */
    @XmlElement(name = "FEATUREDREGION")
    public String FeaturedRegion;

    /**
     * List of the most recent or selected happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public List<Happening> Happenings;

    /**
     * List of all nations in the world.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> Nations;

    /**
     * List of newest nations.
     */
    @XmlElement(name = "NEWNATIONS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> NewestNations;

    /**
     * The number of nations in the world.
     */
    @XmlElement(name = "NUMNATIONS")
    public int NumberOfNations;

    /**
     * The number of regions in the world.
     */
    @XmlElement(name = "NUMREGIONS")
    public int NumberOfRegions;

    /**
     * The selected poll.
     */
    @XmlElement(name = "POLL")
    public Poll SelectedPoll;

    /**
     * List of all regions in the world (index 0), or the regions selected by
     * tag (index 0 or 1).
     */
    @XmlElement(name = "REGIONS")
    private List<RegionList> Regions;

    /**
     * If the Regions shard was used alone or together with RegionsByTag, then
     * this returns the retrieved Regions. If ONLY the RegionsByTag shard was
     * used, this returns the retrieved RegionsByTag. Else, returns null.
     *
     * @return region names
     */
    public List<String> Regions() {
        return Regions == null || Regions.isEmpty() ? null : Regions.get(0).Regions;
    }

    /**
     * If the RegionsByTag shard was used alone or together with Regions, then
     * this returns the retrieved RegionsByTag. If ONLY the Regions shard was
     * used, this returns the retrieved Regions. Else, returns null.
     *
     * @return region names
     */
    public List<String> RegionsByTag() {
        List<String> regions = Regions();
        return regions == null || Regions.size() < 2 ? regions : Regions.get(1).Regions;
    }
}
