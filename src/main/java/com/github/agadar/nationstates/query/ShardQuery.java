package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.XmlConverter;

import lombok.NonNull;

import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import com.github.agadar.nationstates.shard.Shard;

/**
 * Intermediate parent class that adds support for shards.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 * @param <S> the shard type the child class accepts
 */
@SuppressWarnings("rawtypes")
public abstract class ShardQuery<Q extends ShardQuery, R, S extends Shard> extends APIQuery<Q, R> {

    /**
     * See shards(...).
     */
    protected S[] shards;

    public ShardQuery(XmlConverter xmlConverter, RateLimiter generalRateLimiter, RateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String resourceValue) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, resourceValue);
    }

    /**
     * Sets the shards.
     *
     * @param shards the shards to set
     * @return this
     */
    @SuppressWarnings("unchecked")
    public final Q shards(@NonNull S... shards) {
        this.shards = shards;
        return (Q) this;
    }

    @Override
    protected String buildURL() {
        String url = super.buildURL();

        // If there are shards present, then append them to url
        if (shards != null && shards.length > 0) {
            url += "&q=" + shards[0].shardName();

            for (int i = 1; i < shards.length; i++) {
                url += "+" + shards[i].shardName();
            }
        }

        return url;
    }
}
