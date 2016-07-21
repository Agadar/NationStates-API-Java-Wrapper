package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerator for the id's of the various census scales.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public enum CensusId 
{
    CivilRights(0),
    Economy(1),
    PoliticalFreedom(2),
    Population(3),
    WealthGaps(4),
    DeathRate(5),
    Compassion(6),
    EcoFriendliness(7),
    SocialConservatism(8),
    Nudity(9),
    IndustryAutomobileManufacturing(10),
    IndustryCheeseExports(11),
    IndustryBasketWeaving(12),
    IndustryInformationTechnology(13),
    IndustryPizzaDelivery(14),
    IndustryTroutFishing(15),
    IndustryArmsManufacturing(16),
    SectorAgriculture(17),
    IndustryBeverageSales(18),
    IndustryTimberWoodchipping(19),
    IndustryMining(20),
    IndustryInsurance(21),
    IndustryFurnitureRestoration(22),
    IndustryRetail(23),
    IndustryBookPublishing(24),
    IndustryGambling(25),
    SectorManufacturing(26),
    GovernmentSize(27),
    Welfare(28),
    PublicHealthcare(29),
    LawEnforcement(30),
    BusinessSubsidization(31),
    Religiousness(32),
    IncomeEquality(33),
    Niceness(34),
    Rudeness(35),
    Intelligence(36),
    Ignorance(37),
    PoliticalApathy(38),
    Health(39),
    Cheerfulness(40),
    Weather(41),
    Compliance(42),
    Safety(43),
    Lifespan(44),
    IdeologicalRadicality(45),
    DefenseForces(46),
    Pacifism(47),
    EconomicFreedom(48),
    Taxation(49),
    FreedomFromTaxation(50),
    Corruption(51),
    Integrity(52),
    Authoritarianism(53),
    YouthRebelliousness(54),
    Culture(55),
    Employment(56),
    PublicTransport(57),
    Tourism(58),
    Weaponization(59),
    RecreationalDrugUse(60),
    Obesity(61),
    Secularism(62),
    EnvironmentalBeauty(63),
    Charmlessness(64),
    Influence(65),
    WorldAssemblyEndorsements(66),
    Averageness(67),
    HumanDevelopmentIndex(68),
    Primitiveness(69),
    ScientificAdvancement(70),
    Inclusiveness(71),
    AverageIncome(72),
    AverageIncomeofPoor(73),
    AverageIncomeofRich(74),
    PublicEducation(75),
    EconomicOutput(76),
    Crime(77),
    ForeignAid(78),
    BlackMarket(79),
    Residency(80);
    
    /** The underlying id. */
    private final int Id;
    
    /** Map for reverse look-up via id. */
    private final static Map<Integer, CensusId> Reverse = new HashMap<>();
    
    /** Static 'constructor' for filling the reverse map. */
    static
    {
        for (CensusId censusId : values())
        {
            Reverse.put(censusId.Id, censusId);
        }
    }
    
    /**
     * Instantiate a new entry with the given id.
     * 
     * @param id the id
     */
    private CensusId(int id) 
    {
        this.Id = id;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId() 
    {
        return Id;
    }
    
    /**
     * Returns the CensusId mapped to the given id.
     * 
     * @param id
     * @return 
     */
    public static CensusId getById(int id)
    {
        return Reverse.get(id);
    }
}
