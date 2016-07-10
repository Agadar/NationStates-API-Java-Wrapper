package com.github.agadar.nsapi.query.blueprint;

import com.github.agadar.nsapi.NSAPI;
import com.github.agadar.nsapi.ratelimiter.RateLimiter;

/**
 * Top parent class for all Queries to the NationStates API.
 * 
 * @author Agadar <https://github.com/Agadar/>
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
     * of a burst-like pattern, we make this into 3 requests per 2 seconds.
     */
    protected static final RateLimiter rateLimiter = new RateLimiter(3, 2000);
    
    /** 
     * The resource value, e.g. the nation's or region's name. 
     * Set by the constructor.
     */
    protected String resourceValue;
    
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
     * Returns the rate limiter to use in the makeRequest()-function. Child
     * classes can override this to supply their own rate limiter if needed.
     * 
     * @return the rate limiter to use in the makeRequest()-function
     */
    protected RateLimiter getRateLimiter()
    {
        return rateLimiter;
    }
    
    @Override
    public final R execute()
    {
        getRateLimiter().Await();
        return super.execute();
    }
    
    @Override
    protected String buildURL()
    {
        // Start out by concatenating base url and API version number
        String url = super.buildURL() + "cgi-bin/api.cgi?v=" + NSAPI.API_VERSION;
        
        // If we're not using the top resource, then append resource and resourceValue
        if (!resourceString().isEmpty())
        {
            // Ensure resourceValue is not null or empty           
            if (resourceValue == null || resourceValue.isEmpty())
            {
                throw new IllegalArgumentException("'resourceValue' may not be "
                        + "null or empty when not using the top level resource!");
            }
            
            // Append resource and resourceValue
            url += "&" + resourceString() + "=" + resourceValue;
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
