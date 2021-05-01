package miage.skillz.repository;


import miage.skillz.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Set<Recommendation> findByReceiverId(Long receiverId);
}
