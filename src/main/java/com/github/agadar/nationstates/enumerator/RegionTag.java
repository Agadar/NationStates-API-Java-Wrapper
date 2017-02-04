package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The different tags a region can have.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum RegionTag {

    ANARCHIST("anarchist"),
    ANIME("anime"),
    ANTI_CAPITALIST("anti-capitalist"),
    ANTI_COMMUNIST("anti-communist"),
    ANTI_FASCIST("anti-fascist"),
    ANTI_GENERAL_ASSEMBLY("anti-general_assembly"),
    ANTI_SECURITY_COUNCIL("anti-security_council"),
    ANTI_WORLD_ASSEMBLY("anti-world_assembly"),
    CAPITALIST("capitalist"),
    CASUAL("casual"),
    CLASS("class"),
    COMMENDED("commended"),
    COMMUNIST("communist"),
    CONDEMNED("condemned"),
    CONSERVATIVE("conservative"),
    CYBERPUNK("cyberpunk"),
    DEFENDER("defender"),
    DEMOCRATIC("democratic"),
    ECO_FRIENDLY("eco-friendly"),
    ENORMOUS("enormous"),
    FORUM_7("f7er"),
    FUTURE_TECH_FASTER_THAN_LIGHT("ft_ftl"),
    FUTURE_TECH_FASTER_THAN_LIGHT_INHIBITION("ft_ftli"),
    FUTURE_TECH_SLOWER_THAN_LIGHT("ft_stl"),
    FANDOM("fandom"),
    FANTASY_TECH("fantasy_tech"),
    FASCIST("fascist"),
    FEATURED("featured"),
    FEEDER("feeder"),
    FOUNDERLESS("founderless"),
    FREE_TRADE("free_trade"),
    FUTURE_TECH("future_tech"),
    GAME_PLAYER("game_player"),
    GARGANTUAN("gargantuan"),
    GENERAL_ASSEMBLY("general_assembly"),
    GENERALITE("generalite"),
    HUMAN_ONLY("human-only"),
    IMPERIALIST("imperialist"),
    INDEPENDENT("independent"),
    INDUSTRIAL("industrial"),
    INTERNATIONAL_FEDERALIST("international_federalist"),
    INVADER("invader"),
    ISOLATIONIST("isolationist"),
    ISSUES_PLAYER("issues_player"),
    LGBT("lbgt"),
    LARGE("large"),
    LIBERAL("liberal"),
    LIBERATED("liberated"),
    LIBERTARIAN("libertarian"),
    MAGICAL("magical"),
    MAP("map"),
    MEDIUM("medium"),
    MERCENARY("mercenary"),
    MINUSCULE("minuscule"),
    MODERN_TECH("modern_tech"),
    MONARCHIST("monarchist"),
    MULTI_SPECIES("multi-species"),
    NATIONAL_SOVEREIGNTIST("national_sovereigntist"),
    NEUTRAL("neutral"),
    NEW("new"),
    NON_ENGLISH("non-english"),
    OFFSITE_FORUMS("offsite_forums"),
    OUTER_SPACE("outer_space"),
    PORTAL_TO_THE_MULTIVERSE("p2tm"),
    PACIFIST("pacifist"),
    PARODY("parody"),
    PASSWORD("password"),
    PAST_TECH("past_tech"),
    POST_APOCALYPTIC("post_apocalyptic"),
    POST_MODERN_TECH("post-modern_tech"),
    PUPPET_STORAGE("puppet_storage"),
    RECRUITER_FRIENDLY("recruiter_friendly"),
    REGIONAL_GOVERNMENT("regional_government"),
    RELIGIOUS("religious"),
    ROLE_PLAYER("role_player"),
    SECURITY_COUNCIL("security_council"),
    SERIOUS("serious"),
    SILLY("silly"),
    SINKER("sinker"),
    SMALL("small"),
    SNARKY("snarky"),
    SOCIAL("social"),
    SOCIALIST("socialist"),
    SPORTS("sports"),
    STEAMPUNK("steampunk"),
    SURREAL("surreal"),
    THEOCRATIC("theocratic"),
    TOTALITARIAN("totalitarian"),
    VIDEO_GAME("video_game"),
    WARZONE("warzone"),
    WORLD_ASSEMBLY("world_assembly");

    /**
     * The string representation of this RegionTag.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, RegionTag> STRINGS_TO_ENUMS;

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        STRINGS_TO_ENUMS = new HashMap<>();
        for (RegionTag regionTag : values()) {
            STRINGS_TO_ENUMS.put(regionTag.stringValue, regionTag);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the RegionTag represented by the supplied string.
     * Case-insensitive, and whitespaces are interpreted as underscores. Colons
     * (':') are ignored.
     *
     * @param stringValue the supplied string.
     * @return the RegionTag represented by the supplied string.
     */
    public static RegionTag fromString(String stringValue) {
        stringValue = stringValue.toLowerCase().replace(' ', '_').replace(":", "");

        if (!STRINGS_TO_ENUMS.containsKey(stringValue)) {
            throw new IllegalArgumentException("'" + stringValue + "' cannot be parsed to this enum");
        }
        return STRINGS_TO_ENUMS.get(stringValue);
    }

    /**
     * Instantiates a new RegionTag, represented by the supplied string.
     *
     * @param stringValue the supplied string.
     */
    private RegionTag(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Converts a String to a RegionTag, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, RegionTag> {

        @Override
        public RegionTag unmarshal(String v) throws Exception {
            return fromString(v);
        }

        @Override
        public String marshal(RegionTag v) throws Exception {
            return v.stringValue;
        }
    }
}
