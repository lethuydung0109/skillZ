package miage.skillz.service;

import miage.skillz.entity.Badge;
import miage.skillz.entity.Competence;
import miage.skillz.entity.Niveau;
import miage.skillz.entity.User;
import miage.skillz.payload.request.BadgeRequest;
import miage.skillz.repository.BadgeRepository;
import miage.skillz.repository.CompetenceRepository;
import miage.skillz.repository.NiveauRepository;
import miage.skillz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BadgeService {
    @Autowired
    private BadgeRepository repository;
    @Autowired
    private CompetenceService competenceService;
    @Autowired
    private NiveauService niveauService;
    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Badge> createBadge(BadgeRequest badgeRequest,User currentUser) {

        Competence competence = competenceRepository.findById(badgeRequest.getCompetenceId()).orElseThrow();
        Niveau niveau = niveauRepository.findById(badgeRequest.getNiveauId()).orElseThrow();

        List<Badge> cloneBadge = this.findBadgeWithSameCompetence(currentUser.getId(),competence.getId());

        if (!cloneBadge.isEmpty()) {
            Badge newBadge = cloneBadge.get(0);
            newBadge.setNiveau(niveau);
            newBadge.setDateValidation(badgeRequest.getDateValidation());
            newBadge.setQuizScore(badgeRequest.getQuizScore());
            return new ResponseEntity<>(repository.saveAndFlush(newBadge), HttpStatus.OK);
        } else {
            Badge badge = new Badge();
            badge.setCompetence(competence);
            badge.setNiveau(niveau);
            badge.setDateValidation(badgeRequest.getDateValidation());
            badge.setQuizScore(badgeRequest.getQuizScore());
            Badge createdBadge = repository.save(badge);

            createdBadge.setUser(currentUser);
            currentUser.getBadges().add(createdBadge);

            return new ResponseEntity<>(repository.saveAndFlush(createdBadge), HttpStatus.OK);
        }
    }

    //Get all badges
    public List<Badge> getAllBadges() {
        return repository.findAll();
    }

    //Delete Badge
    public void deleteBadge(Long badgeId) {
        repository.deleteById(badgeId);
    }

    public Set<Badge> getCurrentUserBadges(Long userId) {
        return badgeRepository.findAll()
                .stream()
                .filter(badge -> badge.getUser().equals(userRepository.findById(userId).orElseThrow()))
                .collect(Collectors.toSet());
    }

    public List<Badge> findBadgeWithSameCompetence(Long userId,Long competenceId)
    {
        return this.getCurrentUserBadges(userId).stream().filter(badge ->
            badge.getCompetence().getId() == competenceId
        ).collect(Collectors.toList());
    }
}
