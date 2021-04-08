package miage.skillz.controller;

import miage.skillz.entity.Question;
import miage.skillz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/all")
    @RequestMapping(value = "/createSession", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> createQuestion(@RequestBody @Valid Question question)
    {
        return  questionService.createQuestion(question);
    }

    @PutMapping("/all")
    @RequestMapping(value = "/updateSession", method = RequestMethod.PUT,consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody @Valid Question question)
    {
        return  questionService.updateQuestion(question);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/allSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(Question question)
    {
        return  questionService.getQuestion(question);
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteSession/{qId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestion(Long qId)
    {
        questionService.deleteQuestion(qId);
    }
}
