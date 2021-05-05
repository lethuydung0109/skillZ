package miage.skillz.repository;

import miage.skillz.entity.Competence;
import miage.skillz.entity.Niveau;
import miage.skillz.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {

    Poste findPosteByCompetenceAndNiveau(Competence competence, Niveau niveau);
}