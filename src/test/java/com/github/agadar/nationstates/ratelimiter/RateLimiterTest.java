package com.github.agadar.nationstates.ratelimiter;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Agadar (https://github.com/Agadar/)
 */
public class RateLimiterTest {

    private RateLimiter rateLimiter;

    @After
    public void tearDown() {
        rateLimiter = null;
    }

    /**
     * Test of lock and unlock methods, of class RateLimiter.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testLockAndUnlock() throws InterruptedException {
        System.out.println("lock unlock");

        // Arrange
        rateLimiter = new RateLimiter(1, 1000);
        final TestRunnable foo = new TestRunnable(rateLimiter);
        final TestRunnable bar = new TestRunnable(rateLimiter);
        final Thread fooThread = new Thread(foo);
        final Thread barThread = new Thread(bar);

        // Act & Assert
        assertFalse(foo.called);
        assertFalse(bar.called);

        fooThread.start();
        barThread.start();

        Thread.sleep(10);
        assertTrue(foo.called);
        assertFalse(bar.called);

        Thread.sleep(1010);

        assertTrue(bar.called);
    }

    /**
     * Test of getMillisecondsBetweenLocks method, of class RateLimiter.
     */
    @Test
    public void testGetMillisecondsBetweenLocks() {
        System.out.println("getMillisecondsBetweenLocks");

        // Arrange
        rateLimiter = new RateLimiter(5, 250);
        final int expected = 50;

        // Act
        final int actual = rateLimiter.getMillisecondsBetweenLocks();

        // Assert
        assertEquals(expected, actual);
    }

    private class TestRunnable implements Runnable {

        public boolean called = false;
        private final RateLimiter rateLimiter;

        TestRunnable(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            rateLimiter.lock();
            called = true;
            rateLimiter.unlock();
        }
    }
}
