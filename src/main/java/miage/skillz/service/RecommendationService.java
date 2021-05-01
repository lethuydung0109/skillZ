package miage.skillz.service;

import miage.skillz.entity.Recommendation;
import miage.skillz.repository.BadgeRepository;
import miage.skillz.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository repository;

    public Recommendation createRecommendation(Recommendation recommendation) {
        return repository.save(recommendation);
    }
    public Set<Recommendation> getRecommendationByReceiverId (Long receiverId){
        return repository.findByReceiverId(receiverId);
     }
//    @Autowired
//    private RecommendationRepository repository;
//
//    public Recommendation findRecommendationById(Long recommendationId) {
//        repository.findById(recommendationId);
//    }
}
