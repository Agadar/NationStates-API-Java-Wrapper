package com.github.agadar.nsapi.enums.shard;

/**
 * Shards available for Nation requests. These shards have a 1:1 correspondence
 * with the variable fields in Nation.java.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum NationShard implements Shard {

    /**
     * One of two adjectives for this nation, e.g. 'cultured', 'safe', etc.
     */
    ADMIRABLE("ADMIRABLE"),
    /**
     * This nation's national animal.
     */
    ANIMAL("ANIMAL"),
    /**
     * Information about this nation's national animal.
     */
    ANIMAL_TRAIT("ANIMALTRAIT"),
    /**
     * The Rift banner code of this nation's primary banner, or of a randomly
     * chosen eligible banner if no primary banner is set. Can be prepended by
     * '/images/banners/' and appended by '.jpg' to convert into an image URL.
     */
    BANNER("BANNER"),
    /**
     * The eligible Rift banner codes of this nation. Codes can be prepended by
     * '/images/banners/' and appended by '.jpg' to convert into image URLs.
     */
    BANNERS("BANNERS"),
    /**
     * This nation's capital. Has default value if none is set.
     */
    CAPITAL("CAPITAL"),
    /**
     * This nation's government's category e.g. 'Civil Rights Lovefest'.
     */
    CATEGORY("CATEGORY"),
    /**
     * This nation's census scale scores.
     */
    CENSUS("CENSUS"),
    /**
     * Description of crime in this nation.
     */
    CRIME_DESCRIPTION("CRIME"),
    /**
     * This nation's currency name.
     */
    CURRENCY("CURRENCY"),
    /**
     * This nation's leader. Blank if none is set.
     */
    CUSTOM_LEADER("CUSTOMLEADER"),
    /**
     * This nation's capital. Blank if none is set.
     */
    CUSTOM_CAPITAL("CUSTOMCAPITAL"),
    /**
     * This nation's religion. Blank if none is set.
     */
    CUSTOM_RELIGION("CUSTOMRELIGION"),
    /**
     * List of death causes data in this nation.
     */
    DEATHS("DEATHS"),
    /**
     * Primary demonym.
     */
    DEMONYM("DEMONYM"),
    /**
     * Secondary demonym.
     */
    DEMONYM2("DEMONYM2"),
    /**
     * Plural of secondary demonym.
     */
    DEMONYM2_PLURAL("DEMONYM2PLURAL"),
    /**
     * Number of dispatches written by this nation.
     */
    NUMBER_OF_DISPATCHES("DISPATCHES"),
    /**
     * This nations's dispatches. Includes factbooks. Does not include
     * dispatches' texts.
     */
    DISPATCHES("DISPATCHLIST"),
    /**
     * List of nation names that endorsed this nation.
     */
    ENDORSED_BY("ENDORSEMENTS"),
    /**
     * Number of factbooks written by this nation.
     */
    NUMBER_OF_FACTBOOKS("FACTBOOKS"),
    /** 
     * This nation's factbooks. Subset of Dispatches. Does not include dispatches' texts. 
     */
    FACTBOOKS("FACTBOOKLIST"),
    /**
     * UNIX timestamp of when the nation first logged in.
     */
    FIRST_LOGIN("FIRSTLOGIN"),
    /**
     * URL to the nation's flag image.
     */
    FLAG_URL("FLAG"),
    /**
     * Description of when this nation was founded.
     */
    FOUNDED_DESCRIPTION("FOUNDED"),
    /**
     * UNIX timestamp of when this nation was founded.
     */
    FOUNDED("FOUNDEDTIME"),
    /**
     * The nation's civil rights, economy, and political freedoms scores.
     */
    FREEDOM("FREEDOM"),
    /**
     * The nation's full name.
     */
    FULL_NAME("FULLNAME"),
    /**
     * The nation's vote for the current General Assembly resolution.
     */
    GENERAL_ASSEMBLY_VOTE("GAVOTE"),
    /**
     * The nation's gross domestic product.
     */
    GROSS_DOMESTIC_PRODUCT("GDP"),
    /**
     * Overview in percentages of the government's expenditures.
     */
    GOVERNMENT_EXPENDITURE("GOVT"),
    /**
     * Elaborate description of the government.
     */
    GOVERNMENT_DESCRIPTION("GOVTDESC"),
    /**
     * The expenditure the government spends most on.
     */
    GOVERNMENT_PRIORITY("GOVTPRIORITY"),
    /**
     * List of the most recent of this nation's happenings.
     */
    RECENT_HAPPENINGS("HAPPENINGS"),
    /**
     * The average income of the population.
     */
    AVERAGE_INCOME("INCOME"),
    /**
     * Elaborate description of this nation's economy.
     */
    ECONOMY_DESCRIPTION("INDUSTRYDESC"),
    /**
     * The regional influence of this nation, e.g. 'Apprentice'.
     */
    REGIONAL_INFLUENCE("INFLUENCE"),
    /**
     * Description of when this nation was last active.
     */
    LAST_ACTIVITY("LASTACTIVITY"),
    /**
     * UNIX timestamp of when this nation was last logged in.
     */
    LAST_LOGIN("LASTLOGIN"),
    /**
     * This nation's leader. Has default value if none is set.
     */
    LEADER("LEADER"),
    /**
     * List of this nation's most recent legislations.
     */
    RECENT_LEGISLATION("LEGISLATION"),
    /**
     * This nation's biggest industry.
     */
    MAJOR_INDUSTRY("MAJORINDUSTRY"),
    /**
     * This nation's national motto.
     */
    MOTTO("MOTTO"),
    /**
     * This nation's name.
     */
    NAME("NAME"),
    /**
     * Description of what this nation is most notable for.
     */
    NOTABLE_FOR("NOTABLE"),
    /**
     * Income of the poorest population.
     */
    POOREST_INCOME("POOREST"),
    /**
     * The population, in millions.
     */
    POPULATION("POPULATION"),
    /**
     * The percentage of the economy that is State-Owned or Government.
     */
    PUBLIC_SECTOR("PUBLICSECTOR"),
    /**
     * The nation's regional census rank.
     */
    REGIONAL_CENSUS_RANK("RCENSUS"),
    /**
     * Name of the region this nation is in.
     */
    REGION_NAME("REGION"),
    /**
     * This nation's religion. Has default value if none is set.
     */
    RELIGION("RELIGION"),
    /**
     * Income of the richest population.
     */
    RICHEST_INCOME("RICHEST"),
    /**
     * The nation's vote for the current Security Council resolution.
     */
    SECURITY_COUNCIL_VOTE("SCVOTE"),
    /**
     * Overview in percentages of the government's economy sectors.
     */
    ECONOMY_SECTORS("SECTORS"),
    /**
     * Description of this nation's population.
     */
    POPULATION_DESCRIPTION("SENSIBILITIES"),
    /**
     * The income tax rate, expressed as a percentage.
     */
    AVERAGE_INCOME_TAX_RATE("TAX"),
    /**
     * True if the nation is not currently blocking recruitment telegrams.
     */
    CAN_RECEIVE_RECRUITMENT_TELEGRAMS("TGCANRECRUIT"),
    /**
     * True if the nation is not currently blocking campaign telegrams.
     */
    CAN_RECEIVE_CAMPAIGN_TELEGRAMS("TGCANCAMPAIGN"),
    /**
     * The nation's custom government type, e.g. 'Crowned Confederacy'.
     */
    GOVERNMENT_TYPE("TYPE"),
    /**
     * String indicating whether the nation is a member of the World Assembly.
     * Corresponds to the 'WA' shard, but its XML-tag is 'UNSTATUS'.
     */
    WORLD_ASSEMBLY_STATUS("WA"),
    /**
     * The nation's world-wide census rank
     */
    WORLD_CENSUS_RANK("WCENSUS"),
    /**
     * This nation's statistics of the current or last zombie event.
     */
    ZOMBIE_INFO("ZOMBIE");

    /**
     * The underlying shard name.
     */
    private final String shardName;

    /**
     * Instantiate a new entry with the given shard name.
     *
     * @param shardName the name of the underlying shard
     */
    private NationShard(String shardName) {
        this.shardName = shardName;
    }

    @Override
    public String shardName() {
        return shardName;
    }
}
