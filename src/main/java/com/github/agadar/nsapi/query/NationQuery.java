package com.github.agadar.nsapi.query;

import com.github.agadar.nsapi.query.blueprint.CensusQuery;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.domain.nation.Nation;
import com.github.agadar.nsapi.enums.shard.NationShard;

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
     * @param nationName name of the nation to query
     */
    public NationQuery(String nationName) {
        super(nationName);
    }

    /**
     * For use with the 'CanReceiveRecruitmentTelegrams' and
     * 'CanReceiveCampaignTelegrams' shards. Allows a sender region to be
     * specified.
     *
     * @param region
     * @return
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
            throw new NationStatesAPIException("No or empty nation name supplied!");
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
