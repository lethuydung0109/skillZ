package miage.skillz.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quiz;
import miage.skillz.entity.User;
import miage.skillz.models.QuizImpl;
import miage.skillz.security.services.UserDetailsImpl;
import miage.skillz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private UserController userController;

    @PostMapping(value = "/createQuiz", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create Quiz")
    @ApiResponse(code=200, message = "Quiz created",response= MultipartFile.class)
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizImpl quizImpl)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return  quizService.createQuiz(quizImpl,currentUser);
    }

    @PutMapping(value = "/updateQuiz",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody QuizImpl quizImpl)
    {
        return  quizService.updateQuiz(quizImpl);
    }

    @GetMapping(value = "/getQuiz/{quizId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId)
    {
        return  quizService.getQuiz(quizId);
    }

    @GetMapping(value = "/allQuiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Quiz> getAllQuiz()
    {
        return  quizService.getAllQuiz();
    }

    @GetMapping(value = "/user/quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Quiz> getAllQuizByUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());

        return quizService.getAllQuizByUser(currentUser.getId());
    }

    @GetMapping(value = "/getQuizQuestions/{quizId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Question> getQuizQuestions (@PathVariable Long quizId)
    {
        return  quizService.getQuizQuestions(quizId);
    }

    @GetMapping(value = "/addQuestionToQuiz/{quizId}/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addQuestionToQuiz(@PathVariable Long quizId, @PathVariable Long questionId)
    {
        return  quizService.addQuestionToQuiz(quizId,questionId);
    }

    @DeleteMapping(value = "/deleteQuestionFromQuiz/{quizId}/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestionFromQuiz(@PathVariable Long quizId, @PathVariable Long qId)
    {
        quizService.deleteQuestionFromQuiz(quizId, qId);
    }

    @DeleteMapping(value = "/deleteQuizById/{quizId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuiz(@PathVariable Long quizId)
    {
        quizService.deleteQuiz(quizId);
    }

    @DeleteMapping(value = "/deleteAllQuiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllQuiz()
    {
        quizService.deleteAllQuiz();
    }
}
