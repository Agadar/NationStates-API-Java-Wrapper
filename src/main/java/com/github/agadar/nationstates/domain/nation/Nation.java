package com.github.agadar.nationstates.domain.nation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.agadar.nationstates.adapter.CsvStringToStringSetAdapter;
import com.github.agadar.nationstates.adapter.HappeningSpecializationHelper;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.Dispatch;
import com.github.agadar.nationstates.domain.common.WorldAssemblyBadge;
import com.github.agadar.nationstates.domain.common.ZombieInfo;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.InfluenceRank;
import com.github.agadar.nationstates.enumerator.WorldAssemblyStatus;
import com.github.agadar.nationstates.exception.NationStatesAPIException;

import lombok.Getter;
import lombok.Setter;

/**
 * Representation of a nation. This class' fields have a 1:1 correspondence with
 * the shards in NationShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class Nation {

    /**
     * The pattern used for building URLS that point to images behind Rift codes.
     */
    private final static String BANNER_URL = "https://www.nationstates.net/images/banners/%s.jpg";

    /**
     * The nation's id. Not available in dump file.
     */
    @XmlAttribute(name = "id")
    private String id = "";

    /**
     * One of two adjectives for this nation, e.g. 'cultured', 'safe', etc.
     */
    @XmlElement(name = "ADMIRABLE")
    private String admirable = "";

    /**
     * This nation's national animal.
     */
    @XmlElement(name = "ANIMAL")
    private String animal = "";

    /**
     * Information about this nation's national animal.
     */
    @XmlElement(name = "ANIMALTRAIT")
    private String animalTrait = "";

    /**
     * The Rift banner code of this nation's primary banner, or of a randomly chosen
     * eligible banner if no primary banner is set.
     */
    @XmlElement(name = "BANNER")
    private String banner = "";

    /**
     * The eligible Rift banner codes of this nation.
     */
    @XmlElementWrapper(name = "BANNERS")
    @XmlElement(name = "BANNER")
    private Collection<String> banners = new LinkedHashSet<String>();

    /**
     * This nation's capital. Has default value if none is set.
     */
    @XmlElement(name = "CAPITAL")
    private String capital = "";

    /**
     * This nation's government's category e.g. 'Civil Rights Lovefest'.
     */
    @XmlElement(name = "CATEGORY")
    private String category = "";

    /**
     * This nation's census scale scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    private Collection<CensusScore> census = new LinkedHashSet<CensusScore>();

    /**
     * Description of crime in this nation.
     */
    @XmlElement(name = "CRIME")
    private String crimeDescription = "";

    /**
     * This nation's currency name.
     */
    @XmlElement(name = "CURRENCY")
    private String currency = "";

    /**
     * This nation's leader. Blank if none is set.
     */
    @XmlElement(name = "CUSTOMLEADER")
    private String customLeader = "";

    /**
     * This nation's capital. Blank if none is set.
     */
    @XmlElement(name = "CUSTOMCAPITAL")
    private String customCapital = "";

    /**
     * This nation's religion. Blank if none is set.
     */
    @XmlElement(name = "CUSTOMRELIGION")
    private String customReligion = "";

    /**
     * List of death causes data in this nation.
     */
    @XmlElementWrapper(name = "DEATHS")
    @XmlElement(name = "CAUSE")
    private Collection<DeathCause> deaths = new LinkedHashSet<DeathCause>();

    /**
     * Primary demonym.
     */
    @XmlElement(name = "DEMONYM")
    private String demonym = "";

    /**
     * Secondary demonym.
     */
    @XmlElement(name = "DEMONYM2")
    private String demonym2 = "";

    /**
     * Plural of secondary demonym.
     */
    @XmlElement(name = "DEMONYM2PLURAL")
    private String demonym2Plural = "";

    /**
     * Number of dispatches written by this nation.
     */
    @XmlElement(name = "DISPATCHES")
    private int numberOfDispatches;

    /**
     * This nation's dispatches. Includes factbooks. Does not include dispatches'
     * texts.
     */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    private Collection<Dispatch> dispatches = new LinkedHashSet<Dispatch>();

    /**
     * List of nation names that endorsed this nation.
     */
    @XmlElement(name = "ENDORSEMENTS")
    @XmlJavaTypeAdapter(CsvStringToStringSetAdapter.class)
    private Collection<String> endorsedBy = new LinkedHashSet<String>();

    /**
     * Number of factbooks written by this nation.
     */
    @XmlElement(name = "FACTBOOKS")
    private int numberOfFactbooks;

    /**
     * This nation's factbooks. Subset of Dispatches. Does not include dispatches'
     * texts.
     */
    @XmlElementWrapper(name = "FACTBOOKLIST")
    @XmlElement(name = "FACTBOOK")
    private Collection<Dispatch> factbooks = new LinkedHashSet<Dispatch>();

    /**
     * UNIX timestamp of when the nation first logged in.
     */
    @XmlElement(name = "FIRSTLOGIN")
    private long firstLogin;

    /**
     * URL to the nation's flag image.
     */
    @XmlElement(name = "FLAG")
    private String flagUrl = "";

    /**
     * Description of when this nation was founded.
     */
    @XmlElement(name = "FOUNDED")
    private String foundedDescription = "";

    /**
     * UNIX timestamp of when this nation was founded.
     */
    @XmlElement(name = "FOUNDEDTIME")
    private long founded;

    /**
     * The nation's civil rights, economy, and political freedom scores in text.
     */
    @XmlElement(name = "FREEDOM")
    private Freedom freedom = new Freedom();

    /**
     * The nation's civil rights, economy, and political freedom scores in numbers.
     */
    @XmlElement(name = "FREEDOMSCORES")
    private FreedomScores freedomScores = new FreedomScores();

    /**
     * The nation's full name.
     */
    @XmlElement(name = "FULLNAME")
    private String fullName = "";

    /**
     * The nation's vote for the current General Assembly resolution.
     */
    @XmlElement(name = "GAVOTE")
    private String generalAssemblyVote = "";

    /**
     * The nation's gross domestic product.
     */
    @XmlElement(name = "GDP")
    private int grossDomesticProduct;

    /**
     * Overview in percentages of the government's expenditures.
     */
    @XmlElement(name = "GOVT")
    private GovernmentExpenditure governmentExpenditure = new GovernmentExpenditure();

    /**
     * Elaborate description of the government.
     */
    @XmlElement(name = "GOVTDESC")
    private String governmentDescription = "";

    /**
     * The expenditure the government spends most on.
     */
    @XmlElement(name = "GOVTPRIORITY")
    private String governmentPriority = "";

    /**
     * List of the most recent of this nation's happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    private List<Happening> recentHappenings = new ArrayList<Happening>();

    /**
     * The nation's regional influence rank.
     */
    @XmlElement(name = "INFLUENCE")
    @XmlJavaTypeAdapter(InfluenceRank.Adapter.class)
    private InfluenceRank influence = InfluenceRank.NULL;

    /**
     * The average income of the population.
     */
    @XmlElement(name = "INCOME")
    private int averageIncome;

    /**
     * Elaborate description of this nation's economy.
     */
    @XmlElement(name = "INDUSTRYDESC")
    private String economyDescription = "";

    /**
     * The regional influence of this nation, e.g. 'Apprentice'.
     */
    @XmlElement(name = "INFLUENCE")
    private String regionalInfluence = "";

    /**
     * Description of when this nation was last active.
     */
    @XmlElement(name = "LASTACTIVITY")
    private String lastActivity = "";

    /**
     * UNIX timestamp of when this nation was last logged in.
     */
    @XmlElement(name = "LASTLOGIN")
    private long lastLogin;

    /**
     * This nation's leader. Has default value if none is set.
     */
    @XmlElement(name = "LEADER")
    private String leader = "";

    /**
     * List of this nation's most recent legislations.
     */
    @XmlElementWrapper(name = "LEGISLATION")
    @XmlElement(name = "LAW")
    private List<String> recentLegislation = new ArrayList<String>();

    /**
     * This nation's biggest industry.
     */
    @XmlElement(name = "MAJORINDUSTRY")
    private String majorIndustry = "";

    /**
     * This nation's national motto.
     */
    @XmlElement(name = "MOTTO")
    private String motto = "";

    /**
     * This nation's name. Should be similar to id, but capitalized.
     */
    @XmlElement(name = "NAME")
    private String name = "";

    /**
     * Description of what this nation is most notable for.
     */
    @XmlElement(name = "NOTABLE")
    private String notableFor = "";

    /**
     * Income of the poorest population.
     */
    @XmlElement(name = "POOREST")
    private int poorestIncome;

    /**
     * The population, in millions.
     */
    @XmlElement(name = "POPULATION")
    private int population;

    /**
     * The percentage of the economy that is State-Owned or Government.
     */
    @XmlElement(name = "PUBLICSECTOR")
    private double publicSector;

    /**
     * The nation's regional census rank.
     */
    @XmlElement(name = "RCENSUS")
    private int regionalCensusRank;

    /**
     * Name of the region this nation is in.
     */
    @XmlElement(name = "REGION")
    private String regionName = "";

    /**
     * This nation's religion. Has default value if none is set.
     */
    @XmlElement(name = "RELIGION")
    private String religion = "";

    /**
     * Income of the richest population.
     */
    @XmlElement(name = "RICHEST")
    private int richestIncome;

    /**
     * The nation's vote for the current Security Council resolution.
     */
    @XmlElement(name = "SCVOTE")
    private String securityCouncilVote = "";

    /**
     * Overview in percentages of the government's economy sectors.
     */
    @XmlElement(name = "SECTORS")
    private EconomySectors economySectors = new EconomySectors();

    /**
     * Description of this nation's population.
     */
    @XmlElement(name = "SENSIBILITIES")
    private String populationDescription = "";

    /**
     * The income tax rate, expressed as a percentage.
     */
    @XmlElement(name = "TAX")
    private double averageIncomeTaxRate;

    /**
     * True if the nation is not currently blocking recruitment telegrams.
     */
    @XmlElement(name = "TGCANRECRUIT")
    private boolean canReceiveRecruitmentTelegrams;

    /**
     * True if the nation is not currently blocking campaign telegrams.
     */
    @XmlElement(name = "TGCANCAMPAIGN")
    private boolean canReceiveCampaignTelegrams;

    /**
     * The nation's custom government type, e.g. 'Crowned Confederacy'.
     */
    @XmlElement(name = "TYPE")
    private String governmentType = "";

    /**
     * The World Assembly badges granted to this nation by the Security Council.
     */
    @XmlElementWrapper(name = "WABADGES")
    @XmlElement(name = "WABADGE")
    private Collection<WorldAssemblyBadge> worldAssemblyBadges = new LinkedHashSet<WorldAssemblyBadge>();

    /**
     * String indicating whether the nation is a member of the World Assembly.
     * Corresponds to the 'WA' shard, but its XML-tag is 'UNSTATUS'.
     */
    @XmlElement(name = "UNSTATUS")
    @XmlJavaTypeAdapter(WorldAssemblyStatus.Adapter.class)
    private WorldAssemblyStatus worldAssemblyStatus = WorldAssemblyStatus.NULL;

    /**
     * The nation's world-wide census rank.
     */
    @XmlElement(name = "WCENSUS")
    private int worldCensusRank;

    /**
     * This nation's statistics of the current or last zombie event.
     */
    @XmlElement(name = "ZOMBIE")
    private ZombieInfo zombieInfo = new ZombieInfo();

    /**
     * Gives the URL that points to the image behind Banner.
     *
     * @return the URL that points to the image behind Banner
     */
    public String getBannerAsURL() {
        return riftCodeToURL(banner);
    }

    /**
     * Gives the URLs that point to the images behind Banners.
     *
     * @return the URLs that point to the images behind Banners.
     */
    public Collection<String> getBannersAsURLs() {
        return banners.stream().map(riftCode -> riftCodeToURL(riftCode)).collect(Collectors.toSet());
    }

    /**
     * Builds an URL that points to the image behind the given Rift code.
     *
     * @param riftCode the Rift code to build an URL of
     * @return an URL that points to the image behind the given Rift code
     */
    private static String riftCodeToURL(String riftCode) {
        return riftCode.isEmpty() ? "" : String.format(BANNER_URL, riftCode);
    }

    /**
     * Executed after JAXB finishes unmmarshalling.
     * 
     * @param unmarshaller
     * @param parent
     * @throws NationStatesAPIException If happening specialization failed.
     */
    @SuppressWarnings("unused")
    private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) throws NationStatesAPIException {
        this.recentHappenings = HappeningSpecializationHelper.specializeHappenings(this.recentHappenings);
    }

}
