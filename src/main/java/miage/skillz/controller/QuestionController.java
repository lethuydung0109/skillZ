package miage.skillz.controller;

import miage.skillz.entity.Question;
import miage.skillz.entity.ReponseQuestion;
import miage.skillz.models.QuestionImpl;
import miage.skillz.security.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/createQuestion",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionImpl questionImpl)
    {
        return  questionService.createQuestion(questionImpl);
    }

    @PutMapping(value = "/updateQuestion", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody QuestionImpl questionImpl)
    {
        return  questionService.updateQuestion(questionImpl);
    }

    @GetMapping(value = "/getQuestion/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Question getQuestion(@PathVariable Long qId)
    {
        return  questionService.getQuestion(qId);
    }

    @GetMapping(value = "/allQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Question> getAllQuestions()
    {
        return  questionService.getAllQuestions();
    }

    @DeleteMapping(value = "/deleteQuestion/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestion(@PathVariable Long qId)
    {
        questionService.deleteQuestion(qId);
    }

    @DeleteMapping(value = "/deleteAllQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllQuestions()
    {
        questionService.deleteAllQuestions();
    }

    @GetMapping(value = "/correctResponse/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ReponseQuestion> getAllCorrectQuestionResponse(@PathVariable Long qId)
    {
        return questionService.getAllCorrectQuestionResponse(qId);
    }

    @GetMapping(value = "/getQuestionPoids/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getQuestionPoids(@PathVariable Long qId)
    {
        return questionService.getQuestionPoids(qId);
    }
}
