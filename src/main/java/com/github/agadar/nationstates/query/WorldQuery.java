package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.domain.world.World;
import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;
import com.github.agadar.nationstates.enumerator.HappeningsFilter;
import com.github.agadar.nationstates.enumerator.RegionTag;
import com.github.agadar.nationstates.shard.WorldShard;

import lombok.NonNull;

/**
 * A query to the NationStates API's world resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class WorldQuery extends CensusRankQuery<WorldQuery, World, WorldShard> {

    /**
     * Nation to list dispatches of.
     */
    private String dispatchAuthor;

    /**
     * Category to list dispatches of.
     */
    private DispatchCategory dispatchCategory;

    /**
     * Sub-category to list dispatches of.
     */
    private DispatchSubCategory dispatchSubcategory;

    /**
     * Whether to get the best dispatches (as opposed to newest).
     */
    private boolean dispatchGetBest = false;

    /**
     * Id of the dispatch to select.
     */
    private long dispatchId;

    /**
     * Regions WITH these tags are retrieved.
     */
    private RegionTag[] regionsWithTags;

    /**
     * Regions WITHOUT these tags are retrieved.
     */
    private RegionTag[] regionsWithoutTags;

    /**
     * The nation or region to view happenings of.
     */
    private String happeningsView;

    /**
     * The filters for happenings.
     */
    private HappeningsFilter[] happeningsFilter;

    /**
     * The maximum number of happenings to retrieve.
     */
    private int happeningsLimit;

    /**
     * For restricting happenings to those with higher event id's than this.
     */
    private long happeningsSinceId;

    /**
     * For restricting happenings to those with lower event id's than this.
     */
    private long happeningsBeforeId;

    /**
     * For restricting happenings to those with higher timestamps than this.
     */
    private long happeningsSinceTime;

    /**
     * For restricting happenings to those with lower timestamps than this.
     */
    private long happeningsBeforeTime;

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     * @param shards            The shards to query.
     */
    public WorldQuery(@NonNull QueryDependencies queryDependencies, @NonNull WorldShard... shards) {
        super(queryDependencies, "");
        this.shards = shards;
    }

    /**
     * Sets the author to list dispatches of. Does nothing if Dispatches shard is
     * not selected.
     *
     * @param nation the author to list dispatches of
     * @return this
     */
    public WorldQuery dispatchAuthor(@NonNull String nation) {
        this.dispatchAuthor = nation;
        return this;
    }

    /**
     * Sets the category to list dispatches of. Does nothing if Dispatches shard is
     * not selected.
     *
     * @param category the category to list dispatches of
     * @return this
     */
    public WorldQuery dispatchCategory(@NonNull DispatchCategory category) {
        this.dispatchCategory = category;
        return this;
    }

    /**
     * Sets the subcategory to list dispatches of. Does nothing if Dispatches shard
     * is not selected or category is not set.
     *
     * @param subcategory the subcategory to list dispatches of
     * @return this
     */
    public WorldQuery dispatchSubcategory(@NonNull DispatchSubCategory subcategory) {
        this.dispatchSubcategory = subcategory;
        return this;
    }

    /**
     * Sets the type to list dispatches of to 'best', as opposed to the default
     * 'new'. Does nothing if Dispatches shard is not selected.
     *
     * @return this
     */
    public WorldQuery dispatchGetBest() {
        this.dispatchGetBest = true;
        return this;
    }

    /**
     * Sets the id of the dispatch to select. Does nothing if SelectedDispatch shard
     * is not selected.
     *
     * @param dispatchId id of the dispatch to select.
     * @return this
     */
    public WorldQuery dispatchId(long dispatchId) {
        this.dispatchId = dispatchId;
        return this;
    }

    /**
     * Sets the tags, used for retrieving regions that have those tags. Does nothing
     * if the RegionsByTag shard is not selected.
     *
     * @param tags the tags
     * @return this
     */
    public WorldQuery regionsWithTags(@NonNull RegionTag... tags) {
        regionsWithTags = tags;
        return this;
    }

    /**
     * Sets the tags, used for retrieving regions that do NOT have those tags. Does
     * nothing if the RegionsByTag shard is not selected.
     *
     * @param tags the tags
     * @return this
     */
    public WorldQuery regionsWithoutTags(@NonNull RegionTag... tags) {
        regionsWithoutTags = tags;
        return this;
    }

    /**
     * Sets the nation(s) to view happenings of. Does nothing if the Happenings
     * shard is not selected.
     *
     * @param nation the nation(s) to view happenings of
     * @return this
     */
    public WorldQuery happeningsOfNation(@NonNull String... nation) {
        this.happeningsView = nation.length == 1 ? "nation." : "nations.";
        this.happeningsView += nation[0];

        for (int i = 1; i < nation.length; i++) {
            this.happeningsView += "," + nation[i];
        }

        return this;
    }

    /**
     * Sets the region(s) to view happenings of. Does nothing if the Happenings
     * shard is not selected.
     *
     * @param region the region(s) to view happenings of
     * @return this
     */
    public WorldQuery happeningsOfRegion(@NonNull String... region) {
        this.happeningsView = region.length == 1 ? "region." : "regions.";
        this.happeningsView += region[0];

        for (int i = 1; i < region.length; i++) {
            this.happeningsView += "," + region[i];
        }

        return this;
    }

    /**
     * Sets the filters for happenings. Does nothing if the Happenings shard is not
     * selected.
     *
     * @param filters the filters for happenings
     * @return this
     */
    public WorldQuery happeningsFilter(@NonNull HappeningsFilter... filters) {
        this.happeningsFilter = filters;
        return this;
    }

    /**
     * Sets the maximum number of happenings to retrieve. Does nothing if the
     * Happenings shard is not selected.
     *
     * @param limit the maximum number of happenings to retrieve
     * @return this
     */
    public WorldQuery happeningsLimit(int limit) {
        this.happeningsLimit = limit;
        return this;
    }

    /**
     * If this is set, only happenings with a higher id than the one supplied will
     * be retrieved.
     *
     * @param id the id
     * @return this
     */
    public WorldQuery happeningsSinceId(long id) {
        this.happeningsSinceId = id;
        return this;
    }

    /**
     * If this is set, only happenings with a lower id than the one supplied will be
     * retrieved.
     *
     * @param id the id
     * @return this
     */
    public WorldQuery happeningsBeforeId(long id) {
        this.happeningsBeforeId = id;
        return this;
    }

    /**
     * If this is set, only happenings with a later timestamp than the one supplied
     * will be retrieved.
     *
     * @param time the timestamp
     * @return this
     */
    public WorldQuery happeningsSinceTime(long time) {
        this.happeningsSinceTime = time;
        return this;
    }

    /**
     * If this is set, only happenings with an earlier timestamp than the one
     * supplied will be retrieved.
     *
     * @param time the timestamp
     * @return this
     */
    public WorldQuery happeningsBeforeTime(long time) {
        this.happeningsBeforeTime = time;
        return this;
    }

    @Override
    protected String resourceString() {
        return "";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (shards == null || shards.length == 0) {
            throw new IllegalArgumentException("No shards selected!");
        }
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();

        if (dispatchAuthor != null && !dispatchAuthor.isEmpty()) {
            url += "&dispatchauthor=" + dispatchAuthor;
        }

        if (dispatchCategory != null) {
            url += "&dispatchcategory=" + dispatchCategory.toString();

            if (dispatchSubcategory != null) {
                url += ":" + dispatchSubcategory.toString();
            }
        }

        if (dispatchGetBest) {
            url += "&dispatchsort=best";
        }

        if (dispatchId != 0) {
            url += "&dispatchid=" + dispatchId;
        }

        boolean hasWithTags = regionsWithTags != null && regionsWithTags.length > 0;

        if (hasWithTags) {
            url += "&tags=" + regionsWithTags[0].toString();

            for (int i = 1; i < regionsWithTags.length; i++) {
                url += "," + regionsWithTags[i].toString();
            }
        }

        if (regionsWithoutTags != null && regionsWithoutTags.length > 0) {
            int start = 0;

            if (!hasWithTags) {
                url += "&tags=-" + regionsWithoutTags[start++].toString();
            }

            for (int i = start; i < regionsWithoutTags.length; i++) {
                url += ",-" + regionsWithoutTags[i].toString();
            }
        }

        if (happeningsView != null && !happeningsView.isEmpty() && !happeningsView.equals("nation.")
                && !happeningsView.equals("region.") && !happeningsView.equals("nations.")
                && !happeningsView.equals("regions.")) {
            url += "&view=" + happeningsView;
        }

        if (happeningsFilter != null && happeningsFilter.length > 0) {
            url += "&filter=" + happeningsFilter[0].toString();

            for (int i = 1; i < happeningsFilter.length; i++) {
                url += "+" + happeningsFilter[i].toString();
            }
        }

        if (happeningsLimit > 0) {
            url += "&limit=" + happeningsLimit;
        }

        if (happeningsSinceId > 0) {
            url += "&sinceid=" + happeningsSinceId;
        }

        if (happeningsBeforeId > 0) {
            url += "&beforeid=" + happeningsBeforeId;
        }

        if (happeningsSinceTime > 0) {
            url += "&sincetime=" + happeningsSinceTime;
        }

        if (happeningsBeforeTime > 0) {
            url += "&beforetime=" + happeningsBeforeTime;
        }

        return url;
    }
}
