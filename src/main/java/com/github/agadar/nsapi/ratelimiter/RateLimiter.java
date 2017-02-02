package com.github.agadar.nsapi.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enforces thread-safe rate limiting for x requests per y milliseconds.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class RateLimiter {

    /**
     * The logger for this object.
     */
    private static final Logger LOGGER = Logger.getLogger(RateLimiter.class.getName());
    /**
     * The round buffer we're using, with length set to x in 'x requests per y
     * milliseconds.
     */
    private final long[] roundBuffer;
    /**
     * The y in 'x requests per y milliseconds'.
     */
    private final long milliseconds;
    /**
     * Underlying lock used for thread synchronization.
     */
    private final ReentrantLock lock;
    /**
     * The current index in the round buffer, starting at 0.
     */
    private int index = 0;

    /**
     * Constructs a new RateLimiter.
     *
     * @param requests the x in 'x requests per y milliseconds'
     * @param milliseconds the y in 'x requests per y milliseconds'
     */
    public RateLimiter(int requests, int milliseconds) {
        if (requests <= 0) {
            throw new IllegalArgumentException("'requests' must be > 0");
        }
        
        if (milliseconds <= 0) {
            throw new IllegalArgumentException("'milliseconds' must be > 0");
        } 
        
        this.roundBuffer = new long[requests];
        this.milliseconds = milliseconds;
        this.lock = new ReentrantLock();
    }

    /**
     * Call this BEFORE executing code that needs to be rate limited. Blocks the
     * thread as long as necessary so that the rate limit isn't violated.
     * @return True if the thread was not interrupted while waiting to continue.
     */
    public boolean Lock() {
        // Throw exception if this is called while we already hold the lock.
        if (lock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Lock is already held by current thread");
        }
        
        // Block until we've obtained the lock.
        lock.lock();

        // Retrieve oldest and current timestamps, calculate difference.
        final long diff = System.currentTimeMillis() - roundBuffer[index];

        // If the difference is less than the y in 'x requests per y milliseconds'
        // then sleep for the duration of the difference.
        if (diff < milliseconds) {
            final long sleepFor = milliseconds - diff;
            LOGGER.log(Level.INFO, "Rate limit reached. Thread put to sleep for "
                    + "{0} milliseconds.", sleepFor);

            try {
                Thread.sleep(sleepFor);
            } catch (InterruptedException ex) {
                // We were interrupted, so unlock to prevent a deadlock, then return false.
                Thread.currentThread().interrupt();
                lock.unlock();
                LOGGER.log(Level.INFO, "Rate limiter was interrupted.");
                return false;
            }
        }
        // We weren't interrupted, so return true.
        return true;
    }

    /**
     * Call this AFTER executing code that needs to be rate limited. Failure to
     * call this will result in other threads being blocked indefinitely.
     */
    public void Unlock() {
        // Throw exception if this is called while we don't hold the lock yet.
        if (!lock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Lock is not being held by current thread");
        }
        
        // Update the oldest timestamp, then increment the index and unlock.
        roundBuffer[index] = System.currentTimeMillis();
        index = ++index % roundBuffer.length;
        lock.unlock();
    }
}
