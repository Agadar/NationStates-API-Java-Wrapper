package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.IXmlConverter;
import com.github.agadar.nationstates.ratelimiter.IRateLimiter;
import java.io.InputStream;
import java.util.Scanner;

/**
 * A query to the NationStates API's utility resource, retrieving the version
 * number of the latest live API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class VersionQuery extends APIQuery<VersionQuery, Integer> {

    /**
     * Constructor.
     *
     * @param xmlConverter
     * @param generalRateLimiter
     * @param scrapingRateLimiter
     * @param baseUrl
     * @param userAgent
     * @param apiVersion
     */
    public VersionQuery(IXmlConverter xmlConverter, IRateLimiter generalRateLimiter, IRateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, "version");
    }

    @Override
    protected String resourceString() {
        return "a";
    }

    @Override
    protected <T> T translateResponse(InputStream response, Class<T> type) {
        Scanner s = new Scanner(response).useDelimiter("\\A");
        String body = s.hasNext() ? s.next().trim() : "";
        return (T) Integer.valueOf(body);
    }
}
