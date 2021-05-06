package miage.skillz.repository;

import miage.skillz.entity.Badge;
import miage.skillz.entity.PublicContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicContentRepository extends JpaRepository<PublicContent, Long> {
}
