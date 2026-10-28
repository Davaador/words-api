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
    private String category;
    private String level;

    protected Word() {}

    public Word(String en, String mn, String category) {
        this.en = en;
        this.mn = mn;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getEn() { return en; }
    public String getMn() { return mn; }
    public String getCategory() {return  category;}
    public String getLevel() { return level; };
    public void setLevel(String level) {
        this.level = level;
    }

}