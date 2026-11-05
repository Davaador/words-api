package mn.english.words_api;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateLimiter {

    private final int perIpPerMinute;
    private final int globalPerDay;
    private final LongSupplier clock;

    private final Map<String, Deque<Long>> hits = new HashMap<>();
    private LocalDate today = LocalDate.now();
    private int todayCount = 0;

    @Autowired
    public RateLimiter() {
        this(5, 500, System::currentTimeMillis);
    }

    public RateLimiter(int perIpPerMinute, int globalPerDay, LongSupplier clock) {
        this.perIpPerMinute = perIpPerMinute;
        this.globalPerDay = globalPerDay;
        this.clock = clock;
    }

    public synchronized boolean allow(String ip) {
        long now = clock.getAsLong();

        LocalDate date = LocalDate.now();
        if (!date.equals(today)) {
            today = date;
            todayCount = 0;
        }
        if (todayCount >= globalPerDay) {
            return false;
        }

        if (hits.size() > 10_000) {
            hits.clear();
        }
        Deque<Long> times = hits.computeIfAbsent(ip, k -> new ArrayDeque<>());
        while (!times.isEmpty() && now - times.peekFirst() > 60_000) {
            times.pollFirst();
        }
        if (times.size() >= perIpPerMinute) {
            return false;
        }

        times.addLast(now);
        todayCount++;
        return true;
    }
}