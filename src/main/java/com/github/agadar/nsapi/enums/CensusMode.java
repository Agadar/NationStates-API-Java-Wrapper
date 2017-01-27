package com.github.agadar.nsapi.enums;

/**
 * Available values for the Mode option for the Census shard.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum CensusMode {

    /**
     * The nation's individual score, or the region's or world's average score.
     */
    Score("SCORE"),
    /**
     * The nation's or region's world-wide rank on the scale. e.g. '334' means
     * '334th'. Inapplicable for world.
     */
    WorldRank("RANK"),
    /**
     * The nation's regional rank on the scale. e.g. '334' means '334th'.
     * Inapplicable for regions and world.
     */
    RegionRank("RRANK"),
    /**
     * The nation's or region's world-wide rank on the scale, as a percentage.
     * e.g. '15' means 'Top 15%'. Inapplicable for world.
     */
    WorldRankPercent("PRANK"),
    /**
     * The nation's regional rank on the scale, as a percentage. e.g. '15' means
     * 'Top 15%'. Inapplicable for regions and world.
     */
    RegionRankPercent("PRRANK"),
    /**
     * The history of the census score. Cannot be combined with other modes.
     */
    History("HISTORY");

    /**
     * The underlying option name
     */
    private final String option;

    /**
     * Instantiate a new entry with the given option name.
     *
     * @param authName the name of the underlying option
     */
    private CensusMode(String option) {
        this.option = option;
    }

    /**
     * Return the underlying option name.
     *
     * @return the underlying option name
     */
    @Override
    public String toString() {
        return option;
    }
}
