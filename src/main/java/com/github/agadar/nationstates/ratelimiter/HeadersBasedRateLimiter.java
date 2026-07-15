package com.github.agadar.nationstates.ratelimiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Rate limiter based on HTTP header values returned by the NS API.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@Slf4j
public class HeadersBasedRateLimiter implements RateLimiter {

    /**
     * Underlying lock used for thread synchronization.
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Based on the latest RateLimit-Remaining header received. Starts at 50.
     * This represents how many more requests can be made within the current time window.
     */
    private int rateLimitRemaining = 50;

    /**
     * Based on the latest RateLimit-Reset header received. Starts at 30.
     * This represents the number of seconds remaining in the current time window.
     */
    private int rateLimitReset = 30;

    /**
     * Based on the latest Retry-After header received. Starts at 0.
     * Once blocked from accessing the API, we wait this amount of seconds before trying again.
     * If we properly adhere to RateLimit-Remaining and RateLimit-Reset, then this should only happen in
     * exceptional circumstances.
     */
    private int retryAfter = 0;

    @Override
    public boolean lock() {
        // Throw exception if this is called while we already hold the lock.
        if (lock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Lock is already held by current thread");
        }

        // Block until we've obtained the lock.
        lock.lock();

        // Thread was interrupted while we waited to obtain the lock.
        if (Thread.currentThread().isInterrupted()) {
            lock.unlock();
            return false;
        }

        // Determine seconds to sleep, with retryAfter taking priority.
        int sleepSeconds = retryAfter > 0 ? retryAfter : rateLimitRemaining <= 0 ? rateLimitReset : 0;

        // Sleep until we can make calls again.
        if (sleepSeconds > 0) {
            try {
                log.debug("Rate limit reached, sleeping for {} seconds...", sleepSeconds);
                Thread.sleep(sleepSeconds);

            } catch (InterruptedException ex) {
                // We were interrupted, so unlock to prevent a deadlock, then return false.
                log.debug("The sleeping thread was interrupted");
                Thread.currentThread().interrupt();
                lock.unlock();
                return false;
            }
        }
        // We weren't interrupted, so return true.
        return true;
    }

    @Override
    public void updateValues(int rateLimitRemaining, int rateLimitReset, int retryAfter) {
        if (!lock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Lock is not being held by current thread");
        }
        log.trace("Setting rateLimitRemaining to {}, rateLimitReset to {}, retryAfter to {}", rateLimitRemaining, rateLimitReset, retryAfter);
        this.rateLimitRemaining = rateLimitRemaining;
        this.rateLimitReset = rateLimitReset;
        this.retryAfter = retryAfter;
    }

    @Override
    public void unlock() {
        if (!lock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Lock is not being held by current thread");
        }
        lock.unlock();
    }

    @Override
    public int getMillisecondsBetweenLocks() {
        throw new UnsupportedOperationException("Not yet implemented"); // TODO: Implement(?).
    }
}
