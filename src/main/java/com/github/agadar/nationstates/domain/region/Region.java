package com.github.agadar.nationstates.domain.region;

import com.github.agadar.nationstates.adapter.ColonSeparatedToSetAdapter;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.Happening;
import com.github.agadar.nationstates.domain.common.NationCensusScoreRanks;
import com.github.agadar.nationstates.domain.common.Poll;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.common.ZombieInfo;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassiesRmbPermissions;
import com.github.agadar.nationstates.enumerator.RegionTag;

import java.util.Set;
import java.util.SortedSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a region. This class' fields have a 1:1 correspondence with
 * the shards in RegionShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGION")
public class Region {

    /**
     * The region's id. Not available in dump file.
     */
    @XmlAttribute(name = "id")
    public String id;

    /**
     * This region's census scale scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public Set<CensusScore> census;

    /**
     * The census scale scores of this region's nations.
     */
    @XmlElement(name = "CENSUSRANKS")
    public NationCensusScoreRanks censusRanks;

    /**
     * The name of the nation that is the region's world assembly delegate.
     * Returns '0' if no delegate exists.
     */
    @XmlElement(name = "DELEGATE")
    public String delegate;

    /**
     * The authorities granted to the region's world assembly delegate.
     */
    @XmlElement(name = "DELEGATEAUTH")
    @XmlJavaTypeAdapter(Authority.Adapter.class)
    public Set<Authority> delegateAuthorities;

    /**
     * The number of endorsements the region's world assembly delegate has.
     */
    @XmlElement(name = "DELEGATEVOTES")
    public int delegateEndorsements;

    /**
     * The region's embassies.
     */
    @XmlElementWrapper(name = "EMBASSIES")
    @XmlElement(name = "EMBASSY")
    public Set<Embassy> embassies;

    /**
     * Regional Message Board permissions for regions with which this region
     * exchanges embassies.
     */
    @XmlElement(name = "EMBASSYRMB")
    @XmlJavaTypeAdapter(EmbassiesRmbPermissions.Adapter.class)
    public EmbassiesRmbPermissions embassiesRmbPermissions;

    /**
     * The complete mark-up of the regional factbook.
     */
    @XmlElement(name = "FACTBOOK")
    public String factbook;

    /**
     * URL to the region's flag image.
     */
    @XmlElement(name = "FLAG")
    public String flagUrl;

    /**
     * Description of when this region was founded.
     */
    @XmlElement(name = "FOUNDED")
    public String foundedDescription;

    /**
     * UNIX timestamp of when this region was founded.
     */
    @XmlElement(name = "FOUNDEDTIME")
    public Long founded;

    /**
     * The name of the region's founding nation. Returns '0' if none exists.
     */
    @XmlElement(name = "FOUNDER")
    public String founder;

    /**
     * The authorities granted to the region's founder.
     */
    @XmlElement(name = "FOUNDERAUTH")
    @XmlJavaTypeAdapter(Authority.Adapter.class)
    public Set<Authority> founderAuthorities;

    /**
     * The region's votes for the current General Assembly resolution.
     */
    @XmlElement(name = "GAVOTE")
    public WorldAssemblyVote generalAssemblyVote;

    /**
     * List of the most recent of this nation's happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public SortedSet<Happening> recentHappenings;

    /**
     * List of the most recent history.
     */
    @XmlElementWrapper(name = "HISTORY")
    @XmlElement(name = "EVENT")
    public SortedSet<Happening> history;

    /**
     * List of 10 most recent regional messages.
     */
    @XmlElementWrapper(name = "MESSAGES")
    @XmlElement(name = "POST")
    public SortedSet<RegionalMessage> regionalMessages;

    /**
     * Rankings of nations with most RMB posts made.
     */
    @XmlElementWrapper(name = "MOSTPOSTS")
    @XmlElement(name = "NATION")
    public SortedSet<MostPostsRank> mostPostsRanks;

    /**
     * Rankings of nations with most RMB likes given.
     */
    @XmlElementWrapper(name = "MOSTLIKED")
    @XmlElement(name = "NATION")
    public SortedSet<MostLikedRank> mostLikedRanks;

    /**
     * Rankings of nations with most RMB likes received.
     */
    @XmlElementWrapper(name = "MOSTLIKES")
    @XmlElement(name = "NATION")
    public SortedSet<MostLikesRank> mostLikesRanks;

    /**
     * The region's name. Should be similar to id, but capitalized.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * List of nations that inhabit this region.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(ColonSeparatedToSetAdapter.class)
    public Set<String> nationNames;

    /**
     * The number of nations that inhabit this region.
     */
    @XmlElement(name = "NUMNATIONS")
    public int numberOfNations;

    /**
     * List of regional officers.
     */
    @XmlElementWrapper(name = "OFFICERS")
    @XmlElement(name = "OFFICER")
    public Set<Officer> officers;

    /**
     * The regional poll that is currently being conducted.
     */
    @XmlElement(name = "POLL")
    public Poll currentPoll;

    /**
     * The region's power.
     */
    @XmlElement(name = "POWER")
    public String power;

    /**
     * The region's votes for the current Security Council resolution.
     */
    @XmlElement(name = "SCVOTE")
    public WorldAssemblyVote securityCouncilVote;

    /**
     * The region's tags.
     */
    @XmlElementWrapper(name = "TAGS")
    @XmlElement(name = "TAG")
    @XmlJavaTypeAdapter(RegionTag.Adapter.class)
    public Set<RegionTag> tags;

    /**
     * The World Assembly badges granted to this region by the Security Council.
     */
    @XmlElementWrapper(name = "WABADGES")
    @XmlElement(name = "WABADGE")
    public Set<WorldAssemblyBadge> worldAssemblyBadges;

    /**
     * This region's statistics of the current or last zombie event.
     */
    @XmlElement(name = "ZOMBIE")
    public ZombieInfo zombieInfo;

}
