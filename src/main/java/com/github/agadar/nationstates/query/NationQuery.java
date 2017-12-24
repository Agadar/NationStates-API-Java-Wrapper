package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.IXmlConverter;
import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;
import com.github.agadar.nationstates.shard.NationShard;

/**
 * A query to the NationStates API's nation resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class NationQuery extends CensusQuery<NationQuery, Nation, NationShard> {

    /**
     * See canReceiveTelegramFromRegion(...).
     */
    private String canReceiveTelegramFromRegion;

    /**
     * Constructor. Sets the name of the nation to query.
     *
     * @param xmlConverter
     * @param generalRateLimiter
     * @param scrapingRateLimiter
     * @param baseUrl
     * @param userAgent
     * @param apiVersion
     * @param nationName name of the nation to query
     */
    public NationQuery(IXmlConverter xmlConverter, IRateLimiter generalRateLimiter, IRateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String nationName) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, nationName);
    }

    /**
     * For use with the 'CanReceiveRecruitmentTelegrams' and
     * 'CanReceiveCampaignTelegrams' shards. Allows a sender region to be
     * specified.
     *
     * @param region the specified region.
     * @return this
     */
    public final NationQuery canReceiveTelegramFromRegion(String region) {
        this.canReceiveTelegramFromRegion = region.trim();
        return this;
    }

    @Override
    protected String resourceString() {
        return "nation";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (resourceValue == null || resourceValue.isEmpty()) {
            throw new IllegalArgumentException("No or empty nation name supplied!");
        }
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();

        // If canReceiveTelegramFromRegion was supplied, append them to url
        if (canReceiveTelegramFromRegion != null && !canReceiveTelegramFromRegion.isEmpty()) {
            url += "&from=" + canReceiveTelegramFromRegion;
        }

        return url;
    }
}
