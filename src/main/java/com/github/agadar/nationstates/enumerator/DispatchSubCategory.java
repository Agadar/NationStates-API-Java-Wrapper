package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The possible subcategories for dispatches.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum DispatchSubCategory {

    // Factbook
    OVERVIEW("Overview"),
    HISTORY("History"),
    GEOGRAPHY("Geography"),
    POLITICS("Politics"),
    LEGISLATION("Legislation"),
    RELIGION("Religion"),
    ECONOMY("Economy"),
    INTERNATIONAL("International"),
    TRIVIA("Trivia"),
    MISCELLANEOUS("Miscellaneous"),
    // Factbook & Account
    MILITARY("Military"),
    CULTURE("Culture"),
    // Account
    TRADE("Trade"),
    SPORT("Sport"),
    DRAMA("Drama"),
    DIPLOMACY("Diplomacy"),
    SCIENCE("Science"),
    OTHER("Other"),
    // Bulletin
    POLICY("Policy"),
    NEWS("News"),
    OPINION("Opinion"),
    CAMPAIGN("Campaign"),
    // Meta
    GAMEPLAY("Gameplay"),
    REFERENCE("Reference");

    /**
     * The string representation of this DispatchSubCategory.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, DispatchSubCategory> STRINGS_TO_ENUMS = new HashMap<>();

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        for (DispatchSubCategory dispatchSubCategory : values()) {
            STRINGS_TO_ENUMS.put(dispatchSubCategory.stringValue, dispatchSubCategory);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the DispatchSubCategory represented by the supplied string.
     *
     * @param stringValue the supplied string.
     * @return the DispatchSubCategory represented by the supplied string.
     */
    public static DispatchSubCategory fromString(String stringValue) {
        if (!STRINGS_TO_ENUMS.containsKey(stringValue)) {
            throw new IllegalArgumentException("'" + stringValue + "' cannot be parsed to this enum");
        }
        return STRINGS_TO_ENUMS.get(stringValue);
    }

    /**
     * Instantiates a new DispatchSubCategory, represented by the supplied
     * string.
     *
     * @param stringValue the supplied string.
     */
    private DispatchSubCategory(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Converts a String to a DispatchSubCategory, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, DispatchSubCategory> {

        @Override
        public DispatchSubCategory unmarshal(String v) throws Exception {
            return fromString(v);
        }

        @Override
        public String marshal(DispatchSubCategory v) throws Exception {
            return v.stringValue;
        }
    }
}
