package com.github.agadar.nsapi.enums;

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
    private DispatchSubCategory(String underlying) {
        this.underlying = underlying;
    }
}
