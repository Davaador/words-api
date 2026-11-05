package mn.english.words_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

class ClaudeServiceTest {

    @Test
    void failsWith503WhenApiKeyIsMissing() {
        ClaudeService service = new ClaudeService("");

        ResponseStatusException error = assertThrows(
                ResponseStatusException.class,
                () -> service.check("Hello."));

        assertEquals(503, error.getStatusCode().value());
    }
}