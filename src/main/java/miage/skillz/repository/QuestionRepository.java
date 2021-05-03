package miage.skillz.repository;

import miage.skillz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value="SELECT q FROM Question q WHERE q.niveau.niveauId = :idNiveau  ")
    Set<Question> getQuestionByCompetenceNiveau(
           // @Param("idCompetence") Long idCompetence,
            @Param("idNiveau") Long idNiveau);

    @Query(value="SELECT q FROM Question q WHERE q.idQuestion in :qstId")
    Set<Question> findByInventoryIdIn( @Param("qstId") Long[] qstId);
}
