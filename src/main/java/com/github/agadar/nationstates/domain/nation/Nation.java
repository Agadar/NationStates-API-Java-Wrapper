package com.github.agadar.nationstates.domain.nation;

import com.github.agadar.nationstates.adapter.CommaSeparatedToSetAdapter;
import com.github.agadar.nationstates.domain.common.CensusScore;
import com.github.agadar.nationstates.domain.common.Dispatch;
import com.github.agadar.nationstates.domain.common.Happening;
import com.github.agadar.nationstates.domain.common.ZombieInfo;
import com.github.agadar.nationstates.enumerator.WorldAssemblyStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
 * Representation of a nation. This class' fields have a 1:1 correspondence with
 * the shards in NationShard.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class Nation {

    /**
     * The nation's id, which is always supplied.
     */
    @XmlAttribute(name = "id")
    public String id;

    /**
     * One of two adjectives for this nation, e.g. 'cultured', 'safe', etc.
     */
    @XmlElement(name = "ADMIRABLE")
    public String admirable;

    /**
     * This nation's national animal.
     */
    @XmlElement(name = "ANIMAL")
    public String animal;

    /**
     * Information about this nation's national animal.
     */
    @XmlElement(name = "ANIMALTRAIT")
    public String animalTrait;

    /**
     * The Rift banner code of this nation's primary banner, or of a randomly
     * chosen eligible banner if no primary banner is set.
     */
    @XmlElement(name = "BANNER")
    public String banner;

    /**
     * The eligible Rift banner codes of this nation.
     */
    @XmlElementWrapper(name = "BANNERS")
    @XmlElement(name = "BANNER")
    public Set<String> banners;

    /**
     * This nation's capital. Has default value if none is set.
     */
    @XmlElement(name = "CAPITAL")
    public String capital;

    /**
     * This nation's government's category e.g. 'Civil Rights Lovefest'.
     */
    @XmlElement(name = "CATEGORY")
    public String category;

    /**
     * This nation's census scale scores.
     */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public Set<CensusScore> census;

    /**
     * Description of crime in this nation.
     */
    @XmlElement(name = "CRIME")
    public String crimeDescription;

    /**
     * This nation's currency name.
     */
    @XmlElement(name = "CURRENCY")
    public String currency;

    /**
     * This nation's leader. Blank if none is set.
     */
    @XmlElement(name = "CUSTOMLEADER")
    public String customLeader;

    /**
     * This nation's capital. Blank if none is set.
     */
    @XmlElement(name = "CUSTOMCAPITAL")
    public String customCapital;

    /**
     * This nation's religion. Blank if none is set.
     */
    @XmlElement(name = "CUSTOMRELIGION")
    public String customReligion;

    /**
     * List of death causes data in this nation.
     */
    @XmlElementWrapper(name = "DEATHS")
    @XmlElement(name = "CAUSE")
    public Set<DeathCause> deaths;

    /**
     * Primary demonym.
     */
    @XmlElement(name = "DEMONYM")
    public String demonym;

    /**
     * Secondary demonym.
     */
    @XmlElement(name = "DEMONYM2")
    public String demonym2;

    /**
     * Plural of secondary demonym.
     */
    @XmlElement(name = "DEMONYM2PLURAL")
    public String demonym2Plural;

    /**
     * Number of dispatches written by this nation.
     */
    @XmlElement(name = "DISPATCHES")
    public int numberOfDispatches;

    /**
     * This nations's dispatches. Includes factbooks. Does not include
     * dispatches' texts.
     */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    public Set<Dispatch> dispatches;

    /**
     * List of nation names that endorsed this nation.
     */
    @XmlElement(name = "ENDORSEMENTS")
    @XmlJavaTypeAdapter(CommaSeparatedToSetAdapter.class)
    public Set<String> endorsedBy;

    /**
     * Number of factbooks written by this nation.
     */
    @XmlElement(name = "FACTBOOKS")
    public int numberOfFactbooks;

    /* This nation's factbooks. Subset of Dispatches. Does not include dispatches' texts. */
    @XmlElementWrapper(name = "FACTBOOKLIST")
    @XmlElement(name = "FACTBOOK")
    public Set<Dispatch> factbooks;

    /**
     * UNIX timestamp of when the nation first logged in.
     */
    @XmlElement(name = "FIRSTLOGIN")
    public long firstLogin;

    /**
     * URL to the nation's flag image.
     */
    @XmlElement(name = "FLAG")
    public String flagUrl;

    /**
     * Description of when this nation was founded.
     */
    @XmlElement(name = "FOUNDED")
    public String foundedDescription;

    /**
     * UNIX timestamp of when this nation was founded.
     */
    @XmlElement(name = "FOUNDEDTIME")
    public Long founded;

    /**
     * The nation's civil rights, economy, and political freedoms scores.
     */
    @XmlElement(name = "FREEDOM")
    public Freedom freedom;

    /**
     * The nation's full name.
     */
    @XmlElement(name = "FULLNAME")
    public String fullName;

    /**
     * The nation's vote for the current General Assembly resolution.
     */
    @XmlElement(name = "GAVOTE")
    public String generalAssemblyVote;

    /**
     * The nation's gross domestic product.
     */
    @XmlElement(name = "GDP")
    public int grossDomesticProduct;

    /**
     * Overview in percentages of the government's expenditures.
     */
    @XmlElement(name = "GOVT")
    public GovernmentExpenditure governmentExpenditure;

    /**
     * Elaborate description of the government.
     */
    @XmlElement(name = "GOVTDESC")
    public String governmentDescription;

    /**
     * The expenditure the government spends most on.
     */
    @XmlElement(name = "GOVTPRIORITY")
    public String governmentPriority;

    /**
     * List of the most recent of this nation's happenings.
     */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public SortedSet<Happening> recentHappenings;

    /**
     * The average income of the population.
     */
    @XmlElement(name = "INCOME")
    public int averageIncome;

    /**
     * Elaborate description of this nation's economy.
     */
    @XmlElement(name = "INDUSTRYDESC")
    public String economyDescription;

    /**
     * The regional influence of this nation, e.g. 'Apprentice'.
     */
    @XmlElement(name = "INFLUENCE")
    public String regionalInfluence;

    /**
     * Description of when this nation was last active.
     */
    @XmlElement(name = "LASTACTIVITY")
    public String lastActivity;

    /**
     * UNIX timestamp of when this nation was last logged in.
     */
    @XmlElement(name = "LASTLOGIN")
    public long lastLogin;

    /**
     * This nation's leader. Has default value if none is set.
     */
    @XmlElement(name = "LEADER")
    public String leader;

    /**
     * List of this nation's most recent legislations.
     */
    @XmlElementWrapper(name = "LEGISLATION")
    @XmlElement(name = "LAW")
    public List<String> recentLegislation;

    /**
     * This nation's biggest industry.
     */
    @XmlElement(name = "MAJORINDUSTRY")
    public String majorIndustry;

    /**
     * This nation's national motto.
     */
    @XmlElement(name = "MOTTO")
    public String motto;

    /**
     * This nation's name. Should be similar to id, but capitalized.
     */
    @XmlElement(name = "NAME")
    public String name;

    /**
     * Description of what this nation is most notable for.
     */
    @XmlElement(name = "NOTABLE")
    public String notableFor;

    /**
     * Income of the poorest population.
     */
    @XmlElement(name = "POOREST")
    public int poorestIncome;

    /**
     * The population, in millions.
     */
    @XmlElement(name = "POPULATION")
    public int population;

    /**
     * The percentage of the economy that is State-Owned or Government.
     */
    @XmlElement(name = "PUBLICSECTOR")
    public double publicSector;

    /**
     * The nation's regional census rank.
     */
    @XmlElement(name = "RCENSUS")
    public int regionalCensusRank;

    /**
     * Name of the region this nation is in.
     */
    @XmlElement(name = "REGION")
    public String regionName;

    /**
     * This nation's religion. Has default value if none is set.
     */
    @XmlElement(name = "RELIGION")
    public String religion;

    /**
     * Income of the richest population.
     */
    @XmlElement(name = "RICHEST")
    public int richestIncome;

    /**
     * The nation's vote for the current Security Council resolution.
     */
    @XmlElement(name = "SCVOTE")
    public String securityCouncilVote;

    /**
     * Overview in percentages of the government's economy sectors.
     */
    @XmlElement(name = "SECTORS")
    public EconomySectors economySectors;

    /**
     * Description of this nation's population.
     */
    @XmlElement(name = "SENSIBILITIES")
    public String populationDescription;

    /**
     * The income tax rate, expressed as a percentage.
     */
    @XmlElement(name = "TAX")
    public double averageIncomeTaxRate;

    /**
     * True if the nation is not currently blocking recruitment telegrams.
     */
    @XmlElement(name = "TGCANRECRUIT")
    public boolean canReceiveRecruitmentTelegrams;

    /**
     * True if the nation is not currently blocking campaign telegrams.
     */
    @XmlElement(name = "TGCANCAMPAIGN")
    public boolean canReceiveCampaignTelegrams;

    /**
     * The nation's custom government type, e.g. 'Crowned Confederacy'.
     */
    @XmlElement(name = "TYPE")
    public String governmentType;

    /**
     * String indicating whether the nation is a member of the World Assembly.
     * Corresponds to the 'WA' shard, but its XML-tag is 'UNSTATUS'.
     */
    @XmlElement(name = "UNSTATUS")
    @XmlJavaTypeAdapter(WorldAssemblyStatus.Adapter.class)
    public WorldAssemblyStatus worldAssemblyStatus;

    /**
     * The nation's world-wide census rank.
     */
    @XmlElement(name = "WCENSUS")
    public int worldCensusRank;

    /**
     * This nation's statistics of the current or last zombie event.
     */
    @XmlElement(name = "ZOMBIE")
    public ZombieInfo zombieInfo;

    /**
     * The pattern used for building URLS that point to images behind Rift
     * codes.
     */
    private final static String BANNER_URL = "https://www.nationstates.net/images/banners/%s.jpg";

    /**
     * Gives the URL that points to the image behind Banner.
     *
     * @return the URL that points to the image behind Banner
     */
    public String bannerAsURL() {
        return riftCodeToURL(banner);
    }

    /**
     * Gives the URLs that point to the images behind Banners.
     *
     * @return the URLs that point to the images behind Banners.
     */
    public Set<String> bannersAsURLs() {
        final Set<String> urls = new HashSet<>();

        if (banners == null) {
            return urls;
        }
        banners.forEach(riftCode -> urls.add(riftCodeToURL(riftCode)));
        return urls;
    }

    /**
     * Builds an URL that points to the image behind the given Rift code.
     *
     * @param riftCode the Rift code to build an URL of
     * @return an URL that points to the image behind the given Rift code
     */
    private static String riftCodeToURL(String riftCode) {
        return riftCode == null || riftCode.isEmpty() ? null : String.format(BANNER_URL, riftCode);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nation other = (Nation) obj;
        return Objects.equals(this.id, other.id);
    }

}
