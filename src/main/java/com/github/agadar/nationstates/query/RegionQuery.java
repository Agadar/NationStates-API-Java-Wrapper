package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.shard.RegionShard;
import lombok.NonNull;

/**
 * A query to the NationStates API's region resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionQuery extends CensusRankQuery<RegionQuery, Region, RegionShard> {

    /**
     * The maximum number of posts to retrieve (see REGIONAL_MESSAGES shard).
     */
    private long limit;

    /**
     * Offset for the most recent regional messages (see REGIONAL_MESSAGES shard).
     */
    private int offset;

    /**
     * The id of the post to start from (see REGIONAL_MESSAGES shard).
     */
    private long fromId;

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     * @param regionName        The name of the region to query.
     */
    public RegionQuery(@NonNull QueryDependencies queryDependencies, @NonNull String regionName) {
        super(queryDependencies, regionName);
    }

    /**
     * Sets the offset for the 10 most recent regional messages. Does nothing if the
     * RegionalMessages shard is not selected.
     *
     * @param offset the offset for the 10 most recent regional messages
     * @return this
     */
    public RegionQuery messagesOffset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Sets the id of the post to start from. Does nothing if the RegionalMessages
     * shard is not selected.
     *
     * @param id the id of the post to start from
     * @return this
     */
    public RegionQuery messagesFromId(long id) {
        this.fromId = id;
        return this;
    }

    /**
     * Sets the maximum number of posts to retrieve. Does nothing if the RegionalMessages shard is not selected.
     *
     * @param limit maximum number of posts or results to retrieve
     * @return this
     */
    public RegionQuery messagesLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    protected String resourceString() {
        return "region";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (resourceValue == null || resourceValue.isEmpty()) {
            throw new IllegalArgumentException("No or empty region name supplied!");
        }
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        url += offset == 0 ? "" : "&offset=" + offset;
        url += fromId == 0 ? "" : "&fromid=" + fromId;
        url += limit == 0 ? "" : "&limit=" + limit;
        return url;
    }
}
