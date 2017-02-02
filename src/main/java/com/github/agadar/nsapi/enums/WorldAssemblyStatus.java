package com.github.agadar.nsapi.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different World Assembly statusses a nation can have.
 * 
 * @author Agadar (https://github.com/Agadar/)
 */
public enum WorldAssemblyStatus {
    NON_MEMBER("Non-member"),
    MEMBER("WA Member"),
    DELEGATE("WA Delegate")
    ;
        
    /**
     * Reverse mapping.
     */
    private final static Map<String, WorldAssemblyStatus> STRINGS_TO_ENUMS = new HashMap<>();

    /**
     * Static init for filling the reverse mapping.
     */
    static {
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
    public static WorldAssemblyStatus getByString(String underlying) {
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
    private WorldAssemblyStatus(String underlying) {
        this.underlying = underlying;
    }
}
