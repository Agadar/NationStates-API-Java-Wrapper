package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

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
    POLLS('P'),
    NULL('?');

    /**
     * Reverse mapping.
     */
    private final static Map<Character, Authority> CHARS_TO_ENUMS;

    /**
     * Static init for filling the reverse mapping.
     */
    static {
        CHARS_TO_ENUMS = new HashMap<>();
        for (Authority auth : values()) {
            CHARS_TO_ENUMS.put(auth.toChar(), auth);
        }
    }

    /**
     * The underlying authority code.
     */
    private final char authCode;

    /**
     * Returns the Authority corresponding to the specified authority code.
     *
     * @param authCode the specified authority code.
     * @return the corresponding Authority.
     */
    public static Authority fromChar(char authCode) {
        if (!CHARS_TO_ENUMS.containsKey(authCode)) {
            return Authority.NULL;
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

    /**
     * Converts a String containing authority codes to a List of Authority
     * values, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, Set<Authority>> {

        @Override
        public Set<Authority> unmarshal(String vt) throws Exception {
            final Set<Authority> authorities = new HashSet<>();

            for (char code : vt.toCharArray()) {
                final Authority auth = Authority.fromChar(code);

                if (auth != Authority.NULL) {
                    authorities.add(auth);
                }
            }
            return authorities;
        }

        @Override
        public String marshal(Set<Authority> bt) throws Exception {
            final StringBuilder builder = new StringBuilder();
            bt.stream()
                    .filter((auth) -> auth != Authority.NULL)
                    .forEach((auth) -> builder.append(auth.authCode));
            return builder.toString();
        }
    }
}
