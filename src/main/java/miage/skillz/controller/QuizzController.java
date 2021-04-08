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

    @PutMapping("/all")
    @RequestMapping(value = "/updateQuizz", method = RequestMethod.PUT,consumes = "application/json",produces = "application/json")
    public ResponseEntity<Quizz> updateQuizz(@RequestBody @Valid Quizz quizz)
    {
        return  quizzService.updateQuizz(quizz);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/getQuizz/{quizzId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quizz> getQuizz(@PathVariable Long quizzId)
    {
        return  quizzService.getQuizz(quizzId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/allQuizz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Quizz> getAllQuizz()
    {
        return  quizzService.getAllQuizz();
    }

    @GetMapping("/all")
    @RequestMapping(value = "/addQuestionToQuizz/{quizzId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addQuestionToQuizz(@PathVariable Long quizzId, @RequestBody @Valid Question question)
    {
        return  quizzService.addQuestionToQuizz(quizzId,question);
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteQuestionFromQuizz/{quizzId}/{qId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestionFromQuizz(@PathVariable Long quizzId, @PathVariable Long qId)
    {
        quizzService.deleteQuestionFromQuizz(quizzId, qId);
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteQuizzById/{quizzId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuizz(@PathVariable Long quizzId)
    {
        quizzService.deleteQuizz(quizzId);
    }

    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteAllQuizz", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllQuizz()
    {
        quizzService.deleteAllQuizz();
    }
}
