package com.github.agadar.nsapi.domain.region;

import com.github.agadar.nsapi.adapter.CharsToAuthoritiesAdapter;
import com.github.agadar.nsapi.adapter.ColonSeparatedToListAdapter;
import com.github.agadar.nsapi.adapter.StringToEmbassiesRmbPermissionsAdapter;
import com.github.agadar.nsapi.domain.shared.CensusScore;
import com.github.agadar.nsapi.domain.shared.Happening;
import com.github.agadar.nsapi.domain.shared.NationCensusScoreRanks;
import com.github.agadar.nsapi.domain.shared.Poll;
import com.github.agadar.nsapi.domain.shared.ZombieInfo;
import com.github.agadar.nsapi.enums.Authority;
import com.github.agadar.nsapi.enums.EmbassiesRmbPermissions;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a region. This class's fields have a 1:1 correspondence
 * with the shards in RegionShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REGION")
public class Region {

    /**
     * This region's census scale scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public List<CensusScore> census;

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
    @XmlJavaTypeAdapter(CharsToAuthoritiesAdapter.class)
    public List<Authority> delegateAuthorities;

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
    public List<Embassy> embassies;

    /**
     * Regional Message Board permissions for regions with which this region
     * exchanges embassies.
     */
    @XmlElement(name = "EMBASSYRMB")
    @XmlJavaTypeAdapter(StringToEmbassiesRmbPermissionsAdapter.class)
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
    @XmlJavaTypeAdapter(CharsToAuthoritiesAdapter.class)
    public List<Authority> founderAuthorities;

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
    public List<Happening> recentHappenings;

    /**
     * List of the most recent history. Not sure what these represent.
     */
    @XmlElementWrapper(name = "HISTORY")
    @XmlElement(name = "EVENT")
    public List<Happening> history;

    /**
     * List of 10 most recent regional messages.
     */
    @XmlElementWrapper(name = "MESSAGES")
    @XmlElement(name = "POST")
    public List<RegionalMessage> regionalMessages;

    /**
     * Rankings of nations with most RMB posts made.
     */
    @XmlElementWrapper(name = "MOSTPOSTS")
    @XmlElement(name = "NATION")
    public List<MostPostsRank> mostPostsRanks;

    /**
     * Rankings of nations with most RMB likes given.
     */
    @XmlElementWrapper(name = "MOSTLIKED")
    @XmlElement(name = "NATION")
    public List<MostLikedRank> mostLikedRanks;

    /**
     * Rankings of nations with most RMB likes received.
     */
    @XmlElementWrapper(name = "MOSTLIKES")
    @XmlElement(name = "NATION")
    public List<MostLikesRank> mostLikesRanks;

    /**
     * The region's name.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * List of nations that inhabit this region.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(ColonSeparatedToListAdapter.class)
    public List<String> nationNames;

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
    public List<Officer> officers;

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
    public List<String> tags;

    /**
     * This region's statistics of the current or last zombie event.
     */
    @XmlElement(name = "ZOMBIE")
    public ZombieInfo zombieInfo;
}
