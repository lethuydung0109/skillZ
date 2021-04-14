package miage.skillz.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quiz;
import miage.skillz.security.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping(value = "/createQuiz", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create Quiz")
    @ApiResponse(code=200, message = "Quiz created",response= MultipartFile.class)
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz)
    {
        return  quizService.createQuiz(quiz);
    }

    @PutMapping(value = "/updateQuiz",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz)
    {
        return  quizService.updateQuiz(quiz);
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

    @GetMapping(value = "/addQuestionToQuiz/{quizId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addQuestionToQuiz(@PathVariable Long quizId, @RequestBody @Valid Question question)
    {
        return  quizService.addQuestionToQuiz(quizId,question);
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
