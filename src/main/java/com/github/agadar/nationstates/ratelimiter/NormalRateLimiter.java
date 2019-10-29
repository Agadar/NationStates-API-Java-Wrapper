package com.github.agadar.nationstates.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agadar (https://github.com/Agadar/)
 */
@Slf4j
public class NormalRateLimiter implements RateLimiter {

    /**
     * The round buffer we're using, with length set to x in x requests per y
     * milliseconds.
     */
    private final long[] roundBuffer;
    /**
     * The y in x requests per y milliseconds.
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
     * @param requests the x in x requests per y milliseconds
     * @param milliseconds the y in x requests per y milliseconds
     */
    public NormalRateLimiter(int requests, int milliseconds) {
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

    @Override
    public boolean lock() {
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

            try {
                Thread.sleep(sleepFor);
            } catch (InterruptedException ex) {
                // We were interrupted, so unlock to prevent a deadlock, then return false.
                log.error("The sleeping thread was interrupted", ex);
                Thread.currentThread().interrupt();
                lock.unlock();
                return false;
            }
        }
        // We weren't interrupted, so return true.
        return true;
    }

    @Override
    public void unlock() {
        // Throw exception if this is called while we don't hold the lock yet.
        if (!lock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Lock is not being held by current thread");
        }

        // Update the oldest timestamp, then increment the index and unlock.
        roundBuffer[index] = System.currentTimeMillis();
        index = ++index % roundBuffer.length;
        lock.unlock();
    }

    @Override
    public int getMillisecondsBetweenLocks() {
        return Math.round(milliseconds / roundBuffer.length);
    }
}
