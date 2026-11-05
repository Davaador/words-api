package mn.english.words_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class CheckControllerTest {

    private final CheckController controller =
            new CheckController(new ClaudeService(""), new RateLimiter());

    @Test
    void rejectsBlankSentence() {
        ResponseStatusException error = assertThrows(
                ResponseStatusException.class,
                () -> controller.check(new CheckRequest("   "), null));

        assertEquals(400, error.getStatusCode().value());
    }

    @Test
    void rejectsSentenceLongerThan300Characters() {
        String tooLong = "a".repeat(301);

        ResponseStatusException error = assertThrows(
                ResponseStatusException.class,
                () -> controller.check(new CheckRequest(tooLong), null));

        assertEquals(400, error.getStatusCode().value());
    }
}
