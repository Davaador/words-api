package mn.english.words_api;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WordController {

    private final WordRepository repository;
    public WordController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/words")
    public List<Word> getWords() {
        return repository.findAll();
    }
}