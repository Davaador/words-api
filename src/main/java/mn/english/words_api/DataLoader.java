package mn.english.words_api;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component 
public class DataLoader implements CommandLineRunner {
    private final WordRepository repository;
    public DataLoader(WordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            add("web", "tag", "шошго", "page", "хуудас", "button", "товч",
               "file", "файл", "browser", "хөтөч");
            add("css", "style", "загвар", "color", "өнгө", "margin", "гаднах зай",
                    "padding", "доторх зай", "selector", "сонгогч");
            add("javascript", "script", "скрипт", "array", "массив", "loop", "давталт",
                    "return", "буцаах", "console", "консол");
            add("dom", "element", "элемент", "event", "үйл явдал", "click", "дарах",
                    "object", "объект", "index", "индекс");
            add("debugging", "null", "хоосон", "error", "алдаа", "property", "шинж чанар",
                    "missing", "дутуу", "typo", "үсгийн алдаа");
            add("logic", "previous", "өмнөх", "limit", "хязгаар", "skip", "алгасах",
                    "condition", "нөхцөл", "loop around", "эргэлдэх");
            add("app", "answer", "хариулт", "question", "асуулт", "correct", "зөв",
                    "wrong", "буруу", "score", "оноо");
            add("code", "state", "төлөв", "guard", "хамгаалалт", "dead code", "ашиглагдаагүй код",
                    "clean", "цэвэр", "duplicate", "давхардал");
            add("devops", "deploy", "байршуулах", "commit", "хадгалалт", "repository", "хадгалах сан",
                    "install", "суулгах", "offline", "интернэтгүй");
        }
        for (Word w: repository.findAll()) {
            if(w.getLevel() == null) {
                w.setLevel(levelFor(w.getCategory()));
                repository.save(w);
            }

            
        }
    }

    private String levelFor(String category) {
        if(category == null)
            return "easy";
        return switch(category) {
            case "web", "css", "javascript" -> "easy";
            case "dom", "debugging", "logic", "app" -> "medium";
            case "code", "devops" -> "hard";
            default -> "easy";
        };
    }

    private void add(String category, String... pairs) {
        for (int i = 0; i < pairs.length; i += 2) {
            repository.save(new Word(pairs[i], pairs[i + 1], category));
        }
    }
}
