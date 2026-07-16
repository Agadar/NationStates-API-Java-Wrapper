package com.github.agadar.nationstates.ratelimiter;

/**
 * Rate limiter based on local timekeeping, that is also dependent on another rate limiter.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class ClientSideDependantRateLimiter extends ClientSideRateLimiter {

    /**
     * The rate limiter this one is depending on.
     */
    private final RateLimiter dependant;

    /**
     * Constructs a new RateLimiter.
     *
     * @param requests     the x in 'x requests per y milliseconds
     * @param milliseconds the y in 'x requests per y milliseconds
     * @param dependant    the rate limiter this one is depending on
     */
    public ClientSideDependantRateLimiter(RateLimiter dependant, int requests, int milliseconds) {
        super(requests, milliseconds);

        if (dependant == null) {
            throw new IllegalArgumentException("'dependant' may not be null");
        }
        this.dependant = dependant;
    }

    @Override
    public boolean lock() {
        return super.lock() && dependant.lock();
    }

    @Override
    public void updateValues(int rateLimitRemaining, int rateLimitReset, int retryAfter) {
        dependant.updateValues(rateLimitRemaining, rateLimitReset, retryAfter);
        super.updateValues(rateLimitRemaining, rateLimitReset, retryAfter);
    }

    @Override
    public void unlock() {
        dependant.unlock();
        super.unlock();
    }

    @Override
    public int getMillisecondsBetweenLocks() {
        int myTime = super.getMillisecondsBetweenLocks();
        int dependantTime = dependant.getMillisecondsBetweenLocks();
        return Math.max(myTime, dependantTime);
    }
}
