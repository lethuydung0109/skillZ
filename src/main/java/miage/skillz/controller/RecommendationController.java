package miage.skillz.controller;

import miage.skillz.entity.Badge;
import miage.skillz.entity.Competence;
import miage.skillz.entity.Recommendation;
import miage.skillz.entity.User;
import miage.skillz.payload.reponse.MessageResponse;
import miage.skillz.payload.reponse.RecommendationResponse;
import miage.skillz.payload.request.BadgeRequest;
import miage.skillz.payload.request.RecommendationRequest;
import miage.skillz.service.RecommendationService;
import miage.skillz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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
    public ResponseEntity<?> createRecommendation(@Valid @RequestBody RecommendationRequest recommendationRequest) throws ParseException {

        User writer = userService.findById(recommendationRequest.getWriterId());
        User receiver = userService.findById(recommendationRequest.getReceiverId());
        log.info("writer =" + writer.getUsername());
        log.info("receiver = " + receiver.getUsername());
        String content = recommendationRequest.getContent();
        String date = recommendationRequest.getDate();
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Recommendation recommendation = service.createRecommendation(new Recommendation(writer, receiver, content, format.parse(date)));
        RecommendationRequest returnRecommendation = new RecommendationRequest(
                recommendation.getWriter().getId(),
                recommendation.getReceiver().getId(),
                recommendation.getContent(),
                recommendation.getDate().toString()
        );
        log.info("recommendation = " + recommendation.getContent());
//        return ResponseEntity.ok(new MessageResponse("Recommendation registered successfully!"));
        return new ResponseEntity<RecommendationRequest>(returnRecommendation, HttpStatus.OK);
    }

    @GetMapping(value = "/recommendation/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecommendationByReceiverId(@PathVariable ("receiverId") Long receiverId ){
        Set <Recommendation> recommendations = service.getRecommendationByReceiverId(receiverId);
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        Set<RecommendationResponse> listRecommendationResponse = new HashSet<>();

        for(Recommendation rec: recommendations){
            listRecommendationResponse.add(new RecommendationResponse(
                    rec.getId(),
                    rec.getWriter().getId(),
                    rec.getWriter().getUsername(),
                    rec.getReceiver().getId(),
                    rec.getReceiver().getUsername(),
                    rec.getContent(),
                    dateFormat.format(rec.getDate())
            ));
        }

        return new  ResponseEntity <Set <RecommendationResponse> >(listRecommendationResponse, HttpStatus.OK);
    }

}
