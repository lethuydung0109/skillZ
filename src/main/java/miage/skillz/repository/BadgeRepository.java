package miage.skillz.repository;

import miage.skillz.entity.Badge;
import miage.skillz.entity.Competence;
import miage.skillz.entity.Niveau;
import miage.skillz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findByCompetenceAndNiveau(Competence competence, Niveau niveau);
    List<Badge> findByQuizScoreGreaterThanEqual(Long scoreMin);
    List<Badge> findByCompetenceAndNiveauAndQuizScoreGreaterThanEqual(Competence competence, Niveau niveau,Long scoreMin);
}
