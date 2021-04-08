package miage.skillz.security.services;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quizz;
import miage.skillz.repository.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuizzService {

    @Autowired
    private QuizzRepository quizzRepository;

    public ResponseEntity<Quizz> createQuizz(Quizz quizz)
    {
        // vérifier que le niveau de chaque question , correspond au niveau du quizz et que le total des poids est égal à 100%
        return  new ResponseEntity<>(this.quizzRepository.saveAndFlush(quizz), HttpStatus.OK);
    }

    public ResponseEntity<Quizz> updateQuizz(Quizz quizz)
    {
        return  new ResponseEntity<>(this.quizzRepository.saveAndFlush(quizz), HttpStatus.OK);
    }

    public ResponseEntity<Quizz> getQuizz(Long quizzId)
    {
        return  new ResponseEntity<>(this.quizzRepository.findById(quizzId).orElseThrow(NullPointerException::new), HttpStatus.OK);
    }

    public Set<Quizz> getAllQuizz() {
        return new HashSet<>(this.quizzRepository.findAll());
    }

    public boolean addQuestionToQuizz(Long quizzId, Question question)
    {
        return true;
    }

    public void deleteQuestionFromQuizz(Long quizzId, Long questionId) { }

    public void deleteQuizz(Long quizzId)
    {
        this.quizzRepository.deleteById(quizzId);
    }

    public void deleteAllQuizz() {
        this.quizzRepository.deleteAll();
    }
}
