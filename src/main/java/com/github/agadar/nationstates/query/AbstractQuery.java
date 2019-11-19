package com.github.agadar.nationstates.query;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.agadar.nationstates.exception.NationStatesAPIException;
import com.github.agadar.nationstates.exception.NationStatesResourceNotFoundException;
import com.github.agadar.nationstates.function.CheckedFunction;

import lombok.extern.slf4j.Slf4j;

/**
 * Top parent class for all Queries to NationStates in general.
 *
 * @author Agadar (https://github.com/Agadar/)
 *
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
@SuppressWarnings("rawtypes")
@Slf4j
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
     * @param baseUrl   Base URL to NationStates.
     * @param userAgent User agent by which this library or its consumer is
     *                  recognized.
     */
    @SuppressWarnings("unchecked")
    protected AbstractQuery(String baseUrl, String userAgent) {
        returnType = ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
        this.baseUrl = baseUrl;
        this.userAgent = userAgent;
    }

    /**
     * Validates the query parameters before executing the query. If the query
     * parameters are invalid, an exception is thrown. Child classes will want to
     * override this class (making sure to still call
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
     * Generates a String URL based on the supplied values. Subclasses might want to
     * override this method to build the String URL further to accommodate for
     * additional shards/modes/options/etc.
     *
     * @return the generated String URL
     */
    protected String buildURL() {
        return baseUrl;
    }

    /**
     * Closes the given InputStream. If an exception occurs, the exception is not
     * thrown or returned. Use this if you don't particularly care about catching
     * exceptions thrown by closing InputStreams.
     *
     * @param stream the InputStream to close
     */
    protected final void closeInputStreamQuietly(InputStream stream) {
        try {
            stream.close();
        } catch (IOException ex) {
            log.error("An error occured while silently closing the input stream", ex);
        }
    }

    /**
     * Makes a GET request to the NationStates API. Throws exception if the call
     * failed.
     *
     * @param urlStr        The url to make the request to.
     * @param resultHandler The result handler, expected to parse an InputStream to
     *                      the desired result.
     * @return The parsed result.
     * @throws NationStatesAPIException If the call failed.
     */
    protected final <T> T makeRequest(String urlStr, CheckedFunction<InputStream, T> resultHandler)
            throws NationStatesAPIException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", userAgent);
            int responseCode = conn.getResponseCode();
            String response = formatResponse(urlStr, conn, responseCode);
            log.info(response);
            InputStream stream = conn.getErrorStream();

            if (stream == null) {
                stream = conn.getInputStream();
                T result = resultHandler.apply(stream);
                closeInputStreamQuietly(stream);
                return result;

            } else {
                closeInputStreamQuietly(stream);
                if (responseCode == 404) {
                    throw new NationStatesResourceNotFoundException(response);
                }
                throw new NationStatesAPIException(response);
            }
        } catch (Exception ex) {
            log.error("An error occured while handling a request to the API", ex);
            throw new NationStatesAPIException(ex);

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private String formatResponse(String urlStr, HttpURLConnection conn, int responseCode) throws IOException {
        return String.format("NationStates API returned: '%s %s' from URL: %s", responseCode, conn.getResponseMessage(),
                urlStr);
    }
}
