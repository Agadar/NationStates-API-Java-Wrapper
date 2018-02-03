package com.github.agadar.nationstates.query;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

/**
 * Top parent class for all Queries to NationStates in general.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class AbstractQuery<Q extends AbstractQuery, R> {

    /**
     * User agent by which this library or its consumer is recognized.
     */
    protected final String userAgent;

    /**
     * The return type of this Query's execute()-method.
     */
    protected final Class<R> returnType;

    /**
     * Base URL to NationStates.
     */
    private final String baseUrl;

    /**
     * Constructor, setting the returnType.
     *
     * @param baseUrl Base URL to NationStates.
     * @param userAgent User agent by which this library or its consumer is
     * recognized.
     */
    protected AbstractQuery(String baseUrl, String userAgent) {
        returnType = ((Class) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        this.baseUrl = baseUrl;
        this.userAgent = userAgent;
    }

    /**
     * Validates the query parameters before executing the query. If the query
     * parameters are invalid, an exception is thrown. Child classes will want
     * to override this class (making sure to still call
     * super.validateQueryParameters()) if they have additional parameters to
     * validate.
     */
    protected void validateQueryParameters() {
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("No base URL set!");
        }
        if (userAgent == null || userAgent.isEmpty()) {
            throw new IllegalArgumentException("No User Agent set!");
        }
    }

    /**
     * Generates a String URL based on the supplied values. Subclasses might
     * want to override this method to build the String URL further to
     * accommodate for additional shards/modes/options/etc.
     *
     * @return the generated String URL
     */
    protected String buildURL() {
        return baseUrl;
    }

    /**
     * Closes the given InputStream. If an exception occurs, the exception is
     * not thrown or returned. Use this if you don't particularly care about
     * catching exceptions thrown by closing InputStreams.
     *
     * @param stream the InputStream to close
     */
    protected final static void closeInputStreamQuietly(InputStream stream) {
        try {
            stream.close();
        } catch (IOException ex) {
            // Ignore.
        }
    }
}
