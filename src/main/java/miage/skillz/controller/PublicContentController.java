package miage.skillz.controller;


import miage.skillz.entity.Competence;
import miage.skillz.entity.PublicContent;
import miage.skillz.entity.Recommendation;
import miage.skillz.payload.reponse.MessageResponse;
import miage.skillz.payload.reponse.UserResponse;
import miage.skillz.payload.request.RecommendationRequest;
import miage.skillz.service.PublicContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class PublicContentController{
    final static Logger log = LoggerFactory.getLogger(PublicContentController.class);

    @Autowired
    private PublicContentService service;

    @GetMapping(value = "/publicContent",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  getLatestPublicContent()
    {
        List<PublicContent> publicContents = service.getPublicContentList();
        String latestPublicContent = publicContents.get(publicContents.size()-1).getContent();
        log.info(latestPublicContent);
        return new ResponseEntity <String> (latestPublicContent, HttpStatus.OK);
    }

    @PostMapping(value = "/publicContent",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  updatePublicContent(@RequestBody PublicContent content)
    {
        String newContent = content.getContent();
        String newDate = content.getDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        PublicContent newPublicContent = new PublicContent(newContent, newDate);
        service.savePublicContent(newPublicContent);
        log.info(newDate);
        return ResponseEntity.ok(new MessageResponse("Content updated successfully!"));
    }
}
