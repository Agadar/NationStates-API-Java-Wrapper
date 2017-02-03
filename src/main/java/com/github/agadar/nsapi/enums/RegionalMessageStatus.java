package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerator for the different statuses a regional message can have.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum RegionalMessageStatus {

    VISIBLE(0),
    SUPPRESSED_BUT_VIEWABLE(1),
    DELETED_BY_AUTHOR(2),
    SUPPRESSED_BY_MODERATOR(9);

    /**
     * The integer representation of this RegionalMessageStatus.
     */
    private final int intValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<Integer, RegionalMessageStatus> INTS_TO_ENUMS = new HashMap<>();

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        for (RegionalMessageStatus regionalMessageStatus : values()) {
            INTS_TO_ENUMS.put(regionalMessageStatus.intValue, regionalMessageStatus);
        }
    }

    /**
     * Returns the integer representation of this RegionalMessageStatus.
     *
     * @return the integer representation of this RegionalMessageStatus.
     */
    public int toInt() {
        return intValue;
    }

    /**
     * Returns the RegionalMessageStatus represented by the supplied integer.
     *
     * @param intValue the supplied integer.
     * @return the RegionalMessageStatus represented by the supplied integer.
     */
    public static RegionalMessageStatus fromInt(int intValue) {
        if (!INTS_TO_ENUMS.containsKey(intValue)) {
            throw new IllegalArgumentException("'" + intValue + "' cannot be parsed to this enum");
        }
        return INTS_TO_ENUMS.get(intValue);
    }

    /**
     * Instantiates a new RegionalMessageStatus, represented by the supplied
     * integer.
     *
     * @param intValue the supplied integer.
     */
    private RegionalMessageStatus(int intValue) {
        this.intValue = intValue;
    }
}
