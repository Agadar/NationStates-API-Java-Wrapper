package com.github.agadar.nsapi.ratelimiter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enforces thread-safe rate limiting for x requests per y milliseconds.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class RateLimiter
{
    /** The logger for this object. */
    protected static final Logger logger = Logger.getLogger(RateLimiter.class.getName());
    
    /** The round buffer we're using, with length set to x in 'x requests per y milliseconds. */
    protected final long[] roundBuffer;
    /** The y in 'x requests per y milliseconds'. */
    protected final long milliseconds;
    /** The current index in the round buffer, starting at 0. */
    protected int index = 0;
    
    /**
     * Constructs a new RateLimiter.
     * 
     * @param requests the x in 'x requests per y milliseconds'
     * @param milliseconds the y in 'x requests per y milliseconds'
     */
    public RateLimiter(int requests, int milliseconds)
    {
        if (requests <= 0)
        {
            throw new IllegalArgumentException("'requests' should be > 0!");
        }
        
        if (milliseconds <= 0)
        {
            throw new IllegalArgumentException("'milliseconds' should be > 0!");
        }
        
        this.roundBuffer = new long[requests];
        this.milliseconds = milliseconds;       
    }
    
    /**
     * Call this method before any code you want to enforce the rate limit on.
     */
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
        
        // Finally, update the oldest timestamp and increment the index.
        roundBuffer[index] = System.currentTimeMillis();
        index = ++index % roundBuffer.length;
    }
}
