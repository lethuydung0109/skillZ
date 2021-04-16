package miage.skillz.controller;

import miage.skillz.entity.*;
import miage.skillz.enumeration.ENiveau;
import miage.skillz.payload.request.BadgeRequest;
import miage.skillz.service.BadgeService;
import miage.skillz.service.CompetenceService;


import miage.skillz.service.NiveauService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class BadgeController {
    final static Logger log = LoggerFactory.getLogger(BadgeController.class);

    @Autowired
    private BadgeService service;

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private NiveauService niveauService;


    //Get all badges
    @GetMapping(value = "/badge", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Badge> getAllBadges() { return service.getAllBadges(); }

    //Create Badge
    @PostMapping(value = "/badge")
    public void createBadge(@Valid @RequestBody BadgeRequest badgeRequest) {
        Competence competence = competenceService.getCompetenceById(Long.parseLong(badgeRequest.getCompetenceId()));
        String niveau = badgeRequest.getNiveauName();
        Niveau badgeNiveau  = new Niveau();
        switch (niveau) {
            case "Debutant":
                badgeNiveau = niveauService.findByName(ENiveau.NIVEAU1)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


                break;
            case "PreIntermediaire":
                badgeNiveau = niveauService.findByName(ENiveau.NIVEAU2)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                break;
            case "Intermediaire":
                badgeNiveau = niveauService.findByName(ENiveau.NIVEAU3)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                break;
            default:
                badgeNiveau = niveauService.findByName(ENiveau.NIVEAU4)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        }

        Badge badge = new Badge();
        badge.setCompetence(competence);
        badge.setNiveau(badgeNiveau);
        service.createBadge(badge);
    }

    //Delete Badge
    @DeleteMapping(value = "/badge/{badgeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBadge(@PathVariable Long BadgeId) { service.deleteBadge(BadgeId); }

}
