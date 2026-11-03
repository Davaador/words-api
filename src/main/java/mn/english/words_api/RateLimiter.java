package mn.english.words_api;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RateLimiter {
    private static final int PER_IP_PER_MINUTE = 5;
    private static final int GLOBAL_PER_DAY = 500;
    private final Map<String, Deque<Long>> hits = new HashMap<>();
    private LocalDate today = LocalDate.now();
    private int todayCount = 0;

    public synchronized boolean allow(String ip) {
        long now = System.currentTimeMillis();
        LocalDate date = LocalDate.now();
        if (!date.equals(today)) {
            today = date;
            todayCount = 0;
        }
        if (todayCount >= GLOBAL_PER_DAY) {
            return false;
        }
        if (hits.size() > 10_000) {
            hits.clear();
        }
        Deque<Long> times = hits.computeIfAbsent(ip, k -> new ArrayDeque<>());
        while (!times.isEmpty() && now - times.peekFirst() > 60_000) {
            times.pollFirst();
        }
        if (times.size() >= PER_IP_PER_MINUTE) {
            return false;
        }
        times.addLast(now);
        todayCount++;
        return true;
    }

}
