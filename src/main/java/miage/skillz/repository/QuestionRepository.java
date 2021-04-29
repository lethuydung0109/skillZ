package miage.skillz.repository;

import miage.skillz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value="SELECT q FROM Question q WHERE q.niveau = :idNiveau  ")
    Set<Question> getQuestionByCompetenceNiveau(
           // @Param("idCompetence") Long idCompetence,
            @Param("idNiveau") String idNiveau);
}
