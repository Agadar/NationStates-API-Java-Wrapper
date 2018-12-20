package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The different regional influence ranks a nation can have in the region they
 * reside in.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum InfluenceRank {

    NULL("NULL"),
    ZERO("Zero"),
    UNPROVEN("Unproven"),
    HATCHLING("Hatchling"),
    NEWCOMER("Newcomer"),
    NIPPER("Nipper"),
    MINNOW("Minnow"),
    SPRAT("Sprat"),
    SHOESHINER("Shoeshiner"),
    PAGE("Page"),
    SQUIRE("Squire"),
    APPRENTICE("Apprentice"),
    VASSAL("Vassal"),
    TRUCKLER("Truckler"),
    HANDSHAKER("Handshaker"),
    DUCKSPEAKER("Duckspeaker"),
    ENVOY("Envoy"),
    DIPLOMAT("Diplomat"),
    AMBASSADOR("Ambassador"),
    AUXILIARY("Auxiliary"),
    NEGOTIATOR("Negotiator"),
    CONTENDER("Contender"),
    INSTIGATOR("Instigator"),
    DEALMAKER("Dealmaker"),
    ENFORCER("Enforcer"),
    EMINENCE_GRISE("Eminence Grise"),
    POWERBROKER("Powerbroker"),
    POWER("Power"),
    SUPERPOWER("Superpower"),
    DOMINATOR("Dominator"),
    HEGEMONY("Hegemony"),
    HERMIT("Hermit");

    /**
     * The string representation of this InfluenceRank.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, InfluenceRank> STRINGS_TO_ENUMS;

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        STRINGS_TO_ENUMS = new HashMap<>();
        for (InfluenceRank rank : values()) {
            STRINGS_TO_ENUMS.put(rank.stringValue, rank);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the InfluenceRank represented by the supplied string.
     *
     * @param stringValue the supplied string.
     * @return the InfluenceRank represented by the supplied string.
     */
    public static InfluenceRank fromString(String stringValue) {
        return STRINGS_TO_ENUMS.getOrDefault(stringValue, InfluenceRank.NULL);
    }

    /**
     * Instantiates a new InfluenceRank, represented by the supplied string.
     *
     * @param stringValue the supplied string.
     */
    private InfluenceRank(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Converts a String to a InfluenceRank, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, InfluenceRank> {

        @Override
        public InfluenceRank unmarshal(String v) {
            return fromString(v);
        }

        @Override
        public String marshal(InfluenceRank v) {
            return v.stringValue;
        }
    }
}
