package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.shard.NationShard;

import lombok.NonNull;

/**
 * A query to the NationStates API's nation resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationQuery extends CensusQuery<NationQuery, Nation, NationShard> {

    /**
     * See canReceiveTelegramFromRegion(...).
     */
    private String canReceiveTelegramFromRegion;

    /**
     * Constructor.
     *
     * @param queryDependencies Contains the basic dependencies required for most
     *                          queries.
     * @param nationName        The name of the nation to query.
     */
    public NationQuery(@NonNull QueryDependencies queryDependencies, @NonNull String nationName) {
        super(queryDependencies, nationName);
    }

    /**
     * For use with the 'CanReceiveRecruitmentTelegrams' and
     * 'CanReceiveCampaignTelegrams' shards. Allows a sender region to be specified.
     *
     * @param region the specified region.
     * @return this
     */
    public NationQuery canReceiveTelegramFromRegion(@NonNull String region) {
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
