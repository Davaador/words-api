package mn.english.words_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String en;
    private String mn;

    protected Word() {}

    public Word(String en, String mn) {
        this.en = en;
        this.mn = mn;
    }

    public Long getId() { return id; }
    public String getEn() { return en; }
    public String getMn() { return mn; }
}