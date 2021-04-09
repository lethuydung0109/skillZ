package miage.skillz.security.services;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Question;
import miage.skillz.entity.ReponseQuestion;
import miage.skillz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<Question> createQuestion(Question question)
    {
        log.info("Question {} ", question);
        return new ResponseEntity<>(this.questionRepository.saveAndFlush(question), HttpStatus.OK);
    }

    public ResponseEntity<Question> updateQuestion(Question question)
    {
        return  new ResponseEntity<>(this.questionRepository.saveAndFlush(question), HttpStatus.OK);
    }

    public ResponseEntity<Question> getQuestion(Question question)
    {
        return  new ResponseEntity<>(this.questionRepository.findById(question.getIdQuestion()).orElseThrow(NullPointerException::new), HttpStatus.OK);
    }

    public Set<Question> getAllQuestions()
    {
        return new HashSet<>(this.questionRepository.findAll());
    }

    public void deleteQuestion(Long qId)
    {
        this.questionRepository.deleteById(qId);
    }

    public void deleteAllQuestions()
    {
        this.questionRepository.deleteAll();
    }

    public void addResponseToQuestion(Long repId, Long qId) { }

    public void deleteResponseFromQuestion(Long repId, Long qId) { }

    public Set<ReponseQuestion> getAllQuestionResponse(Long qId)
    {
        return this.questionRepository.findById(qId).orElseThrow().getReponsesQuestions();
    }

    public Set<ReponseQuestion> getAllCorrectQuestionResponse(Long qId)
    {
            return this.getAllQuestionResponse(qId).stream().filter(ReponseQuestion::getIsCorrect).collect(Collectors.toSet());
    }

}
