package miage.skillz.service;

import miage.skillz.entity.Badge;
import miage.skillz.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {
    @Autowired
    private BadgeRepository repository;

    //Create Badge
    public Badge createBadge(Badge badge) {
        return repository.save(badge);
    }

    //Get all badges
    public List<Badge> getAllBadges() {
        return repository.findAll();
    }

    //Delete Badge
    public void deleteBadge(Long badgeId) {
        repository.deleteById(badgeId);
    }


}
