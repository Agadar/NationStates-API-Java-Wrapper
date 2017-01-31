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
    protected static final Logger logger = Logger.getLogger(RateLimiter.class.getName());
    /**
     * The round buffer we're using, with length set to x in 'x requests per y
     * milliseconds.
     */
    protected final long[] roundBuffer;
    /**
     * The y in 'x requests per y milliseconds'.
     */
    protected final long milliseconds;
    /**
     * Underlying lock used for thread synchronization.
     */
    protected final ReentrantLock lock;
    /**
     * The current index in the round buffer, starting at 0.
     */
    protected int index = 0;

    /**
     * Constructs a new RateLimiter.
     *
     * @param requests the x in 'x requests per y milliseconds'
     * @param milliseconds the y in 'x requests per y milliseconds'
     */
    public RateLimiter(int requests, int milliseconds) {
        if (requests <= 0) {
            throw new IllegalArgumentException("'requests' should be > 0!");
        }

        if (milliseconds <= 0) {
            throw new IllegalArgumentException("'milliseconds' should be > 0!");
        }

        this.roundBuffer = new long[requests];
        this.milliseconds = milliseconds;
        this.lock = new ReentrantLock();
    }

    /**
     * Call this BEFORE executing code that needs to be rate limited. Blocks the
     * thread as long as necessary so that the rate limit isn't violated.
     */
    public void Lock() {
        assert !lock.isHeldByCurrentThread();
        lock.lock();

        // Retrieve oldest and current timestamps, calculate difference.
        final long diff = System.currentTimeMillis() - roundBuffer[index];

        // If the difference is less than the y in 'x requests per y milliseconds'
        // then print a warning and sleep for the duration of the difference.
        if (diff < milliseconds) {
            final long sleepFor = milliseconds - diff;
            logger.log(Level.INFO, "Rate limit reached. Thread put to sleep for "
                    + "{0} milliseconds.", sleepFor);

            try {
                Thread.sleep(sleepFor);
            } catch (InterruptedException ex) {
                lock.unlock();
                throw new RuntimeException("RateLimiter.class blew up!", ex);
            }
        }
    }

    /**
     * Call this AFTER executing code that needs to be rate limited. Failure to
     * call this will result in other threads being blocked indefinitely.
     */
    public void Unlock() {
        // Finally, update the oldest timestamp and increment the index.
        roundBuffer[index] = System.currentTimeMillis();
        index = ++index % roundBuffer.length;

        assert lock.isHeldByCurrentThread();
        lock.unlock();
    }
}
