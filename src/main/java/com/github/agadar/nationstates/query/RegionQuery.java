package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.IXmlConverter;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;
import com.github.agadar.nationstates.shard.RegionShard;

/**
 * A query to the NationStates API's region resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RegionQuery extends CensusRankQuery<RegionQuery, Region, RegionShard> {

    /**
     * Offset for the 10 most recent regional messages.
     */
    private int offset;

    /**
     * The id of the post to start from.
     */
    private long fromId;

    /**
     * The maximum number of posts to retrieve, or the maximum number of results
     * to return for 'mostposts', 'mostliked', and 'mostlikes' to retrieve.
     */
    private long limit;

    /**
     * Earliest epoch date of messages to take into account for MostPosts,
     * MostLiked, and MostLikes shards.
     */
    private long postsFrom;

    /**
     * Latest epoch date of messages to take into account for 'mostposts',
     * 'mostliked', and 'mostlikes'.
     */
    private long postsTo;

    /**
     * Constructor. Sets the name of the region to query.
     *
     * @param xmlConverter
     * @param generalRateLimiter
     * @param scrapingRateLimiter
     * @param baseUrl
     * @param userAgent
     * @param apiVersion
     * @param regionName name of the region to query
     */
    public RegionQuery(IXmlConverter xmlConverter, IRateLimiter generalRateLimiter, IRateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String regionName) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, regionName);
    }

    /**
     * Sets the offset for the 10 most recent regional messages. Does nothing if
     * the RegionalMessages shard is not selected.
     *
     * @param offset the offset for the 10 most recent regional messages
     * @return this
     */
    public final RegionQuery messagesOffset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Sets the id of the post to start from. Does nothing if the
     * RegionalMessages shard is not selected.
     *
     * @param id the id of the post to start from
     * @return this
     */
    public final RegionQuery messagesFromId(long id) {
        this.fromId = id;
        return this;
    }

    /**
     * Sets the maximum number of posts to retrieve, or the maximum number of
     * results to return for MostLiked, MostLikes, or MostPosts to retrieve.
     * Does nothing if the RegionalMessages, MostLiked, MostLikes, or MostPosts
     * shard is not selected.
     *
     * @param limit maximum number of posts or results to retrieve
     * @return this
     */
    public final RegionQuery messagesLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Sets the earliest epoch date of messages to take into account for
     * MostPosts, MostLiked, and MostLikes shards.
     *
     * @param postsFrom earliest epoch date of messages to take into account
     * @return this
     */
    public final RegionQuery postsFrom(long postsFrom) {
        this.postsFrom = postsFrom;
        return this;
    }

    /**
     * Sets the latest epoch date of messages to take into account for
     * MostPosts, MostLiked, and MostLikes shards.
     *
     * @param postsTo latest epoch date of messages to take into account
     * @return this
     */
    public final RegionQuery postsTo(long postsTo) {
        this.postsTo = postsTo;
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
        url += postsFrom == 0 ? "" : "&from=" + postsFrom;
        url += postsTo == 0 ? "" : "&to=" + postsTo;
        return url;
    }
}
