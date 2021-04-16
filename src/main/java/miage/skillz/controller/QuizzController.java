package miage.skillz.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quizz;
import miage.skillz.security.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class QuizzController {

    @Autowired
    private QuizzService quizzService;

    @PostMapping(value = "/createQuizz", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create Quizz")
    @ApiResponse(code=200, message = "Quizz created",response= MultipartFile.class)
    public ResponseEntity<Quizz> createQuizz(@RequestBody @Valid Quizz quizz)
    {
        return  quizzService.createQuizz(quizz);
    }

    @PutMapping(value = "/updateQuizz",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Quizz> updateQuizz(@RequestBody Quizz quizz)
    {
        return  quizzService.updateQuizz(quizz);
    }

    @GetMapping(value = "/getQuizz/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quizz> getQuizz(@PathVariable Long quizzId)
    {
        return  quizzService.getQuizz(quizzId);
    }

    @GetMapping(value = "/allQuizz", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Quizz> getAllQuizz()
    {
        return  quizzService.getAllQuizz();
    }

    @GetMapping(value = "/addQuestionToQuizz/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addQuestionToQuizz(@PathVariable Long quizzId, @RequestBody @Valid Question question)
    {
        return  quizzService.addQuestionToQuizz(quizzId,question);
    }

    @DeleteMapping(value = "/deleteQuestionFromQuizz/{quizzId}/{qId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestionFromQuizz(@PathVariable Long quizzId, @PathVariable Long qId)
    {
        quizzService.deleteQuestionFromQuizz(quizzId, qId);
    }

    @DeleteMapping(value = "/deleteQuizzById/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuizz(@PathVariable Long quizzId)
    {
        quizzService.deleteQuizz(quizzId);
    }

    @DeleteMapping(value = "/deleteAllQuizz", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllQuizz()
    {
        quizzService.deleteAllQuizz();
    }
}
