package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The different World Assembly badge types a nation or region can have.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum WorldAssemblyBadgeType {

    LIBERATE("liberate"), // Regions only
    COMMEND("commend"),
    CONDEMN("condemn"),
    NULL("NULL");

    /**
     * The string representation of this WorldAssemblyBadgeType.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, WorldAssemblyBadgeType> STRINGS_TO_ENUMS;

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        STRINGS_TO_ENUMS = new HashMap<>();
        for (WorldAssemblyBadgeType type : values()) {
            STRINGS_TO_ENUMS.put(type.stringValue, type);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the WorldAssemblyBadgeType represented by the supplied string.
     *
     * @param stringValue the supplied string.
     * @return the WorldAssemblyBadgeType represented by the supplied string.
     */
    public static WorldAssemblyBadgeType fromString(String stringValue) {
        if (!STRINGS_TO_ENUMS.containsKey(stringValue)) {
            return WorldAssemblyBadgeType.NULL;
        }
        return STRINGS_TO_ENUMS.get(stringValue);
    }

    /**
     * Instantiates a new WorldAssemblyBadgeType, represented by the supplied
     * string.
     *
     * @param stringValue the supplied string.
     */
    private WorldAssemblyBadgeType(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Converts a String to a WorldAssemblyBadgeType, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, WorldAssemblyBadgeType> {

        @Override
        public WorldAssemblyBadgeType unmarshal(String v) throws Exception {
            return fromString(v);
        }

        @Override
        public String marshal(WorldAssemblyBadgeType v) throws Exception {
            return v.stringValue;
        }
    }
}
