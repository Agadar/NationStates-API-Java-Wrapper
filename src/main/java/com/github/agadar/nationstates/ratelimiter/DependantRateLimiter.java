package com.github.agadar.nationstates.ratelimiter;

/**
 * A rate limiter that is also dependant on another rate limiter.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public final class DependantRateLimiter extends RateLimiter {

    /**
     * The rate limiter this one is dependant on.
     */
    private final IRateLimiter dependant;

    /**
     * Constructs a new RateLimiter.
     *
     * @param requests the x in 'x requests per y milliseconds
     * @param milliseconds the y in 'x requests per y milliseconds
     * @param dependant the rate limiter this one is dependant on
     */
    public DependantRateLimiter(IRateLimiter dependant, int requests, int milliseconds) {
        super(requests, milliseconds);

        if (dependant == null) {
            throw new IllegalArgumentException("'dependant' may not be null");
        }
        this.dependant = dependant;
    }

    @Override
    public boolean lock() {
        return super.lock() ? dependant.lock() : false;
    }

    @Override
    public void unlock() {
        dependant.unlock();
        super.unlock();
    }

    @Override
    public int getMillisecondsBetweenLocks() {
        final int myTime = super.getMillisecondsBetweenLocks();
        final int dependantTime = dependant.getMillisecondsBetweenLocks();
        return Math.max(myTime, dependantTime);
    }
}
