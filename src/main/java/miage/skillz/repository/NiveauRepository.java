package miage.skillz.repository;


import miage.skillz.entity.Niveau;
import miage.skillz.enumeration.ENiveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
    Optional<Niveau> findByNiveau(ENiveau niveau);
}
