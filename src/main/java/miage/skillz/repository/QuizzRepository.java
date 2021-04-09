package miage.skillz.repository;

import miage.skillz.entity.Question;
import miage.skillz.entity.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz,Long> {
    Optional<Quizz> findByName(String name);
    Optional<Boolean> existsByName(String name);
}
