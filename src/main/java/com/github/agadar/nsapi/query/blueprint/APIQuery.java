package com.github.agadar.nsapi.query.blueprint;

import com.github.agadar.nsapi.NSAPI;
import com.github.agadar.nsapi.NationStatesAPIException;
import com.github.agadar.nsapi.ratelimiter.DependantRateLimiter;
import com.github.agadar.nsapi.ratelimiter.RateLimiter;

/**
 * Top parent class for all Queries to the NationStates API.
 * 
 * @author Agadar (https://github.com/Agadar/)
 * 
 * @param <Q> the child class that extends this abstract class
 * @param <R> the type the child class' execute()-function returns
 */
public abstract class APIQuery<Q extends APIQuery, R> extends AbstractQuery<Q, R>
{
    /**
     * The general rate limiter for all API calls. The mandated rate limit is 50
     * requests per 30 seconds. To make sure we're on the safe side, we reduce
     * this to 45 requests per 30 seconds. To get a spread-like pattern instead
     * of a burst-like pattern, we make this into 6 requests per 4 seconds.
     */
    protected static final RateLimiter rateLimiter = new RateLimiter(6, 4000);
    
    /**
     * Rate limiter for API calls when scraping. Reduces the rate limit further 
     * to just 1 request per second, as suggested by the official documentation.
     */
    private static final DependantRateLimiter reducedRateLimiter = 
        new DependantRateLimiter(1, 1000, rateLimiter);
    
    /** 
     * The resource value, e.g. the nation's or region's name. 
     * Set by the constructor.
     */
    protected final String resourceValue;
    
    /** Whether to use the reduced rate limiter. */
    private boolean slowMode = false;
    
    /**
     * Constructor. Sets the resource value, e.g. the nation's or region's name.
     * 
     * @param resourceValue the resource value
     */
    protected APIQuery(String resourceValue)
    {
        this.resourceValue = resourceValue;
    }
    
    /**
     * Makes the Query execute in slow mode, reducing the rate limit from 6
     * requests per 4 seconds to only 1 request per second. This is suggested
     * by the official documentations when scraping i.e. when requesting a LOT
     * of data that cannot be retrieved from the daily dumps.
     * 
     * @return this 
     */
    public Q slowMode()
    {
        slowMode = true;
        return (Q) this;
    }
    
    /**
     * Returns the rate limiter to use in the makeRequest()-function. Child
     * classes can override this to supply their own rate limiter if needed.
     * 
     * @return the rate limiter to use in the makeRequest()-function
     */
    protected RateLimiter getRateLimiter()
    {
        return slowMode ? reducedRateLimiter : rateLimiter;
    }
    
    @Override
    public <T extends R> T execute(Class<T> type)
    {
        getRateLimiter().Await();
        return super.execute(type);
    }

    @Override
    protected void validateQueryParameters()
    {
        super.validateQueryParameters();
        String resourceString = resourceString();
        
        // Ensure resourceValue is not null or empty if the resource string isn't either.
        if (resourceString != null && !resourceString.isEmpty() &&
                (resourceValue == null || resourceValue.isEmpty()))
        {        
            throw new NationStatesAPIException("'resourceValue' may not be "
                + "null or empty if 'resourceString' isn't null or empty!");
        }
    }
    
    @Override
    protected String buildURL()
    {
        // Start out by concatenating base url and API version number
        String url = super.buildURL() + "cgi-bin/api.cgi?v=" + NSAPI.API_VERSION;
        String resourceString = resourceString();
        
        // If we're not using the top resource, then append resource and resourceValue
        if (resourceString != null && !resourceString.isEmpty())
        {
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
