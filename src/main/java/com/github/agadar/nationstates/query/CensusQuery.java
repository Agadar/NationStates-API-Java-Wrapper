package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.XmlConverter;

import lombok.NonNull;

import com.github.agadar.nationstates.enumerator.CensusId;
import com.github.agadar.nationstates.enumerator.CensusMode;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import com.github.agadar.nationstates.shard.Shard;

/**
 * Intermediate parent class that adds options for censuses.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 * @param <S> the shard type the child class accepts
 */
@SuppressWarnings("rawtypes")
public abstract class CensusQuery<Q extends CensusQuery, R, S extends Shard> extends ShardQuery<Q, R, S> {

    /**
     * See censusIds(...).
     */
    private CensusId[] censusIds;

    /**
     * See censusModes(...).
     */
    private CensusMode[] censusModes;

    /**
     * See censusHistoryFrom(...).
     */
    private long censusHistoryFrom;

    /**
     * See censusHistoryTo(...).
     */
    private long censusHistoryTo;

    public CensusQuery(XmlConverter xmlConverter, RateLimiter generalRateLimiter, RateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String resourceValue) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, resourceValue);
    }

    /**
     * Sets the census id's. Does nothing if none of the following shards are set:
     * Census, CensusRanks, CensusName, CensusDescriptions, CensusScale, or
     * CensusTitle.
     *
     * Note: setting multiple id's is only supported for the Census shard.
     *
     * @param censusIds the census id's to set
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q censusIds(@NonNull CensusId... censusIds) {
        this.censusIds = censusIds;
        return (Q) this;
    }

    /**
     * Sets the census modes. Does nothing if the Census shard is not selected. Note
     * that the History census mode cannot be combined with any other modes.
     *
     * @param censusModes the census modes to select
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q censusModes(@NonNull CensusMode... censusModes) {
        this.censusModes = censusModes;
        return (Q) this;
    }

    /**
     * Sets the lower bound in UNIX timestamp for the History census mode.
     * Implicitly sets the History census mode.
     *
     * @param timestamp the lower bound in UNIX timestamp for the History census
     *                  mode
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q censusHistoryFrom(long timestamp) {
        this.censusHistoryFrom = timestamp;
        return (Q) this;
    }

    /**
     * Sets the upper bound in UNIX timestamp for the History census mode.
     * Implicitly sets the History census mode.
     *
     * @param timestamp the upper bound in UNIX timestamp for the History census
     *                  mode
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q censusHistoryTo(long timestamp) {
        this.censusHistoryTo = timestamp;
        return (Q) this;
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();

        // If census id's were supplied, append them to url
        if (censusIds != null && censusIds.length > 0) {
            url += "&scale=" + censusIds[0].toInt();

            for (int i = 1; i < censusIds.length; i++) {
                url += "+" + censusIds[i].toInt();
            }
        }

        // If modes were supplied, append them to url
        if (censusModes != null && censusModes.length > 0) {
            url += "&mode=" + censusModes[0].toString();

            for (int i = 1; i < censusModes.length; i++) {
                url += "+" + censusModes[i].toString();
            }
        }

        // If census history from and to were supplied, append them to url
        if (censusHistoryFrom != 0) {
            url += "&from=" + censusHistoryFrom;
        }

        if (censusHistoryTo != 0) {
            url += "&to=" + censusHistoryTo;
        }

        return url;
    }
}
