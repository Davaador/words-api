package mn.english.words_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class CheckController {
    private final ClaudeService claude;
    private final RateLimiter limiter;
    public CheckController(ClaudeService claude, RateLimiter limiter) {
        this.claude = claude;
        this.limiter = limiter;
    }

    @PostMapping("/api/check")
    public CheckResponse check(@RequestBody CheckRequest request, HttpServletRequest http) {
        String sentence = request.sentence();
        if (sentence == null || sentence.isBlank() || sentence.length() > 300) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!limiter.allow(http.getRemoteAddr())) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        }
        return new CheckResponse(claude.check(sentence.trim()));
    }
    
}
