package mn.english.words_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class WordController {

    private final WordRepository repository;

    @Value("${ADMIN_KEY:}")
    private String adminKey;


    public WordController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/words")
    public List<Word> getWords(@RequestParam(required = false) String category) {
        if(category == null){
            return repository.findAll();
        }
        return  repository.findByCategory(category);
    }

    @PostMapping("/api/words")
    public Word add(@RequestHeader("X-Admin-Key") String key, @RequestBody NewWord body) {
        if(adminKey.isBlank() || !adminKey.equals(key)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if(body.en() == null || body.en().isBlank() || body.mn() == null || body.mn().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Word word = new Word(body.en(), body.mn(), body.category());
        word.setLevel(body.level() == null ? "easy" : body.level());
        return repository.save(word);
    }
}