package miage.skillz.controller;

import miage.skillz.entity.Question;
import miage.skillz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/createQuestion",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question)
    {
        return  questionService.createQuestion(question);
    }

    @PutMapping(value = "/updateQuestion", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
    {
        return  questionService.updateQuestion(question);
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
}
