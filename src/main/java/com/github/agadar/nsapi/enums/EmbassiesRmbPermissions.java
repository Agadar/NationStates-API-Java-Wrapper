package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different kinds of permissions a region can have set regarding
 * the nations of regions it has embassies with posting on the region's message
 * board.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum EmbassiesRmbPermissions {

    NOBODY("0"),
    DELEGATES_AND_FOUNDERS("con"),
    OFFICERS("off"),
    OFFICERS_WITH_COMMUNICATIONS_AUTHORITY("com"),
    EVERYONE("all");

    /**
     * Reverse mapping.
     */
    private final static Map<String, EmbassiesRmbPermissions> STRINGS_TO_ENUMS = new HashMap<>();

    /**
     * Static init for filling the reverse mapping.
     */
    static {
        for (EmbassiesRmbPermissions status : values()) {
            STRINGS_TO_ENUMS.put(status.toString(), status);
        }
    }

    /**
     * The categories as they're known by the server.
     */
    private final String underlying;

    /**
     * Returns the EmbassiesRMBPermissions with the supplied underlying string.
     *
     * @param underlying string to find the EmbassiesRMBPermissions of
     * @return the corresponding EmbassiesRMBPermissions
     */
    public static EmbassiesRmbPermissions fromString(String underlying) {
        if (!STRINGS_TO_ENUMS.containsKey(underlying)) {
            throw new IllegalArgumentException("'" + underlying + "' cannot be parsed to this enum");
        }
        return STRINGS_TO_ENUMS.get(underlying);
    }

    /**
     * Return the underlying string.
     *
     * @return the underlying string.
     */
    @Override
    public String toString() {
        return underlying;
    }

    /**
     * Instantiate a new entry with the given underlying string.
     *
     * @param underlying The underlying string.
     */
    private EmbassiesRmbPermissions(String underlying) {
        this.underlying = underlying;
    }
}
