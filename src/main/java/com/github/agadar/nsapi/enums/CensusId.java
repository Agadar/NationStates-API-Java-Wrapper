package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerator for the id's of the various census scales.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum CensusId {
    CIVIL_RIGHTS(0),
    ECONOMY(1),
    POLITICAL_FREEDOM(2),
    POPULATION(3),
    WEALTH_GAPS(4),
    DEATH_RATE(5),
    COMPASSION(6),
    ECO_FRIENDLINESS(7),
    SOCIAL_CONSERVATISM(8),
    NUDITY(9),
    INDUSTRY_AUTOMOBILE_MANUFACTURING(10),
    INDUSTRY_CHEESE_EXPORTS(11),
    INDUSTRY_BASKET_WEAVING(12),
    INDUSTRY_INFORMATION_TECHNOLOGY(13),
    INDUSTRY_PIZZA_DELIVERY(14),
    INDUSTRY_TROUT_FISHING(15),
    INDUSTRY_ARMS_MANUFACTURING(16),
    SECTOR_AGRICULTURE(17),
    INDUSTRY_BEVERAGE_SALES(18),
    INDUSTRY_TIMBERWOOD_CHIPPING(19),
    INDUSTRY_MINING(20),
    INDUSTRY_INSURANCE(21),
    INDUSTRY_FURNITURE_RESTORATION(22),
    INDUSTRY_RETAIL(23),
    INDUSTRY_BOOK_PUBLISHING(24),
    INDUSTRY_GAMBLING(25),
    SECTOR_MANUFACTURING(26),
    GOVERNMENT_SIZE(27),
    WELFARE(28),
    PUBLIC_HEALTHCARE(29),
    LAW_ENFORCEMENT(30),
    BUSINESS_SUBSIDIZATION(31),
    RELIGIOUSNESS(32),
    INCOME_EQUALITY(33),
    NICENESS(34),
    RUDENESS(35),
    INTELLIGENCE(36),
    IGNORANCE(37),
    POLITICAL_APATHY(38),
    HEALTH(39),
    CHEERFULNESS(40),
    WEATHER(41),
    COMPLIANCE(42),
    SAFETY(43),
    LIFESPAN(44),
    IDEOLOGICAL_RADICALITY(45),
    DEFENSE_FORCES(46),
    PACIFISM(47),
    ECONOMIC_FREEDOM(48),
    TAXATION(49),
    FREEDOM_FROM_TAXATION(50),
    CORRUPTION(51),
    INTEGRITY(52),
    AUTHORITARIANISM(53),
    YOUTH_REBELLIOUSNESS(54),
    CULTURE(55),
    EMPLOYMENT(56),
    PUBLIC_TRANSPORT(57),
    TOURISM(58),
    WEAPONIZATION(59),
    RECREATIONAL_DRUG_USE(60),
    OBESITY(61),
    SECULARISM(62),
    ENVIRONMENTAL_BEAUTY(63),
    CHARMLESSNESS(64),
    INFLUENCE(65),
    WORLD_ASSEMBLY_ENDORSEMENTS(66),
    AVERAGENESS(67),
    HUMAN_DEVELOPMENT_INDEX(68),
    PRIMITIVENESS(69),
    SCIENTIFIC_ADVANCEMENT(70),
    INCLUSIVENESS(71),
    AVERAGE_INCOME(72),
    AVERAGE_INCOME_OF_POOR(73),
    AVERAGE_INCOME_OF_RICH(74),
    PUBLIC_EDUCATION(75),
    ECONOMIC_OUTPUT(76),
    CRIME(77),
    FOREIGN_AID(78),
    BLACK_MARKET(79),
    RESIDENCY(80);

    /**
     * The underlying id.
     */
    private final int id;

    /**
     * Map for reverse look-up via id.
     */
    private final static Map<Integer, CensusId> INTS_TO_ENUMS = new HashMap<>();

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        for (CensusId censusId : values()) {
            INTS_TO_ENUMS.put(censusId.id, censusId);
        }
    }

    /**
     * Returns the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the CensusId mapped to the given id.
     *
     * @param id the id
     * @return the CensusId mapped to the given id
     */
    public static CensusId getById(int id) {
        if (!INTS_TO_ENUMS.containsKey(id)) {
            throw new IllegalArgumentException("'" + id + "' cannot be parsed to this enum");
        }
        return INTS_TO_ENUMS.get(id);
    }
    
    /**
     * Instantiate a new entry with the given id.
     *
     * @param id the id
     */
    private CensusId(int id) {
        this.id = id;
    }
}
