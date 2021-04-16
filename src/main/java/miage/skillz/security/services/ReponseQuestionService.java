package miage.skillz.security.services;

import miage.skillz.entity.ReponseQuestion;
import miage.skillz.repository.ReponseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReponseQuestionService {

    @Autowired
    private ReponseQuestionRepository repository;

    public ResponseEntity<ReponseQuestion> createReponseQuestion(ReponseQuestion repQuestion) {
        return new ResponseEntity<>(this.repository.saveAndFlush(repQuestion), HttpStatus.OK);
    }

    public ResponseEntity<ReponseQuestion> updateReponseQuestion(ReponseQuestion repQuestion) {
        return  new ResponseEntity<>(this.repository.saveAndFlush(repQuestion), HttpStatus.OK);
    }

    public ResponseEntity<ReponseQuestion> getReponseQuestion(ReponseQuestion repQuestion) {
        return  new ResponseEntity<>(this.repository.findById(repQuestion.getIdReponse()).orElseThrow(NullPointerException::new), HttpStatus.OK);
    }

    public Set<ReponseQuestion> getAllReponseQuestions() {

        return new HashSet<>(this.repository.findAll());
    }

    public void deleteReponseQuestion(Long qId) {
        this.repository.deleteById(qId);
    }

    public void deleteAllRepQuestion() {
        this.repository.deleteAll();
    }
}
