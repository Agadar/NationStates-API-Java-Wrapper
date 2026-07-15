package com.github.agadar.nationstates.ratelimiter;

/**
 * Enforces thread-safe rate limiting.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public interface RateLimiter {

    /**
     * Call this BEFORE executing code that needs to be rate limited. Blocks the
     * thread as long as necessary so that the rate limit isn't violated.
     *
     * @return True if the thread was not interrupted while waiting to continue.
     */
    boolean lock();

    /**
     * Makes sure the rate limiter's values are updated.
     *
     * @param rateLimitRemaining How many more requests can be made within the current time window.
     * @param rateLimitReset     The number of seconds remaining in the current time window.
     * @param retryAfter         Once blocked from accessing the API, we wait this amount of seconds before trying again.
     */
    void updateValues(int rateLimitRemaining, int rateLimitReset, int retryAfter);

    /**
     * Call this AFTER executing code that needs to be rate limited. Failure to
     * call this will result in other threads being blocked indefinitely.
     */
    void unlock();

    /**
     * @return The aforementioned y divided by the aforementioned x.
     */
    int getMillisecondsBetweenLocks();
}
