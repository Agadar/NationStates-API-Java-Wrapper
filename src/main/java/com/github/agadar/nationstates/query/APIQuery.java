package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.IXmlConverter;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;

/**
 * Top parent class for all Queries to the NationStates API.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class APIQuery<Q extends APIQuery, R> extends AbstractQuery<Q, R> {

    /**
     * The general rate limiter for all API calls.
     */
    protected final IRateLimiter generalRateLimiter;

    /**
     * Rate limiter for API calls when scraping.
     */
    private final IRateLimiter scrapingRateLimiter;
    
    /**
     * The version of the NationStates API to target.
     */
    private final int apiVersion;

    /**
     * The resource value, e.g. the nation's or region's name. Set by the
     * constructor.
     */
    protected final String resourceValue;

    /**
     * Whether to use the reduced rate limiter.
     */
    private boolean slowMode = false;

    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     *
     * @param xmlConverter
     * @param resourceValue the resource value
     * @param generalRateLimiter
     * @param baseUrl
     * @param userAgent
     * @param apiVersion
     * @param scrapingRateLimiter
     */
    protected APIQuery(IXmlConverter xmlConverter, IRateLimiter generalRateLimiter, IRateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String resourceValue) {
        super(xmlConverter, baseUrl, userAgent);
        this.resourceValue = resourceValue;
        this.generalRateLimiter = generalRateLimiter;
        this.scrapingRateLimiter = scrapingRateLimiter;
        this.apiVersion = apiVersion;
    }

    /**
     * Makes the Query execute in slow mode, reducing the rate limit from 6
     * requests per 4 seconds to only 1 request per second. This is suggested by
     * the official documentations when scraping i.e. when requesting a LOT of
     * data that cannot be retrieved from the daily dumps.
     *
     * @return this
     */
    public Q slowMode() {
        slowMode = true;
        return (Q) this;
    }

    /**
     * Returns the rate limiter to use in the makeRequest()-function. Child
     * classes can override this to supply their own rate limiter if needed.
     *
     * @return the rate limiter to use in the makeRequest()-function
     */
    protected IRateLimiter getRateLimiter() {
        return slowMode ? scrapingRateLimiter : generalRateLimiter;
    }

    @Override
    public <T> T execute(Class<T> type) {
        if (getRateLimiter().lock()) {
            try {
                return super.execute(type);
            } catch (Exception ex) {
                throw ex;
            } finally {
                getRateLimiter().unlock();
            }
        }
        return null;
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();
        final String resourceString = resourceString();

        // Ensure resourceValue is not null or empty if the resource string isn't either.
        if (resourceString != null && !resourceString.isEmpty()
                && (resourceValue == null || resourceValue.isEmpty())) {
            throw new IllegalArgumentException("'resourceValue' may not be "
                    + "null or empty if 'resourceString' isn't null or empty!");
        }
    }

    @Override
    protected String buildURL() {
        // Start out by concatenating base url and API version number
        String url = super.buildURL() + "cgi-bin/api.cgi?v=" + apiVersion;
        final String resourceString = resourceString();

        // If we're not using the top resource, then append resource and resourceValue
        if (resourceString != null && !resourceString.isEmpty()) {
            url += "&" + resourceString + "=" + resourceValue;
        }

        // Finally, return the generated url
        return url;
    }

    /**
     * Gives the resource string of this Query, e.g. 'nation', 'region', etc.
     *
     * @return the resource string of this Query
     */
    protected abstract String resourceString();
}
