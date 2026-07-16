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
     * Based on the latest RateLimit-Remaining header received. If true, then the current time window is exhausted, and
     * we must wait for the next one before we can continue making requests.
     */
    private boolean currentTimeWindowExhausted = false;

    /**
     * Based on the latest RateLimit-Reset and/or Retry-After header received. Represents when the next time window starts
     * (and thus when we can make a new set of requests).
     */
    private long nextTimeWindowStart = System.currentTimeMillis();

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
            log.debug("The current thread was interrupted");
            return false;
        }

        // Recalculate currentTimeWindowExhausted as we may have reached the timestamp already.
        long currentTime = System.currentTimeMillis();
        currentTimeWindowExhausted = currentTimeWindowExhausted && nextTimeWindowStart > currentTime;
        log.trace("currentTimeWindowExhausted set to {}", currentTimeWindowExhausted);

        // Sleep until we can make calls again.
        if (currentTimeWindowExhausted) {
            try {
                long sleepFor = nextTimeWindowStart - currentTime;
                log.debug("Rate limit reached, sleeping for {} milliseconds...", sleepFor);
                Thread.sleep(sleepFor);
                log.debug("Thread has awoken");

            } catch (InterruptedException ex) {
                // We were interrupted, so unlock to prevent a deadlock, then return false.
                Thread.currentThread().interrupt();
                lock.unlock();
                log.debug("The current thread was interrupted");
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
        long longestWait = retryAfter > 0 ? retryAfter : rateLimitRemaining <= 0 ? rateLimitReset : 0;
        nextTimeWindowStart = System.currentTimeMillis() + longestWait * 1000;
        currentTimeWindowExhausted = retryAfter > 0 || rateLimitRemaining <= 0;

        log.trace("currentTimeWindowExhausted set to {} and nextTimeWindowStart set to {} using rateLimitRemaining {}, rateLimitReset {}, retryAfter {}",
                currentTimeWindowExhausted, nextTimeWindowStart, rateLimitRemaining, rateLimitReset, retryAfter);
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
        return 0;   // Cannot be calculated for this rate limiter type.
    }
}
