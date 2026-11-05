package mn.english.words_api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RateLimiterTest {

    @Test
    void allowsFiveRequestsPerMinuteFromOneIp() {
        RateLimiter limiter = new RateLimiter();

        for (int i = 0; i < 5; i++) {
            assertTrue(limiter.allow("1.1.1.1"));
        }
        assertFalse(limiter.allow("1.1.1.1"));
    }

    @Test
    void countsEachIpSeparately() {
        RateLimiter limiter = new RateLimiter();

        for (int i = 0; i < 5; i++) {
            limiter.allow("1.1.1.1");
        }

        assertFalse(limiter.allow("1.1.1.1"));
        assertTrue(limiter.allow("2.2.2.2"));
    }

    @Test
    void keepsRejectingTheSameIpButAllowsAnotherIp() {
        RateLimiter limiter = new RateLimiter();

        for (int i = 0; i < 7; i++) {
            limiter.allow("1.1.1.1");
        }

        assertFalse(limiter.allow("1.1.1.1"));
        assertTrue(limiter.allow("2.2.2.2"));
    }

    @Test
    void allowsTheSameIpAgainAfterOneMinute() {
        long[] now = { 0L };
        RateLimiter limiter = new RateLimiter(5, 500, () -> now[0]);

        for (int i = 0; i < 5; i++) {
            limiter.allow("1.1.1.1");
        }
        assertFalse(limiter.allow("1.1.1.1"));

        now[0] = 61_000;

        assertTrue(limiter.allow("1.1.1.1"));
    }

    @Test
    void stopsEveryoneWhenTheDailyLimitIsReached() {
        RateLimiter limiter = new RateLimiter(5, 3, () -> 0L);

        assertTrue(limiter.allow("1.1.1.1"));
        assertTrue(limiter.allow("2.2.2.2"));
        assertTrue(limiter.allow("3.3.3.3"));
        assertFalse(limiter.allow("4.4.4.4"));
    }
}