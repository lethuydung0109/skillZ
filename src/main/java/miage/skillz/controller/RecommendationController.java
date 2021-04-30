package miage.skillz.controller;

import miage.skillz.entity.Recommendation;
import miage.skillz.payload.reponse.MessageResponse;
import miage.skillz.payload.request.BadgeRequest;
import miage.skillz.service.RecommendationService;
import miage.skillz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class RecommendationController {
    final static Logger log = LoggerFactory.getLogger(RecommendationController.class);

    @Autowired
    private RecommendationService service;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/recommendation")
    public ResponseEntity<?> createRecommendation(@Valid @RequestBody Recommendation recommendation){
        service.createRecommendation(recommendation);
        return ResponseEntity.ok(new MessageResponse("Recommendation registered successfully!"));
    }


}
