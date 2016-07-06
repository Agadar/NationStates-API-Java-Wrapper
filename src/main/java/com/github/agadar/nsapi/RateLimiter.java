package com.github.agadar.nsapi;

import com.sun.istack.internal.logging.Logger;

/**
 * Enforces rate limiting for x requests per y milliseconds.
 * 
 * @author Agadar <https://github.com/Agadar/>
 */
public class RateLimiter 
{
    /** The round buffer we're using, with length set to x in 'x requests per y milliseconds. */
    private final long[] roundBuffer;
    /** The y in 'x requests per y milliseconds'. */
    private final long milliseconds;
    /** The current index in the round buffer, starting at 0. */
    private int index = 0;
    
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
            Logger.getLogger(RateLimiter.class).warning("Rate limit reached!"
                        + " Thread put to sleep for " + sleepFor + " milliseconds.");           
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
