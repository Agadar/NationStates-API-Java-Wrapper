package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Represents the different statusses an embassy can be in.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum EmbassyStatus {

    INVITED("invited"), // this region was invited by another region to build embassies
    REQUESTED("requested"), // this region invited another region to build embassies
    PENDING("pending"), // the invitation or request was accepted and the embassies are underway
    REJECTED("rejected"), // this region rejected an invitation by another region to build embassies
    DENIED("denied"), // the other region rejected the request from this region to build embassies
    CLOSING("closing"), // the embassies are being closed
    ESTABLISHED(null) // the embassies are established
    ;

    /**
     * Reverse mapping.
     */
    private final static Map<String, EmbassyStatus> STRINGS_TO_ENUMS = new HashMap<>();

    /**
     * Static init for filling the reverse mapping.
     */
    static {
        for (EmbassyStatus status : values()) {
            STRINGS_TO_ENUMS.put(status.toString(), status);
        }
    }

    /**
     * The categories as they're known by the server.
     */
    private final String underlying;

    /**
     * Returns the EmbassyStatus with the supplied underlying string.
     *
     * @param underlying string to find the EmbassyStatus of
     * @return the corresponding EmbassyStatus
     */
    public static EmbassyStatus fromString(String underlying) {
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
    private EmbassyStatus(String underlying) {
        this.underlying = underlying;
    }

    /**
     * Converts a String to an EmbassyStatus, and vice versa.
     */
    public static class Adapter extends XmlAdapter<String, EmbassyStatus> {

        @Override
        public EmbassyStatus unmarshal(String vt) {
            return EmbassyStatus.fromString(vt);
        }

        @Override
        public String marshal(EmbassyStatus bt) {
            return bt.underlying;
        }
    }
}
