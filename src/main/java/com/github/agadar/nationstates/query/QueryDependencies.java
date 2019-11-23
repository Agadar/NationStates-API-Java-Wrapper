package com.github.agadar.nationstates.query;

import com.github.agadar.nationstates.ratelimiter.RateLimiter;
import com.github.agadar.nationstates.xmlconverter.XmlConverter;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * Contains the basic dependencies required for most queries.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
@Getter
@Builder
public class QueryDependencies {
    /**
     * The converter for translating XML from the API to objects.
     */
    @NonNull
    private XmlConverter xmlConverter;
    /**
     * The general rate limiter for all API calls.
     */
    @NonNull
    private RateLimiter generalRateLimiter;
    /**
     * Rate limiter for API calls when scraping.
     */
    @NonNull
    private RateLimiter scrapingRateLimiter;
    /**
     * The URL to the API to consume.
     */
    @NonNull
    private String baseUrl;
    /**
     * The User Agent to send to the API to identify the application.
     */
    @NonNull
    private String userAgent;
    /**
     * The version of the NationStates API to target.
     */
    private int apiVersion;
}
