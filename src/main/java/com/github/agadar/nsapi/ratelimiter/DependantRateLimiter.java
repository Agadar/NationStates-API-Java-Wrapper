package com.github.agadar.nsapi.ratelimiter;

import static com.github.agadar.nsapi.ratelimiter.RateLimiter.logger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enforces thread-safe rate limiting for x requests per y milliseconds. This
 * rate limiter is dependant on another rate limiter. In other words, this
 * limiter's Await()- function calls the supplied rate limiter's
 * Await()-function, on top of its normal functionality.
 *
 * @author Agadar <https://github.com/Agadar/>
 */
public final class DependantRateLimiter extends RateLimiter
{
    /** The rate limiter this one is dependant on. */
    private final RateLimiter dependant;
    
    /**
     * Constructs a new RateLimiter.
     * 
     * @param requests the x in 'x requests per y milliseconds'
     * @param milliseconds the y in 'x requests per y milliseconds'
     * @param dependant the rate limiter this one is dependant on
     */
    public DependantRateLimiter(int requests, int milliseconds, RateLimiter dependant)
    {
        super(requests, milliseconds);
        
        if (dependant == null)
        {
            throw new IllegalArgumentException("'dependant' should not be null!");
        }
        
        this.dependant = dependant;
    }
    
    /**
     * Call this method before any code you want to enforce the rate limit on.
     * This code is identical to RateLimiter.Await()'s, save for the call to
     * the dependant's Await()-function.
     */
    @Override
    public synchronized void Await()
    {
        // Retrieve oldest and current timestamps, calculate difference.
        long diff = System.currentTimeMillis() - roundBuffer[index];
        
        // If the difference is less than the y in 'x requests per y milliseconds'
        // then print a warning and sleep for the duration of the difference.
        if (diff < milliseconds)
        {
            long sleepFor = milliseconds - diff;
            logger.log(Level.INFO, "Rate limit reached. Thread put to sleep for "
                    + "{0} milliseconds.", sleepFor);
            
            try
            {
                Thread.sleep(sleepFor);
            }
            catch (InterruptedException ex)
            {
                throw new RuntimeException("RateLimiter.class blew up!", ex);
            }
        }
        
        // Call the dependant's Await()-function.
        dependant.Await();
        
        // Finally, update the oldest timestamp and increment the index.
        roundBuffer[index] = System.currentTimeMillis();
        index = ++index % roundBuffer.length;
    }
}
