package miage.skillz.service;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Question;
import miage.skillz.entity.ReponseQuestion;
import miage.skillz.repository.QuestionRepository;
import miage.skillz.repository.ReponseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReponseQuestionRepository repQuestionRepository;

    public ResponseEntity<Question> createQuestion(Question question)
    {
        System.out.println("Question received 1 : "+question);
        Set<ReponseQuestion> reponseQuestions = new HashSet<>(question.getReponsesQuestions());
        question.setReponsesQuestions(new HashSet<>());

        Question createdQuestion = this.questionRepository.save(question);

        Set<ReponseQuestion> createdRepQuestions = new HashSet<>();

        for (ReponseQuestion rep: reponseQuestions) {
            rep.setQuestion(createdQuestion);
            ReponseQuestion newRep = this.repQuestionRepository.save(rep);
            createdRepQuestions.add(newRep);
        }

        createdQuestion.setReponsesQuestions(createdRepQuestions);

        return new ResponseEntity<>(this.questionRepository.saveAndFlush(createdQuestion), HttpStatus.OK);
    }

    public ResponseEntity<Question> updateQuestion(Question question)
    {
        return  new ResponseEntity<>(this.questionRepository.saveAndFlush(question), HttpStatus.OK);
    }

    public Question getQuestion(Long qId)
    {
        return this.questionRepository.findById(qId).orElseThrow(NullPointerException::new);
    }

    public List<Question> getAllQuestions()
    {
        return this.questionRepository.findAll();
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
