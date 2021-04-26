package miage.skillz.controller;

import miage.skillz.entity.ReponseQuestion;
import miage.skillz.service.ReponseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class ReponseQuestionController {

    @Autowired
    private ReponseQuestionService reponseQuestionService;

    @PostMapping(value = "/createRepQuestion",consumes = "application/json",produces = "application/json")
    public ResponseEntity<ReponseQuestion> createReponseQuestion(@RequestBody @Valid ReponseQuestion repQuestion)
    {
        return  reponseQuestionService.createReponseQuestion(repQuestion);
    }

    @PutMapping(value = "/updateRepQuestion", consumes = "application/json",produces = "application/json")
    public ResponseEntity<ReponseQuestion> updateReponseQuestion(@RequestBody @Valid ReponseQuestion repQuestion)
    {
        return  reponseQuestionService.updateReponseQuestion(repQuestion);
    }

    @GetMapping(value = "/getRepQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReponseQuestion> getReponseQuestion(ReponseQuestion repQuestion)
    {
        return  reponseQuestionService.getReponseQuestion(repQuestion);
    }

    @GetMapping(value = "/allRepQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ReponseQuestion> getAllReponseQuestion()
    {
        return  reponseQuestionService.getAllReponseQuestions();
    }

    @DeleteMapping(value = "/deleteReponseQuestion/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReponseQuestion(Long qId)
    {
        reponseQuestionService.deleteReponseQuestion(qId);
    }

    @DeleteMapping(value = "/deleteAllRepQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllRepQuestion()
    {
        reponseQuestionService.deleteAllRepQuestion();
    }
}
