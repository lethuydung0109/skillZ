package miage.skillz.repository;

import miage.skillz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Optional<Quiz> findByName(String name);
    Optional<Boolean> existsByName(String name);
}
