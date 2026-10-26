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
            repository.saveAll(List.of(
                new Word("tag", "шошго"),
                new Word("page", "хуудас"),
                new Word("button", "товч"),
                new Word("file", "файл"),
                new Word("browser", "хөтөч"),
                new Word("style", "загвар"),
                new Word("color", "өнгө"),
                new Word("margin", "гаднах зай"),
                new Word("padding", "доторх зай"),
                new Word("selector", "сонгогч"),
                new Word("script", "скрипт"),
                new Word("array", "массив"),
                new Word("loop", "давталт"),
                new Word("return", "буцаах"),
                new Word("console", "консол"),
                new Word("element", "элемент"),
                new Word("event", "үйл явдал"),
                new Word("click", "дарах"),
                new Word("object", "объект"),
                new Word("index", "индекс"),
                new Word("null", "хоосон"),
                new Word("error", "алдаа"),
                new Word("property", "шинж чанар"),
                new Word("missing", "дутуу"),
                new Word("typo", "үсгийн алдаа"),
                new Word("previous", "өмнөх"),
                new Word("limit", "хязгаар"),
                new Word("skip", "алгасах"),
                new Word("condition", "нөхцөл"),
                new Word("loop around", "эргэлдэх"),
                new Word("answer", "хариулт"),
                new Word("question", "асуулт"),
                new Word("correct", "зөв"),
                new Word("wrong", "буруу"),
                new Word("score", "оноо"),
                new Word("state", "төлөв"),
                new Word("guard", "хамгаалалт"),
                new Word("dead code", "ашиглагдаагүй код"),
                new Word("clean", "цэвэр"),
                new Word("duplicate", "давхардал"),
                new Word("deploy", "байршуулах"),
                new Word("commit", "хадгалалт"),
                new Word("repository", "хадгалах сан"),
                new Word("install", "суулгах"),
                new Word("offline", "интернэтгүй")
            ));
        }
    }

}
