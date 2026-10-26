package mn.english.words_api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  WordRepository extends JpaRepository<Word, Long> {
    
}
