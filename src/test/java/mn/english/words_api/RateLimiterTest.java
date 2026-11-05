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
}