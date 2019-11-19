package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.xmlconverter.XmlConverter;
import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import java.io.InputStream;
import java.util.Scanner;

/**
 * A query to the NationStates API's utility resource, retrieving the version
 * number of the latest live API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class VersionQuery extends APIQuery<VersionQuery, Integer> {

    public VersionQuery(XmlConverter xmlConverter, RateLimiter generalRateLimiter, RateLimiter scrapingRateLimiter,
            String baseUrl, String userAgent, int apiVersion) {
        super(xmlConverter, generalRateLimiter, scrapingRateLimiter, baseUrl, userAgent, apiVersion, "version");
    }

    @Override
    protected String resourceString() {
        return "a";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T parseResponse(InputStream response, Class<T> type) {
        @SuppressWarnings("resource")
        Scanner s = new Scanner(response).useDelimiter("\\A");
        String body = s.hasNext() ? s.next().trim() : "";
        return (T) Integer.valueOf(body);
    }
}
