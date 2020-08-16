package com.github.agadar.nationstates.domain.region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.ColonStringToStringSetAdapter;
import com.github.agadar.nationstates.adapter.CsvStringToLongSetSetAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.NationCensusScoreRanks;
import com.github.agadar.nationstates.domain.common.Poll;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.common.ZombieInfo;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassiesRmbPermissions;
import com.github.agadar.nationstates.enumerator.RegionTag;
import com.github.agadar.nationstates.exception.NationStatesAPIException;

import lombok.Getter;
import lombok.Setter;

/**
 * Representation of a region. This class' fields have a 1:1 correspondence with
 * the shards in RegionShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGION")
public class Region {

    /**
     * The region's id. Not available in dump file.
     */
    @XmlAttribute(name = "id")
    private String id = "";

    /**
     * This region's census scale scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    private Collection<CensusScore> census = new LinkedHashSet<CensusScore>();

    /**
     * The census scale scores of this region's nations.
     */
    @XmlElement(name = "CENSUSRANKS")
    private NationCensusScoreRanks censusRanks = new NationCensusScoreRanks();

    /**
     * The name of the nation that is the region's world assembly delegate. Returns
     * '0' if no delegate exists.
     */
    @XmlElement(name = "DELEGATE")
    private String delegate = "";

    /**
     * The authorities granted to the region's world assembly delegate.
     */
    @XmlElement(name = "DELEGATEAUTH")
    @XmlJavaTypeAdapter(Authority.Adapter.class)
    private Collection<Authority> delegateAuthorities = new LinkedHashSet<Authority>();

    /**
     * The number of endorsements the region's world assembly delegate has.
     */
    @XmlElement(name = "DELEGATEVOTES")
    private int delegateEndorsements;
    
    /**
     * The unique identifiers of this region's pinned dispatches.
     */
    @XmlElement(name = "DISPATCHES")
    @XmlJavaTypeAdapter(CsvStringToLongSetSetAdapter.class)
    private Collection<Long> dispatches = new LinkedHashSet<Long>(); 

    /**
     * The region's embassies.
     */
    @XmlElementWrapper(name = "EMBASSIES")
    @XmlElement(name = "EMBASSY")
    private Collection<Embassy> embassies = new LinkedHashSet<Embassy>();

    /**
     * Regional Message Board permissions for regions with which this region
     * exchanges embassies.
     */
    @XmlElement(name = "EMBASSYRMB")
    @XmlJavaTypeAdapter(EmbassiesRmbPermissions.Adapter.class)
    private EmbassiesRmbPermissions embassiesRmbPermissions = EmbassiesRmbPermissions.NULL;

    /**
     * The complete mark-up of the regional factbook.
     */
    @XmlElement(name = "FACTBOOK")
    private String factbook = "";

    /**
     * URL to the region's flag image.
     */
    @XmlElement(name = "FLAG")
    private String flagUrl = "";

    /**
     * Description of when this region was founded.
     */
    @XmlElement(name = "FOUNDED")
    private String foundedDescription = "";

    /**
     * UNIX timestamp of when this region was founded.
     */
    @XmlElement(name = "FOUNDEDTIME")
    private long founded;

    /**
     * The name of the region's founding nation. Returns '0' if none exists.
     */
    @XmlElement(name = "FOUNDER")
    private String founder = "";

    /**
     * The authorities granted to the region's founder.
     */
    @XmlElement(name = "FOUNDERAUTH")
    @XmlJavaTypeAdapter(Authority.Adapter.class)
    private Collection<Authority> founderAuthorities = new LinkedHashSet<Authority>();

    /**
     * The region's votes for the current General Assembly resolution.
     */
    @XmlElement(name = "GAVOTE")
    private WorldAssemblyVote generalAssemblyVote = new WorldAssemblyVote();

    /**
     * List of the most recent of this region's happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    private List<Happening> recentHappenings = new ArrayList<Happening>();

    /**
     * List of the most recent history.
     */
    @XmlElementWrapper(name = "HISTORY")
    @XmlElement(name = "EVENT")
    private List<Happening> history = new ArrayList<Happening>();

    /**
     * UNIX timestamp of when this region was last updated.
     */
    @XmlElement(name = "LASTUPDATE")
    private long lastUpdate;

    /**
     * List of 10 most recent regional messages.
     */
    @XmlElementWrapper(name = "MESSAGES")
    @XmlElement(name = "POST")
    private List<RegionalMessage> regionalMessages = new ArrayList<RegionalMessage>();

    /**
     * Rankings of nations with most RMB posts made.
     */
    @XmlElementWrapper(name = "MOSTPOSTS")
    @XmlElement(name = "NATION")
    private List<MostPostsRank> mostPostsRanks = new ArrayList<MostPostsRank>();

    /**
     * Rankings of nations with most RMB likes given.
     */
    @XmlElementWrapper(name = "MOSTLIKED")
    @XmlElement(name = "NATION")
    private List<MostLikedRank> mostLikedRanks = new ArrayList<MostLikedRank>();

    /**
     * Rankings of nations with most RMB likes received.
     */
    @XmlElementWrapper(name = "MOSTLIKES")
    @XmlElement(name = "NATION")
    private List<MostLikesRank> mostLikesRanks = new ArrayList<MostLikesRank>();

    /**
     * The region's name. Should be similar to id, but capitalized.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * List of nations that inhabit this region.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(ColonStringToStringSetAdapter.class)
    private Collection<String> nationNames = new LinkedHashSet<String>();

    /**
     * The number of nations that inhabit this region.
     */
    @XmlElement(name = "NUMNATIONS")
    private int numberOfNations;

    /**
     * List of regional officers.
     */
    @XmlElementWrapper(name = "OFFICERS")
    @XmlElement(name = "OFFICER")
    private Collection<Officer> officers = new LinkedHashSet<Officer>();

    /**
     * The regional poll that is currently being conducted.
     */
    @XmlElement(name = "POLL")
    private Poll currentPoll = new Poll();

    /**
     * The region's power.
     */
    @XmlElement(name = "POWER")
    private String power = "";

    /**
     * The region's votes for the current Security Council resolution.
     */
    @XmlElement(name = "SCVOTE")
    private WorldAssemblyVote securityCouncilVote = new WorldAssemblyVote();

    /**
     * The region's tags.
     */
    @XmlElementWrapper(name = "TAGS")
    @XmlElement(name = "TAG")
    @XmlJavaTypeAdapter(RegionTag.Adapter.class)
    private Collection<RegionTag> tags = new LinkedHashSet<RegionTag>();

    /**
     * The World Assembly badges granted to this region by the Security Council.
     */
    @XmlElementWrapper(name = "WABADGES")
    @XmlElement(name = "WABADGE")
    private Collection<WorldAssemblyBadge> worldAssemblyBadges = new LinkedHashSet<WorldAssemblyBadge>();

    /**
     * This region's statistics of the current or last zombie event.
     */
    @XmlElement(name = "ZOMBIE")
    private ZombieInfo zombieInfo = new ZombieInfo();

    /**
     * Executed after JAXB finishes unmarshalling.
     * 
     * @param unmarshaller
     * @param parent
     * @throws NationStatesAPIException If happening specialization failed.
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) throws NationStatesAPIException {
        this.recentHappenings = HappeningSpecializationHelper.specializeHappenings(this.recentHappenings);
        this.history = HappeningSpecializationHelper.specializeHappenings(this.history);
    }

}
