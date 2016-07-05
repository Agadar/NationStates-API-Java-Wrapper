package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for Nation requests. These shards have a 1:1 correspondence 
 * with the variable fields in Nation.java.
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public enum NationShard
{
    /** One of two adjectives for this nation, e.g. 'cultured', 'safe', etc. */
    Admirable("ADMIRABLE"),
    /** This nation's national animal */
    Animal("ANIMAL"),
    /** Information about this nation's national animal */
    AnimalTrait("ANIMALTRAIT"),
    /** The Rift banner code of this nation's primary banner, or of a randomly
        chosen eligible banner if no primary banner is set. Can be prepended
        by '/images/banners/' and appended by '.jpg' to convert into an image URL.*/
    Banner("BANNER"),
    /** The eligible Rift banner codes of this nation. Codes can be prepended
        by '/images/banners/' and appended by '.jpg' to convert into image URLs. */
    Banners("BANNERS"),
    /** This nation's capital. Has default value if none is set. */
    Capital("CAPITAL"),
    /** This nation's government's category e.g. 'Civil Rights Lovefest' */
    Category("CATEGORY"),
    /** This nation's census scale scores */
    Census("CENSUS"),
    /** Description of crime in this nation */
    CrimeDescription("CRIME"),
    /** This nation's currency name */
    Currency("CURRENCY"),
    /** This nation's leader. Blank if none is set. */
    CustomLeader("CUSTOMLEADER"),
    /** This nation's capital. Blank if none is set. */
    CustomCapital("CUSTOMCAPITAL"),
    /** This nation's religion. Blank if none is set. */
    CustomReligion("CUSTOMRELIGION"),
    /** List of death causes data in this nation */
    Deaths("DEATHS"),
    /** Primary demonym */
    Demonym("DEMONYM"),
    /** Secondary demonym */
    Demonym2("DEMONYM2"),
    /** Plural of secondary demonym */
    Demonym2Plural("DEMONYM2PLURAL"),
    /** Number of dispatches written by this nation */
    NumberOfDispatches("DISPATCHES"),
    /** This nations's dispatches. Includes factbooks. */
    Dispatches("DISPATCHLIST"),
    /** List of nation names that endorsed this nation */
    EndorsedBy("ENDORSEMENTS"),
    /** Number of factbooks written by this nation */
    NumberOfFactbooks("FACTBOOKS"),
    /* This nation's factbooks. Subset of Dispatches. */
    Factbooks("FACTBOOKLIST"),
    /** UNIX timestamp of when the nation first logged in */
    FirstLogin("FIRSTLOGIN"),
    /** URL to the nation's flag image */
    FlagUrl("FLAG"),
    /** Description of when this nation was founded */
    Founded("FOUNDED"),
    /** The nation's civil rights, economy, and political freedoms scores */
    Freedom("FREEDOM"),
    /** The nation's full name */
    FullName("FULLNAME"),
    /** The nation's vote for the current General Assembly resolution */
    GeneralAssemblyVote("GAVOTE"),
    /** The nation's gross domestic product */
    GrossDomesticProduct("GDP"),
    /** Overview in percentages of the government's expenditures */
    GovernmentExpenditure("GOVT"),
    /** Elaborate description of the government */
    GovernmentDescription("GOVTDESC"),
    /** The expenditure the government spends most on */
    GovernmentPriority("GOVTPRIORITY"),
    /** List of the most recent of this nation's happenings */
    RecentHappenings("HAPPENINGS"),
    /** The average income of the population */
    AverageIncome("INCOME"),
    /** Elaborate description of this nation's economy */
    EconomyDescription("INDUSTRYDESC"),
    /** The regional influence of this nation, e.g. 'Apprentice' */
    RegionalInfluence("INFLUENCE"),
    /** Description of when this nation was last active */
    LastActivity("LASTACTIVITY"),
    /** UNIX timestamp of when this nation was last logged in */
    LastLogin("LASTLOGIN"),
    /** This nation's leader. Has default value if none is set. */ 
    Leader("LEADER"),
    /** List of this nation's most recent legislations */
    RecentLegislation("LEGISLATION"),
    /** This nation's biggest industry */
    MajorIndustry("MAJORINDUSTRY"),
    /** This nation's national motto */
    Motto("MOTTO"),
    /** This nation's name */
    Name("NAME"),
    /** Description of what this nation is most notable for */
    NotableFor("NOTABLE"),
    /** Income of the poorest population */
    PoorestIncome("POOREST"),
    /** The population, in millions */
    Population("POPULATION"),
    /** The percentage of the economy that is State-Owned or Government */
    PublicSector("PUBLICSECTOR"),
    /** The nation's regional census rank */
    CensusRankInRegion("RCENSUS"),
    /** Name of the region this nation is in */
    RegionName("REGION"),
    /** This nation's religion. Has default value if none is set. */ 
    Religion("RELIGION"),
    /** Income of the richest population */
    RichestIncome("RICHEST"),
    /** The nation's vote for the current Security Council resolution */
    SecurityCouncilVote("SCVOTE"),
    /** Overview in percentages of the government's economy sectors */
    EconomySectors("SECTORS"),
    /** Description of this nation's population */
    PopulationDescription("SENSIBILITIES"),
    /** The income tax rate, expressed as a percentage */
    AverageIncomeTaxRate("TAX"),
    /** True if the nation is not currently blocking recruitment telegrams */
    CanReceiveRecruitmentTelegrams("TGCANRECRUIT"),
    /** True if the nation is not currently blocking campaign telegrams */
    CanReceiveCampaignTelegrams("TGCANCAMPAIGN"),
    /** The nation's custom government type, e.g. 'Crowned Confederacy' */
    GovernmentType("TYPE"),
    /** String indicating whether the nation is a member of the World Assembly. 
    *   Corresponds to the 'WA' shard, but its XML-tag is 'UNSTATUS'. */
    WorldAssemblyStatus("WA"),
    /** The nation's world-wide census rank */
    CensusRankInWorld("WCENSUS"),
    /** This nation's statistics of the current or last zombie event. */
    ZombieInfo("ZOMBIE");
    
    /** The underlying shard name */
    private final String shardName;
    
    /**
     * Instantiate a new entry with the given shard name.
     * 
     * @param shardName the name of the underlying shard
     */
    private NationShard(String shardName) 
    {
        this.shardName = shardName;
    }

    /**
     * Return the underlying shard name.
     * 
     * @return the underlying shard name
     */
    @Override
    public String toString() 
    {
        return shardName;
    }
}
