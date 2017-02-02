package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Regional authority codes to authority names.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum Authority {
    /**
     * Executive
     */
    X("Executive"),
    /**
     * World Assembly
     */
    W("World Assembly"),
    /**
     * Appearance
     */
    A("Appearance"),
    /**
     * Border Control
     */
    B("Border Control"),
    /**
     * Communications
     */
    C("Communications"),
    /**
     * Embassies
     */
    E("Embassies"),
    /**
     * Polls
     */
    P("Polls");

    /**
     * Reverse mapping.
     */
    private final static Map<String, Authority> STRINGS_TO_ENUMS = new HashMap<>();

    /**
     * Static init for filling the reverse mapping.
     */
    static {
        for (Authority auth : values()) {
            STRINGS_TO_ENUMS.put(auth.toString(), auth);
        }
    }

    /**
     * The underlying authority name
     */
    private final String authName;

    /**
     * Returns the Authority holding a specific authName.
     *
     * @param authName string to find the corresponding Authority of
     * @return the corresponding Authority
     */
    public static Authority getByAuthName(String authName) {
        if (!STRINGS_TO_ENUMS.containsKey(authName)) {
            throw new IllegalArgumentException("'" + authName + "' cannot be parsed to this enum");
        }
        return STRINGS_TO_ENUMS.get(authName);
    }

    /**
     * Return the underlying authority name.
     *
     * @return the underlying authority name
     */
    @Override
    public String toString() {
        return authName;
    }
    
    /**
     * Instantiate a new entry with the given authority name.
     *
     * @param authName the name of the underlying authority
     */
    private Authority(String authName) {
        this.authName = authName;
    }
}
