package com.github.agadar.nationstates.query;

import java.io.InputStream;

import com.github.agadar.nationstates.exception.NationStatesAPIException;
import com.github.agadar.nationstates.function.CheckedFunction;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import com.github.agadar.nationstates.xmlconverter.XmlConverter;

import lombok.NonNull;

/**
 * Top parent class for all Queries to the NationStates API.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
@SuppressWarnings("rawtypes")
public abstract class APIQuery<Q extends APIQuery, R> extends AbstractQuery<Q, R> {

    /**
     * The general rate limiter for all API calls.
     */
    protected final RateLimiter generalRateLimiter;

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
     * Used for XML conversions.
     */
    private final XmlConverter xmlConverter;

    /**
     * Rate limiter for API calls when scraping.
     */
    private final RateLimiter scrapingRateLimiter;

    /**
     * The version of the NationStates API to target.
     */
    private final int apiVersion;

    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     *
     * @param xmlConverter        The converter for translating XML from the API to
     *                            objects.
     * @param resourceValue       The resource value (e.g. 'nation', 'region'...)
     * @param generalRateLimiter  The default rate limiter
     * @param baseUrl             The URL to the API to consume
     * @param userAgent           The User Agent to communicate with
     * @param apiVersion          The version of the API to expect to consume
     * @param scrapingRateLimiter Rate limiter used when 'scraping'
     */
    protected APIQuery(XmlConverter xmlConverter, RateLimiter generalRateLimiter, RateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion, String resourceValue) {
        super(baseUrl, userAgent);
        this.resourceValue = resourceValue;
        this.generalRateLimiter = generalRateLimiter;
        this.scrapingRateLimiter = scrapingRateLimiter;
        this.apiVersion = apiVersion;
        this.xmlConverter = xmlConverter;
    }

    /**
     * Makes the Query execute in slow mode, reducing the rate limit from 6 requests
     * per 4 seconds to only 1 request per second. This is suggested by the official
     * documentations when scraping i.e. when requesting a LOT of data that cannot
     * be retrieved from the daily dumps.
     *
     * @return this
     */
    @SuppressWarnings("unchecked")
    public Q slowMode() {
        slowMode = true;
        return (Q) this;
    }

    /**
     * Executes this query, returning any result.
     *
     * @return the result
     * @throws NationStatesAPIException
     */
    public R execute() throws NationStatesAPIException {
        return execute(returnType);
    }

    /**
     * Executes this query, returning any result.
     *
     * @param type the type to return
     * @return the result
     * @throws NationStatesAPIException
     */
    public <T> T execute(@NonNull Class<T> type) throws NationStatesAPIException {
        if (getRateLimiter().lock()) {
            try {
                validateQueryParameters();
                CheckedFunction<InputStream, T> resultHandler = (istream) -> translateResponse(istream, type);
                String url = buildURL().replace(' ', '_');
                return makeRequest(url, resultHandler);

            } finally {
                getRateLimiter().unlock();
            }
        }
        return null;
    }

    /**
     * Returns the rate limiter to use in the makeRequest()-function. Child classes
     * can override this to supply their own rate limiter if needed.
     *
     * @return the rate limiter to use in the makeRequest()-function
     */
    protected RateLimiter getRateLimiter() {
        return slowMode ? scrapingRateLimiter : generalRateLimiter;
    }

    @Override
    protected void validateQueryParameters() {
        super.validateQueryParameters();
        String resourceString = resourceString();

        // Ensure resourceValue is not null or empty if the resource string isn't
        // either.
        if (resourceString != null && !resourceString.isEmpty() && (resourceValue == null || resourceValue.isEmpty())) {
            throw new IllegalArgumentException(
                    "'resourceValue' may not be null or empty if 'resourceString' isn't null or empty!");
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
     * Translates the stream response to the object this Query wishes to return via
     * its execute() function. The standard way to translate is via JAXB, which
     * assumes the stream is in a valid XML-format. Child classes might want to
     * override this function if they wish to return primitives or something else.
     *
     * @param <T>      type to parse to
     * @param response the response to translate
     * @param type     type to parse to
     * @return the translated response
     * @throws NationStatesAPIException
     */
    protected <T> T translateResponse(InputStream response, Class<T> type) throws NationStatesAPIException {
        return xmlConverter.xmlToObject(response, type);
    }

    /**
     * Gives the resource string of this Query, e.g. 'nation', 'region', etc.
     *
     * @return the resource string of this Query
     */
    protected abstract String resourceString();
}
