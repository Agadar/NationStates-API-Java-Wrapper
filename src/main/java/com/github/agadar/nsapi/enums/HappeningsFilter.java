package com.github.agadar.nsapi.enums;

/**
 * The possible options for the happenings filter for the World resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum HappeningsFilter {
    LAW("law"),
    CHANGE("change"),
    DISPATCH("dispatch"),
    REGIONAL_MESSAGE_BOARD("rmb"),
    EMBASSY("embassy"),
    EJECT("eject"),
    ADMIN("admin"),
    MOVE("move"),
    FOUNDING("founding"),
    CEASED_TO_EXIST("cte"),
    VOTE("vote"),
    RESOLUTION("resolution"),
    MEMBER("member"),
    ENDORSEMENT("endo");

    /**
     * The subcategories as they're known by the server.
     */
    private final String underlying;

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
    private HappeningsFilter(String underlying) {
        this.underlying = underlying;
    }
}
