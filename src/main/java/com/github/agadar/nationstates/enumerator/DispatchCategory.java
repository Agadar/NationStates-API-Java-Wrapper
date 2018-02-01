package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The possible categories for dispatches.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum DispatchCategory {

    FACTBOOK("Factbook"),
    BULLETIN("Bulletin"),
    ACCOUNT("Account"),
    META("Meta"),
    NULL("NULL");

    /**
     * The string representation of this DispatchCategory.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, DispatchCategory> STRINGS_TO_ENUMS;

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        STRINGS_TO_ENUMS = new HashMap<>();
        for (DispatchCategory dispatchCategory : values()) {
            STRINGS_TO_ENUMS.put(dispatchCategory.stringValue, dispatchCategory);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the DispatchCategory represented by the supplied string.
     *
     * @param stringValue the supplied string.
     * @return the DispatchCategory represented by the supplied string.
     */
    public static DispatchCategory fromString(String stringValue) {
        if (!STRINGS_TO_ENUMS.containsKey(stringValue)) {
            return DispatchCategory.NULL;
        }
        return STRINGS_TO_ENUMS.get(stringValue);
    }

    /**
     * Instantiates a new DispatchCategory, represented by the supplied string.
     *
     * @param stringValue the supplied string.
     */
    private DispatchCategory(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Converts a String to a DispatchCategory, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, DispatchCategory> {

        @Override
        public DispatchCategory unmarshal(String v) {
            return fromString(v);
        }

        @Override
        public String marshal(DispatchCategory v) {
            return v.stringValue;
        }

    }
}
