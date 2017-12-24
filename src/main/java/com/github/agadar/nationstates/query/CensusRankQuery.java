package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.IXmlConverter;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;
import com.github.agadar.nationstates.shard.Shard;

/**
 * Intermediate parent class that adds options for census ranks.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 * @param <S> the shard type the child class accepts
 */
public abstract class CensusRankQuery<Q extends CensusRankQuery, R, S extends Shard> extends CensusQuery<Q, R, S> {

    /**
     * What rank to start the retrieved census rank scores list from.
     */
    private int censusRanksStart;

    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     *
     * @param xmlConverter
     * @param generalRateLimiter
     * @param scrapingRateLimiter
     * @param baseUrl
     * @param userAgent
     * @param apiVersion
     * @param resourceValue the resource value
     */
    public CensusRankQuery(IXmlConverter xmlConverter, IRateLimiter generalRateLimiter, IRateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String resourceValue) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, resourceValue);
    }

    /**
     * Sets a rank to start the retrieved census rank scores list from. Does
     * nothing if the CensusRanks shard is not selected.
     *
     * @param rank the rank to start the retrieved census rank scores list from
     * @return this
     */
    public final Q censusRanksStart(int rank) {
        this.censusRanksStart = rank;
        return (Q) this;
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();
        return censusRanksStart == 0 ? url : url + "&start=" + censusRanksStart;
    }
}
