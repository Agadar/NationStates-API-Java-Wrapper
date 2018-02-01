package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Represents the different World Assembly statuses a nation can have.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum WorldAssemblyStatus {

    NON_MEMBER("Non-member"),
    MEMBER("WA Member"),
    DELEGATE("WA Delegate"),
    NULL("NULL");

    /**
     * Reverse mapping.
     */
    private final static Map<String, WorldAssemblyStatus> STRINGS_TO_ENUMS;

    /**
     * Static init for filling the reverse mapping.
     */
    static {
        STRINGS_TO_ENUMS = new HashMap<>();
        for (WorldAssemblyStatus status : values()) {
            STRINGS_TO_ENUMS.put(status.toString(), status);
        }
    }

    /**
     * The categories as they're known by the server.
     */
    private final String underlying;

    /**
     * Returns the WorldAssemblyStatus with the supplied underlying string.
     *
     * @param underlying string to find the WorldAssemblyStatus of
     * @return the corresponding WorldAssemblyStatus
     */
    public static WorldAssemblyStatus fromString(String underlying) {
        if (!STRINGS_TO_ENUMS.containsKey(underlying)) {
            return WorldAssemblyStatus.NULL;
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
    private WorldAssemblyStatus(String underlying) {
        this.underlying = underlying;
    }

    /**
     * Converts a string to a WorldAssemblyStatus, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, WorldAssemblyStatus> {

        @Override
        public WorldAssemblyStatus unmarshal(String vt) {
            return WorldAssemblyStatus.fromString(vt);
        }

        @Override
        public String marshal(WorldAssemblyStatus bt) {
            return bt.underlying;
        }
    }
}
