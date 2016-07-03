package com.github.agadar.nsapi.domain;

import com.github.agadar.nsapi.adapter.CommaSeparatedToListAdapter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Representation of a nation.
 *
 * @author Martin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "NATION")
public class Nation
{
    @XmlElement(name = "ADMIRABLE")
    public String Admirable;
    
    @XmlElement(name = "ANIMAL")
    public String Animal;
    
    @XmlElement(name = "ANIMALTRAIT")
    public String AnimalTrait;
    
    @XmlElement(name = "BANNER")
    public String Banner;
    
    @XmlElementWrapper(name = "BANNERS")
    @XmlElement(name = "BANNER")
    public List<String> Banners;
    
    @XmlElement(name="CAPITAL")
    public String Capital;
    
    @XmlElement(name="CATEGORY")
    public String Category;
    
    @XmlElementWrapper(name = "CENSUS")
    @XmlElement(name = "SCALE")
    public List<CensusScale> Census;
    
    @XmlElement(name = "CRIME")
    public String CrimeDescription;
    
    @XmlElement(name = "CURRENCY")
    public String Currency;
    
    @XmlElement(name = "CUSTOMLEADER")
    public String CustomLeader;
    
    @XmlElement(name = "CUSTOMCAPITAL")
    public String CustomCapital;
    
    @XmlElement(name = "CUSTOMRELIGION")
    public String CustomReligion;
    
    @XmlElementWrapper(name = "DEATHS")
    @XmlElement(name = "CAUSE")
    public List<DeathCause> Deaths;
    
    @XmlElement(name = "DEMONYM")
    public String Demonym;
    
    @XmlElement(name = "DEMONYM2")
    public String Demonym2;
    
    @XmlElement(name = "DEMONYM2PLURAL")
    public String Demonym2Plural;
    
    @XmlElement(name = "DISPATCHES")
    public int NumberOfDispatches;
    
    /** This nations's dispatches. Includes factbooks. */
    @XmlElementWrapper(name = "DISPATCHLIST")
    @XmlElement(name = "DISPATCH")
    public List<Dispatch> Dispatches;
    
    @XmlElement(name = "ENDORSEMENTS")
    @XmlJavaTypeAdapter(CommaSeparatedToListAdapter.class)
    public List<String> Endorsements;
    
    @XmlElement(name = "FACTBOOKS")
    public int NumberOfFactbooks;
    
    @XmlElementWrapper(name = "FACTBOOKLIST")
    @XmlElement(name = "FACTBOOK")
    public List<Dispatch> Factbooks;
    
    @XmlElement(name = "FIRSTLOGIN")
    public long FirstLogin;
    
    @XmlElement(name = "FLAG")
    public String FlagUrl;
    
    @XmlElement(name = "FOUNDED")
    public String Founded;
    
    @XmlElement(name = "FREEDOM")
    public Freedom Freedom;
    
    @XmlElement(name = "FULLNAME")
    public String FullName;
    
    @XmlElement(name = "GAVOTE")
    public String GeneralAssemblyVote;
    
    @XmlElement(name = "GDP")
    public int GrossDomesticProduct;
    
    @XmlElement(name = "GOVT")
    public GovernmentExpenditure GovernmentExpenditure;
    
    @XmlElement(name = "GOVTDESC")
    public String GovernmentDescription;
    
    @XmlElement(name = "GOVTPRIORITY")
    public String GovernmentPriority;
    
    @XmlElementWrapper(name = "HAPPENINGS")
    @XmlElement(name = "EVENT")
    public List<Happening> RecentHappenings;
    
    @XmlElement(name = "INCOME")
    public int AverageIncome;
    
    @XmlElement(name = "INDUSTRYDESC")
    public String IndustryDescription;
    
    @XmlElement(name = "INFLUENCE")
    public String RegionalInfluence;
    
    @XmlElement(name = "LASTACTIVITY")
    public String LastActivity;
    
    @XmlElement(name = "LASTLOGIN")
    public long LastLogin;
    
    @XmlElement(name = "LEADER")
    public String Leader;
    
    @XmlElementWrapper(name = "LEGISLATION")
    @XmlElement(name = "LAW")
    public List<String> RecentLegislation;
    
    @XmlElement(name = "MAJORINDUSTRY")
    public String MajorIndustry;
    
    @XmlElement(name = "MOTTO")
    public String Motto;
    
    @XmlElement(name = "NAME")
    public String Name;
    
    @XmlElement(name = "NOTABLE")
    public String NotableFor;
    
    @XmlElement(name = "POOREST")
    public int PoorestIncome;
    
    @XmlElement(name = "POPULATION")
    public int Population; // 14.000 -> 14 billion
    
    @XmlElement(name = "PUBLICSECTOR")
    public double PublicSector;
    
    @XmlElement(name = "RCENSUS")
    public int CensusRankInRegion;
    
    @XmlElement(name = "REGION")
    public String RegionName;
    
    @XmlElement(name = "RELIGION")
    public String Religion;
    
    @XmlElement(name = "RICHEST")
    public int RichestIncome;
    
    @XmlElement(name = "SCVOTE")
    public String SecurityCouncilVote;
    
    @XmlElement(name = "SECTORS")
    public EconomySectors EconomySectors;
    
    @XmlElement(name = "SENSIBILITIES")
    public String PopulationDescription;
    
    @XmlElement(name = "TAX")
    public double AverageIncomeTaxRate;
    
    /** True if the nation is not currently blocking recruitment telegrams */
    @XmlElement(name = "TGCANRECRUIT")
    public boolean CanReceiveRecruitmentTelegrams;
    
    /** True if the nation is not currently blocking campaign telegrams */
    @XmlElement(name = "TGCANCAMPAIGN")
    public boolean CanReceiveCampaignTelegrams;
    
    @XmlElement(name = "TYPE")
    public String GovernmentType;
    
    /** String indicating whether the nation is a member of the World Assembly. 
    *   Corresponds to the 'WA' shard, but its XML-tag is 'UNSTATUS'. */
    @XmlElement(name = "UNSTATUS")
    public String WorldAssemblyStatus;
    
    @XmlElement(name = "WCENSUS")
    public int CensusRankInWorld;
    
    /** This nation's statistics of the current or last zombie event. */
    @XmlElement(name = "ZOMBIE")
    public ZombieInfo ZombieInfo;
}
