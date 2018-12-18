package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.XmlConverter;
import com.github.agadar.nationstates.domain.worldassembly.WorldAssembly;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import com.github.agadar.nationstates.shard.WorldAssemblyShard;

/**
 * A query to the NationStates API's World Assembly resource.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class WorldAssemblyQuery extends ShardQuery<WorldAssemblyQuery, WorldAssembly, WorldAssemblyShard> {

    /**
     * The id of the resolution to retrieve.
     */
    private int resolutionId;

    public WorldAssemblyQuery(XmlConverter xmlConverter, RateLimiter generalRateLimiter,
            RateLimiter scrapingRateLimiter, String baseUrl, String userAgent, int apiVersion, Council council) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion,
                String.valueOf(council.toInt()));
    }

    /**
     * Sets the id of the resolution to retrieve. Does nothing if the Resolution
     * shard is not selected.
     *
     * @param resolutionId the id of the resolution to retrieve
     * @return this
     */
    public final WorldAssemblyQuery resolutionId(int resolutionId) {
        this.resolutionId = resolutionId;
        return this;
    }

    @Override
    protected String resourceString() {
        return "wa";
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();

        if (resourceValue == null || resourceValue.isEmpty()) {
            throw new IllegalArgumentException("No council number supplied!");
        }
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        return resolutionId == 0 ? url : url + "&id=" + resolutionId;
    }
}
