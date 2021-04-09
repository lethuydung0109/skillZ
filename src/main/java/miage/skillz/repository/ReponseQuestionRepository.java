package miage.skillz.repository;

import miage.skillz.entity.ReponseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseQuestionRepository extends JpaRepository<ReponseQuestion,Long> {
}
