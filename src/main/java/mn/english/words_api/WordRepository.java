package mn.english.words_api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByCategory(String category);
    
}
