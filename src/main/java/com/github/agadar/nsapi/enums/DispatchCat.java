package com.github.agadar.nsapi.enums;

/**
 * The possible categories for dispatches.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum DispatchCat {
    FACTBOOK("Factbook"),
    BULLETIN("Bulletin"),
    ACCOUNT("Account"),
    META("Meta")
    ;
    
    /**
     * The categories as they're known by the server.
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
    private DispatchCat(String underlying) {
        this.underlying = underlying;
    }
}
