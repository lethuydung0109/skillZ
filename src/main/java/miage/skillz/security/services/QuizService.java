package miage.skillz.security.services;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quiz;
import miage.skillz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public ResponseEntity<Quiz> createQuiz(Quiz quiz)
    {
        System.out.println("Received quiz : "+ quiz.toString());
        //Set<Question> questions = quiz.getQuestionsQuiz();

        return  new ResponseEntity<>(this.quizRepository.saveAndFlush(quiz), HttpStatus.OK);
    }

    public ResponseEntity<Quiz> updateQuiz(Quiz quiz)
    {
        return  new ResponseEntity<>(this.quizRepository.saveAndFlush(quiz), HttpStatus.OK);
    }

    public ResponseEntity<Quiz> getQuiz(Long quizId)
    {
        return  new ResponseEntity<>(this.quizRepository.findById(quizId).orElseThrow(NullPointerException::new), HttpStatus.OK);
    }

    public Set<Quiz> getAllQuiz() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    public boolean addQuestionToQuiz(Long quizId, Question question)
    {
        return true;
    }

    public void deleteQuestionFromQuiz(Long quizId, Long questionId) { }

    public void deleteQuiz(Long quizId)
    {
        this.quizRepository.deleteById(quizId);
    }

    public void deleteAllQuiz() {
        this.quizRepository.deleteAll();
    }
}
