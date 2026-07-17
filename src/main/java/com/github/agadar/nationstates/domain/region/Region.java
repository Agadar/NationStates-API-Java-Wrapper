package com.github.agadar.nationstates.domain.region;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import com.github.agadar.nationstates.adapter.ColonStringToStringSetAdapter;
import com.github.agadar.nationstates.adapter.CsvStringToLongSetAdapter;
import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.adapter.StringToInstantAdapter;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.NationCensusScoreRanks;
import com.github.agadar.nationstates.domain.common.Poll;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.common.ZombieInfo;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.Authority;
import com.github.agadar.nationstates.enumerator.EmbassiesRmbPermissions;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
     * The region's id. Same as the name, but with underscores instead of spaces and in all lower-case.
     * <p>
     * Not available in the standard (shard-less) call nor in the dump file.
     */
    @XmlAttribute(name = "id")
    private String id = "";

    /**
     * List of banned nations.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "BANNED")
    @XmlJavaTypeAdapter(ColonStringToStringSetAdapter.class)
    private LinkedHashSet<String> banlist = new LinkedHashSet<>();

    /**
     * Id of the regional banner.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "BANNER")
    private String banner = "";

    /**
     * Name of the nation that set the regional banner.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "BANNERBY")
    private String bannerBy = "";
    
    /**
     * Full path of the regional banner.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "BANNERURL")
    private String bannerUrl = "";

    /**
     * This region's census scale scores.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    private LinkedHashSet<CensusScore> census = new LinkedHashSet<>();

    /**
     * The census scale scores of this region's nations.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "CENSUSRANKS")
    private NationCensusScoreRanks censusRanks = new NationCensusScoreRanks();

    /**
     * Database id of the region.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "DBID")
    private String dbid = "";

    /**
     * The name of the nation that is the region's world assembly delegate. Returns
     * '0' if no delegate exists.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "DELEGATE")
    private String delegate = "";

    /**
     * The authorities granted to the region's world assembly delegate.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "DELEGATEAUTH")
    @XmlJavaTypeAdapter(Authority.Adapter.class)
    private LinkedHashSet<Authority> delegateAuthorities = new LinkedHashSet<>();

    /**
     * The number of endorsements the region's world assembly delegate has.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "DELEGATEVOTES")
    private int delegateEndorsements;
    
    /**
     * The unique identifiers of this region's pinned dispatches.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "DISPATCHES")
    @XmlJavaTypeAdapter(CsvStringToLongSetAdapter.class)
    private LinkedHashSet<Long> dispatches = new LinkedHashSet<>();

    /**
     * The region's embassies.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElementWrapper(name = "EMBASSIES")
    @XmlElement(name = "EMBASSY")
    private LinkedHashSet<Embassy> embassies = new LinkedHashSet<>();

    /**
     * Regional Message Board permissions for regions with which this region
     * exchanges embassies.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "EMBASSYRMB")
    @XmlJavaTypeAdapter(EmbassiesRmbPermissions.Adapter.class)
    private EmbassiesRmbPermissions embassiesRmbPermissions = EmbassiesRmbPermissions.NULL;

    /**
     * The complete mark-up of the regional factbook.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "FACTBOOK")
    private String factbook = "";

    /**
     * URL to the region's flag image.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "FLAG")
    private String flagUrl = "";

    /**
     * Description of when this region was founded.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "FOUNDED")
    private String foundedDescription = "";

    /**
     * UNIX timestamp of when this region was founded.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "FOUNDEDTIME")
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant founded;

    /**
     * The name of the region's founding nation. Returns '0' if none exists.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "FOUNDER")
    private String founder = "";

    /**
     * If this region is a frontier region or not.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "FRONTIER")
    private boolean frontier;

    /**
     * The region's votes for the current General Assembly resolution.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "GAVOTE")
    private WorldAssemblyVote generalAssemblyVote = new WorldAssemblyVote();
    
    /**
     * The region's governor. Returns '0' if none exists.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "GOVERNOR")
    private String governor;

    /**
     * The region's governor's title. Empty if no regional governor exists.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "GOVERNORTITLE")
    private String governorTitle;
    
    /**
     * List of the most recent of this region's happenings.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    private ArrayList<Happening> recentHappenings = new ArrayList<>();

    /**
     * List of the most recent history.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElementWrapper(name = "HISTORY")
    @XmlElement(name = "EVENT")
    private ArrayList<Happening> history = new ArrayList<>();

    /**
     * UNIX timestamp of when this region was last updated.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "LASTUPDATE")
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant lastUpdate;

    /**
     * UNIX timestamp of when this region was last majorly updated.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "LASTMAJORUPDATE")
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant lastMajorUpdate;
    
    /**
     * UNIX timestamp of when this region was last minorly updated.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "LASTMINORUPDATE")
    @XmlJavaTypeAdapter(StringToInstantAdapter.class)
    private Instant lastMinorUpdate;

    /**
     * The region's magnetism. See https://www.nationstates.net/page=faq#magnetism.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "MAGNETISM")
    private float magnetism;

    /**
     * List of 10 most recent regional messages.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElementWrapper(name = "MESSAGES")
    @XmlElement(name = "POST")
    private ArrayList<RegionalMessage> regionalMessages = new ArrayList<>();
    
    /**
     * The region's name.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * List of nations that inhabit this region.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "NATIONS")
    @XmlJavaTypeAdapter(ColonStringToStringSetAdapter.class)
    private LinkedHashSet<String> nationNames = new LinkedHashSet<>();

    /**
     * The number of nations that inhabit this region.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "NUMNATIONS")
    private int numberOfNations;
    
    /**
     * The region's nations that are members of the World Assembly.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "WANATIONS")
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private LinkedHashSet<String> worldAssemblyNations = new LinkedHashSet<>();

    /**
     * The number of the region's nations that are members of the World Assembly.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "NUMWANATIONS")
    private int numberOfWorldAssemblyNations;

    /**
     * List of regional officers.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElementWrapper(name = "OFFICERS")
    @XmlElement(name = "OFFICER")
    private LinkedHashSet<Officer> officers = new LinkedHashSet<>();

    /**
     * The regional poll that is currently being conducted.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "POLL")
    private Poll currentPoll = new Poll();

    /**
     * The region's power.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "POWER")
    private String power = "";
    
    /**
     * The nations that are allowed to send recruitment telegrams for the region.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElement(name = "RECRUITERS")
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private LinkedHashSet<String> recruiters = new LinkedHashSet<>();

    /**
     * The region's votes for the current Security Council resolution.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "SCVOTE")
    private WorldAssemblyVote securityCouncilVote = new WorldAssemblyVote();

    /**
     * The region's tags.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElementWrapper(name = "TAGS")
    @XmlElement(name = "TAG")
    private LinkedHashSet<String> tags = new LinkedHashSet<>();

    /**
     * The World Assembly badges granted to this region by the Security Council.
     * <p>
     * Available in the standard (shard-less) call and in the dump file.
     */
    @XmlElementWrapper(name = "WABADGES")
    @XmlElement(name = "WABADGE")
    private LinkedHashSet<WorldAssemblyBadge> worldAssemblyBadges = new LinkedHashSet<>();

    /**
     * This region's statistics of the current or last zombie event.
     * <p>
     * Not available in the standard (shard-less) call, nor in the dump file.
     */
    @XmlElement(name = "ZOMBIE")
    private ZombieInfo zombieInfo = new ZombieInfo();

    /**
     * Executed after JAXB finishes unmarshalling.
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        this.recentHappenings = HappeningSpecializationHelper.specializeHappenings(this.recentHappenings);
        this.history = HappeningSpecializationHelper.specializeHappenings(this.history);
    }
}
