package miage.skillz.controller;

import miage.skillz.entity.*;
import miage.skillz.enumeration.ENiveau;
import miage.skillz.payload.reponse.MessageResponse;
import miage.skillz.payload.request.BadgeRequest;
import miage.skillz.security.services.UserDetailsImpl;
import miage.skillz.service.BadgeService;
import miage.skillz.service.CompetenceService;


import miage.skillz.service.NiveauService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class BadgeController {
    final static Logger log = LoggerFactory.getLogger(BadgeController.class);

    @Autowired
    private BadgeService service;

    @Autowired
    private UserController userController;

    //Get all badges
    @GetMapping(value = "/allBadges", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Badge> getAllBadges() { return service.getAllBadges(); }

    @GetMapping(value = "/allCurrentUserBadges", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Badge> getCurrentUserBadges() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return service.getCurrentUserBadges(currentUser.getId());
    }

    @GetMapping(value = "/userBadges/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Badge> getAllBadgeByUserId(@PathVariable Long userId) {
        return service.getCurrentUserBadges(userId);
    }

    @GetMapping(value = "/userBadgesStat/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getNumberOfBadgeByUserId(@PathVariable Long userId) {
        Set<Badge> badges = service.getCurrentUserBadges(userId);
        int nbBadges = badges.size();
        return nbBadges;
    }

    //Create Badge
    @PostMapping(value = "/badge")
    public ResponseEntity<?> createBadge(@RequestBody BadgeRequest badgeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return service.createBadge(badgeRequest, currentUser);
    }

    //Delete Badge
    @DeleteMapping(value = "/badge/{badgeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBadge(@PathVariable Long BadgeId) { service.deleteBadge(BadgeId); }

}
