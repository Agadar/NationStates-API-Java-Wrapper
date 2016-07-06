package com.github.agadar.nsapi.domain.nation;

import com.github.agadar.nsapi.adapter.CommaSeparatedToListAdapter;
import com.github.agadar.nsapi.domain.shared.CensusScore;
import com.github.agadar.nsapi.domain.shared.Dispatch;
import com.github.agadar.nsapi.domain.shared.Happening;
import com.github.agadar.nsapi.domain.shared.ZombieInfo;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a nation. This class's fields have a 1:1 correspondence 
 * with the shards in NationShard.java.
 *
 * @author Agadar <https://github.com/Agadar/>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class Nation
{
    /** One of two adjectives for this nation, e.g. 'cultured', 'safe', etc. */
    @XmlElement(name = "ADMIRABLE")
    public String Admirable;
    
    /** This nation's national animal. */
    @XmlElement(name = "ANIMAL")
    public String Animal;
    
    /** Information about this nation's national animal. */
    @XmlElement(name = "ANIMALTRAIT")
    public String AnimalTrait;
    
    /** The Rift banner code of this nation's primary banner, or of a randomly
        chosen eligible banner if no primary banner is set.*/
    @XmlElement(name = "BANNER")
    public String Banner;
    
    /** The eligible Rift banner codes of this nation. */
    @XmlElementWrapper(name = "BANNERS")
    @XmlElement(name = "BANNER")
    public List<String> Banners;
    
    /** This nation's capital. Has default value if none is set. */
    @XmlElement(name="CAPITAL")
    public String Capital;
    
    /** This nation's government's category e.g. 'Civil Rights Lovefest'. */
    @XmlElement(name="CATEGORY")
    public String Category;
    
    /** This nation's census scale scores. */
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public List<CensusScore> Census;
    
    /** Description of crime in this nation. */
    @XmlElement(name = "CRIME")
    public String CrimeDescription;
    
    /** This nation's currency name. */
    @XmlElement(name = "CURRENCY")
    public String Currency;
    
    /** This nation's leader. Blank if none is set. */
    @XmlElement(name = "CUSTOMLEADER")
    public String CustomLeader;
    
    /** This nation's capital. Blank if none is set. */
    @XmlElement(name = "CUSTOMCAPITAL")
    public String CustomCapital;
    
    /** This nation's religion. Blank if none is set. */
    @XmlElement(name = "CUSTOMRELIGION")
    public String CustomReligion;
    
    /** List of death causes data in this nation. */
    @XmlElementWrapper(name = "DEATHS")
    @XmlElement(name = "CAUSE")
    public List<DeathCause> Deaths;
    
    /** Primary demonym. */
    @XmlElement(name = "DEMONYM")
    public String Demonym;
    
    /** Secondary demonym. */
    @XmlElement(name = "DEMONYM2")
    public String Demonym2;
    
    /** Plural of secondary demonym. */
    @XmlElement(name = "DEMONYM2PLURAL")
    public String Demonym2Plural;
    
    /** Number of dispatches written by this nation. */
    @XmlElement(name = "DISPATCHES")
    public int NumberOfDispatches;
    
    /** This nations's dispatches. Includes factbooks. Does not include dispatches' texts. */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    public List<Dispatch> Dispatches;
    
    /** List of nation names that endorsed this nation. */
    @XmlElement(name = "ENDORSEMENTS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> EndorsedBy;
    
    /** Number of factbooks written by this nation. */
    @XmlElement(name = "FACTBOOKS")
    public int NumberOfFactbooks;
    
    /* This nation's factbooks. Subset of Dispatches. Does not include dispatches' texts. */
    @XmlElementWrapper(name = "FACTBOOKLIST")
    @XmlElement(name = "FACTBOOK")
    public List<Dispatch> Factbooks;
    
    /** UNIX timestamp of when the nation first logged in. */
    @XmlElement(name = "FIRSTLOGIN")
    public long FirstLogin;
    
    /** URL to the nation's flag image. */
    @XmlElement(name = "FLAG")
    public String FlagUrl;
    
    /** Description of when this nation was founded. */
    @XmlElement(name = "FOUNDED")
    public String Founded;
    
    /** The nation's civil rights, economy, and political freedoms scores. */
    @XmlElement(name = "FREEDOM")
    public Freedom Freedom;
    
    /** The nation's full name. */
    @XmlElement(name = "FULLNAME")
    public String FullName;
    
    /** The nation's vote for the current General Assembly resolution. */
    @XmlElement(name = "GAVOTE")
    public String GeneralAssemblyVote;
    
    /** The nation's gross domestic product. */
    @XmlElement(name = "GDP")
    public int GrossDomesticProduct;
    
    /** Overview in percentages of the government's expenditures. */
    @XmlElement(name = "GOVT")
    public GovernmentExpenditure GovernmentExpenditure;
    
    /** Elaborate description of the government. */
    @XmlElement(name = "GOVTDESC")
    public String GovernmentDescription;
    
    /** The expenditure the government spends most on. */
    @XmlElement(name = "GOVTPRIORITY")
    public String GovernmentPriority;
    
    /** List of the most recent of this nation's happenings. */
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public List<Happening> RecentHappenings;
    
    /** The average income of the population. */
    @XmlElement(name = "INCOME")
    public int AverageIncome;
    
    /** Elaborate description of this nation's economy. */
    @XmlElement(name = "INDUSTRYDESC")
    public String EconomyDescription;
    
    /** The regional influence of this nation, e.g. 'Apprentice'. */
    @XmlElement(name = "INFLUENCE")
    public String RegionalInfluence;
    
    /** Description of when this nation was last active. */
    @XmlElement(name = "LASTACTIVITY")
    public String LastActivity;
    
    /** UNIX timestamp of when this nation was last logged in. */
    @XmlElement(name = "LASTLOGIN")
    public long LastLogin;
    
    /** This nation's leader. Has default value if none is set. */ 
    @XmlElement(name = "LEADER")
    public String Leader;
    
    /** List of this nation's most recent legislations. */
    @XmlElementWrapper(name = "LEGISLATION")
    @XmlElement(name = "LAW")
    public List<String> RecentLegislation;
    
    /** This nation's biggest industry. */
    @XmlElement(name = "MAJORINDUSTRY")
    public String MajorIndustry;
    
    /** This nation's national motto. */
    @XmlElement(name = "MOTTO")
    public String Motto;
    
    /** This nation's name. */
    @XmlElement(name = "NAME")
    public String Name;
    
    /** Description of what this nation is most notable for. */
    @XmlElement(name = "NOTABLE")
    public String NotableFor;
    
    /** Income of the poorest population. */
    @XmlElement(name = "POOREST")
    public int PoorestIncome;
    
    /** The population, in millions. */
    @XmlElement(name = "POPULATION")
    public int Population;
    
    /** The percentage of the economy that is State-Owned or Government. */
    @XmlElement(name = "PUBLICSECTOR")
    public double PublicSector;
    
    /** The nation's regional census rank. */
    @XmlElement(name = "RCENSUS")
    public int CensusRankInRegion;
    
    /** Name of the region this nation is in. */
    @XmlElement(name = "REGION")
    public String RegionName;
    
    /** This nation's religion. Has default value if none is set. */ 
    @XmlElement(name = "RELIGION")
    public String Religion;
    
    /** Income of the richest population. */
    @XmlElement(name = "RICHEST")
    public int RichestIncome;
    
    /** The nation's vote for the current Security Council resolution. */
    @XmlElement(name = "SCVOTE")
    public String SecurityCouncilVote;
    
    /** Overview in percentages of the government's economy sectors. */
    @XmlElement(name = "SECTORS")
    public EconomySectors EconomySectors;
    
    /** Description of this nation's population. */
    @XmlElement(name = "SENSIBILITIES")
    public String PopulationDescription;
    
    /** The income tax rate, expressed as a percentage. */
    @XmlElement(name = "TAX")
    public double AverageIncomeTaxRate;
    
    /** True if the nation is not currently blocking recruitment telegrams. */
    @XmlElement(name = "TGCANRECRUIT")
    public boolean CanReceiveRecruitmentTelegrams;
    
    /** True if the nation is not currently blocking campaign telegrams. */
    @XmlElement(name = "TGCANCAMPAIGN")
    public boolean CanReceiveCampaignTelegrams;
    
    /** The nation's custom government type, e.g. 'Crowned Confederacy'. */
    @XmlElement(name = "TYPE")
    public String GovernmentType;
    
    /** String indicating whether the nation is a member of the World Assembly. 
    *   Corresponds to the 'WA' shard, but its XML-tag is 'UNSTATUS'. */
    @XmlElement(name = "UNSTATUS")
    public String WorldAssemblyStatus;
    
    /** The nation's world-wide census rank. */
    @XmlElement(name = "WCENSUS")
    public int CensusRankInWorld;
    
    /** This nation's statistics of the current or last zombie event. */
    @XmlElement(name = "ZOMBIE")
    public ZombieInfo ZombieInfo;
    
    /** The pattern used for building URLS that point to images behind Rift codes. */
    private final static String BANNER_URL = "https://www.nationstates.net/images/banners/%s.jpg";
    
    /**
     * Builds an URL that points to the image behind the given Rift code.
     *
     * @param riftCode the Rift code to build an URL of
     * @return an URL that points to the image behind the given Rift code
     */
    private String riftCodeToURL(String riftCode)
    {
        return riftCode == null || riftCode.isEmpty() ? null : 
               String.format(BANNER_URL, riftCode);
    }
    
    /**
     * Gives the URL that points to the image behind Banner.
     * 
     * @return the URL that points to the image behind Banner
     */
    public String BannerAsURL()
    {
        return riftCodeToURL(Banner);
    }
    
    /**
     * Gives the URLs that point to the images behind Banners.
     * 
     * @return the URLs that point to the images behind Banners.
     */
    public List<String> BannersAsURLs()
    {
        if (Banners == null)
        {
            return null;
        }
        
        List<String> urls = new ArrayList<>();       
        Banners.forEach(riftCode -> urls.add(riftCodeToURL(riftCode)));       
        return urls;
    }

    @Override
    public String toString()
    {
        return "Nation{" + "Admirable=" + Admirable + ", Animal=" + Animal +
               ", AnimalTrait=" + AnimalTrait + ", Banner=" + Banner +
               ", Banners=" + Banners + ", Capital=" + Capital + ", Category=" +
               Category + ", Census=" + Census + ", CrimeDescription=" +
               CrimeDescription + ", Currency=" + Currency + ", CustomLeader=" +
               CustomLeader + ", CustomCapital=" + CustomCapital +
               ", CustomReligion=" + CustomReligion + ", Deaths=" + Deaths +
               ", Demonym=" + Demonym + ", Demonym2=" + Demonym2 +
               ", Demonym2Plural=" + Demonym2Plural + ", NumberOfDispatches=" +
               NumberOfDispatches + ", Dispatches=" + Dispatches +
               ", EndorsedBy=" + EndorsedBy + ", NumberOfFactbooks=" +
               NumberOfFactbooks + ", Factbooks=" + Factbooks + ", FirstLogin=" +
               FirstLogin + ", FlagUrl=" + FlagUrl + ", Founded=" + Founded +
               ", Freedom=" + Freedom + ", FullName=" + FullName +
               ", GeneralAssemblyVote=" + GeneralAssemblyVote +
               ", GrossDomesticProduct=" + GrossDomesticProduct +
               ", GovernmentExpenditure=" + GovernmentExpenditure +
               ", GovernmentDescription=" + GovernmentDescription +
               ", GovernmentPriority=" + GovernmentPriority +
               ", RecentHappenings=" + RecentHappenings + ", AverageIncome=" +
               AverageIncome + ", EconomyDescription=" + EconomyDescription +
               ", RegionalInfluence=" + RegionalInfluence + ", LastActivity=" +
               LastActivity + ", LastLogin=" + LastLogin + ", Leader=" + Leader +
               ", RecentLegislation=" + RecentLegislation + ", MajorIndustry=" +
               MajorIndustry + ", Motto=" + Motto + ", Name=" + Name +
               ", NotableFor=" + NotableFor + ", PoorestIncome=" + PoorestIncome +
               ", Population=" + Population + ", PublicSector=" + PublicSector +
               ", CensusRankInRegion=" + CensusRankInRegion + ", RegionName=" +
               RegionName + ", Religion=" + Religion + ", RichestIncome=" +
               RichestIncome + ", SecurityCouncilVote=" + SecurityCouncilVote +
               ", EconomySectors=" + EconomySectors + ", PopulationDescription=" +
               PopulationDescription + ", AverageIncomeTaxRate=" +
               AverageIncomeTaxRate + ", CanReceiveRecruitmentTelegrams=" +
               CanReceiveRecruitmentTelegrams + ", CanReceiveCampaignTelegrams=" +
               CanReceiveCampaignTelegrams + ", GovernmentType=" + GovernmentType +
               ", WorldAssemblyStatus=" + WorldAssemblyStatus +
               ", CensusRankInWorld=" + CensusRankInWorld + ", ZombieInfo=" +
               ZombieInfo + '}';
    }
    
    
}
