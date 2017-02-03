package com.github.agadar.nsapi.enums;

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
    META("Meta");

    /**
     * The string representation of this DispatchCategory.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, DispatchCategory> STRINGS_TO_ENUMS = new HashMap<>();

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
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
            throw new IllegalArgumentException("'" + stringValue + "' cannot be parsed to this enum");
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
        public DispatchCategory unmarshal(String v) throws Exception {
            return fromString(v);
        }

        @Override
        public String marshal(DispatchCategory v) throws Exception {
            return v.stringValue;
        }

    }
}
