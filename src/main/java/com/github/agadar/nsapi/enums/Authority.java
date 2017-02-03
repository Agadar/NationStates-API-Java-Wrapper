package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The different authorities regional officers can have.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum Authority {

    EXECUTIVE('X'),
    WORLD_ASSEMBLY('W'),
    APPEARANCE('A'),
    BORDER_CONTROL('B'),
    COMMUNICATIONS('C'),
    EMBASSIES('E'),
    POLLS('P');

    /**
     * Reverse mapping.
     */
    private final static Map<Character, Authority> CHARS_TO_ENUMS = new HashMap<>();

    /**
     * Static init for filling the reverse mapping.
     */
    static {
        for (Authority auth : values()) {
            CHARS_TO_ENUMS.put(auth.toChar(), auth);
        }
    }

    /**
     * The underlying authority code.
     */
    private final char authCode;

    /**
     * Returns the Authority correspondong to the specified authority code.
     *
     * @param authCode the specified authority code.
     * @return the corresponding Authority.
     */
    public static Authority fromChar(char authCode) {
        if (!CHARS_TO_ENUMS.containsKey(authCode)) {
            throw new IllegalArgumentException("'" + authCode + "' cannot be parsed to this enum");
        }
        return CHARS_TO_ENUMS.get(authCode);
    }

    /**
     * Returns the underlying authority code.
     *
     * @return the underlying authority code.
     */
    public char toChar() {
        return authCode;
    }

    /**
     * Instantiate a new entry with the given authority code.
     *
     * @param authCode the authority code.
     */
    private Authority(char authCode) {
        this.authCode = authCode;
    }
}
