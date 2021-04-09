package miage.skillz.controller;

import miage.skillz.entity.Question;
import miage.skillz.security.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/createQuestion",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> createQuestion(@RequestBody @Valid Question question)
    {
        return  questionService.createQuestion(question);
    }

    @PutMapping(value = "/updateQuestion", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody @Valid Question question)
    {
        return  questionService.updateQuestion(question);
    }

    @GetMapping(value = "/getQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(Question question)
    {
        return  questionService.getQuestion(question);
    }

    @GetMapping(value = "/allQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Question> getAllQuestions()
    {
        return  questionService.getAllQuestions();
    }

    @DeleteMapping(value = "/deleteQuestion/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestion(Long qId)
    {
        questionService.deleteQuestion(qId);
    }

    @DeleteMapping(value = "/deleteAllQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllQuestions()
    {
        questionService.deleteAllQuestions();
    }
}
