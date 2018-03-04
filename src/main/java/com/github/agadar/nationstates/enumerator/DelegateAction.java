package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The possible actions a delegate can have taken on the current proposal at
 * vote.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum DelegateAction {

    FOR("FOR"),
    AGAINST("AGAINST"),
    WITHDREW("WITHDREW"),
    NULL("NULL");

    /**
     * The string representation of this DispatchCategory.
     */
    private final String stringValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<String, DelegateAction> STRINGS_TO_ENUMS;

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        STRINGS_TO_ENUMS = new HashMap<>();
        for (DelegateAction delegateAction : values()) {
            STRINGS_TO_ENUMS.put(delegateAction.stringValue, delegateAction);
        }
    }

    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Returns the DelegateAction represented by the supplied string.
     *
     * @param stringValue the supplied string.
     * @return the DelegateAction represented by the supplied string.
     */
    public static DelegateAction fromString(String stringValue) {
        if (!STRINGS_TO_ENUMS.containsKey(stringValue)) {
            return DelegateAction.NULL;
        }
        return STRINGS_TO_ENUMS.get(stringValue);
    }

    /**
     * Instantiates a new DelegateAction, represented by the supplied string.
     *
     * @param stringValue the supplied string.
     */
    private DelegateAction(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Converts a String to a DelegateAction, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, DelegateAction> {

        @Override
        public DelegateAction unmarshal(String v) {
            return fromString(v);
        }

        @Override
        public String marshal(DelegateAction v) {
            return v.stringValue;
        }
    }
}
