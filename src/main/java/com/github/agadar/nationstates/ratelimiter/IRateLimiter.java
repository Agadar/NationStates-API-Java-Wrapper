package com.github.agadar.nationstates.ratelimiter;

/**
 * Enforces thread-safe rate limiting for x requests per y milliseconds.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public interface IRateLimiter {

    /**
     * Call this BEFORE executing code that needs to be rate limited. Blocks the
     * thread as long as necessary so that the rate limit isn't violated.
     *
     * @return True if the thread was not interrupted while waiting to continue.
     */
    public boolean lock();

    /**
     * Call this AFTER executing code that needs to be rate limited. Failure to
     * call this will result in other threads being blocked indefinitely.
     */
    public void unlock();
    
    /**
     * @return The aforementioned y divided by the aforementioned x.
     */
    public int getMillisecondsBetweenLocks();
}
