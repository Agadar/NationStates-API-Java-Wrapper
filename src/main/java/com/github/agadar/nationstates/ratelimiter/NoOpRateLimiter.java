package com.github.agadar.nationstates.ratelimiter;

/**
 * No-op rate limiter for when rate limiting is not applicable.
 */
public class NoOpRateLimiter implements RateLimiter {

    @Override
    public boolean lock() {
        return true;
    }

    @Override
    public void updateValues(int rateLimitRemaining, int rateLimitReset, int retryAfter) {
        // No-op
    }

    @Override
    public void unlock() {
        // No-op
    }

    @Override
    public int getMillisecondsBetweenLocks() {
        return 0;
    }
}
