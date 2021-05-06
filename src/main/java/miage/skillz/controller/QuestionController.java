package miage.skillz.controller;

import miage.skillz.entity.Question;
import miage.skillz.entity.ReponseQuestion;
import miage.skillz.entity.User;
import miage.skillz.models.QuestionImpl;
import miage.skillz.security.services.UserDetailsImpl;
import miage.skillz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserController userController;

    @PostMapping(value = "/createQuestion",consumes = "application/json",produces = "application/json")
    public Question createQuestion(@RequestBody QuestionImpl questionImpl)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return  questionService.createQuestion(questionImpl,currentUser);
    }

    @PostMapping(value = "/createManyQuestion",consumes = "application/json",produces = "application/json")
    public Set<Question> createListQuestions(@RequestBody Set<QuestionImpl> listQuestions) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return  questionService.createListQuestions(listQuestions,currentUser);
    }

    @PutMapping(value = "/updateQuestion", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Question> updateQuestion(@RequestBody QuestionImpl questionImpl)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return  questionService.updateQuestion(questionImpl,currentUser);
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

    @GetMapping(value = "/numberOfQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public  int getNbQuestions()
    {
        List<Question> questions = questionService.getAllQuestions();
        return questions.size();
    }

    @GetMapping(value = "/lesQuestionById/{qstId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Question> getLesQuestionById(@PathVariable Long[] qstId)
    {
        return  questionService.getLesQuestionById(qstId);
    }

    @GetMapping(value = "/user/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Question> getAllQuizByUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());

        return questionService.getAllQuestionsByUser(currentUser.getId());
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

    @GetMapping(value = "/questionReponses/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ReponseQuestion> getAllQuestionResponse(@PathVariable Long qId)
    {
        return questionService.getAllQuestionResponse(qId);
    }


    @GetMapping(value = "/getQuestionCompetenceNiveau/{idNiveau}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Question> getQuestionByCompetenceNiveau(@PathVariable Long idNiveau)
    {
        return questionService.getQuestionByCompetenceNiveau(/*idCompetence,*/idNiveau);
    }


}
