package miage.skillz.service;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Badge;
import miage.skillz.entity.Poste;
import miage.skillz.entity.User;
import miage.skillz.models.PosteImpl;
import miage.skillz.repository.BadgeRepository;
import miage.skillz.repository.CompetenceRepository;
import miage.skillz.repository.NiveauRepository;
import miage.skillz.repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PosteService {

    @Autowired
    PosteRepository posteRepository;
    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private BadgeRepository badgeRepository;

    public ResponseEntity<Poste> createPoste(PosteImpl posteImpl)
    {
        Poste poste = new Poste(posteImpl.getName(),
                                this.competenceRepository.findById(posteImpl.getCompetenceId()).orElseThrow(),
                                this.niveauRepository.findById(posteImpl.getNiveauId()).orElseThrow(),
                                posteImpl.getScoreMin());

        return new ResponseEntity<>(this.posteRepository.saveAndFlush(poste), HttpStatus.OK);
    }

    public ResponseEntity<Poste> updatePoste(PosteImpl posteImpl)
    {
        Poste poste = this.posteRepository.findById(posteImpl.getPosteId()).orElseThrow();
        poste.setName(posteImpl.getName());
        poste.setCompetence(this.competenceRepository.findById(posteImpl.getCompetenceId()).orElseThrow());
        poste.setNiveau(this.niveauRepository.findById(posteImpl.getNiveauId()).orElseThrow());
        poste.setScoreMin(posteImpl.getScoreMin());

        return new ResponseEntity<>(this.posteRepository.saveAndFlush(poste), HttpStatus.OK);
    }

    public Set<User> getCandidatesForThePost(Long posteId)
    {
        Poste poste = this.posteRepository.findById(posteId).orElseThrow();
        List<Badge> badges=this.badgeRepository
                                .findByCompetenceAndNiveauAndQuizScoreGreaterThanEqual(
                                        poste.getCompetence(),
                                        poste.getNiveau(),
                                        poste.getScoreMin());

        //System.out.println("badges"+badges.toString());

        Set<User> candidates = new HashSet<>();
        badges.forEach(badge -> {
            candidates.add(badge.getUser());
        });
        //System.out.println("candidates : "+candidates.toString());

        return candidates;
    }

    public List<Poste> getAllPostes()
    {
        return this.posteRepository.findAll();
    }

    public void deletePoste(Long posteId) {
        this.posteRepository.deleteById(posteId);
    }
}
