package miage.skillz.service;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quiz;
import miage.skillz.entity.User;
import miage.skillz.models.QuizImpl;
import miage.skillz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Quiz> createQuiz(QuizImpl quizImpl,User currentUser)
    {
        System.out.println("Received quiz : "+ quizImpl.toString());
        Quiz quiz = new Quiz(quizImpl.getName(),
                            niveauRepository.findById(quizImpl.getIdNiveau()).orElseThrow(),
                            quizImpl.getTheme(),quizImpl.getSeuilValidation(),quizImpl.getDuree(),
                            quizImpl.getDateOfCreation(),competenceRepository.findById(quizImpl.getIdCompetence()).orElseThrow());
        quiz.setQuizQuestions(new HashSet<>());

        //Association question-quiz
        Set<Question> questions = new HashSet<>();
        for (Long qId : quizImpl.getQuizQuestionsId()) {
            questions.add(this.questionRepository.findById(qId).orElseThrow());
        }

        // Ajout de l'entity à la bdd
        Quiz createdQuiz = this.quizRepository.save(quiz);
        createdQuiz.getQuizQuestions().addAll(questions);

        System.out.println("created quiz : "+ createdQuiz.toString());

        createdQuiz.setUser(currentUser);
        currentUser.getMyCreatedQuiz().add(createdQuiz);

        return  new ResponseEntity<>(this.quizRepository.saveAndFlush(createdQuiz), HttpStatus.OK);
    }

    public Set<Quiz> createListQuiz(Set<QuizImpl> listQuiz, User currentUser)
    {
        Set<Quiz> listCreatedQuiz = new HashSet<>();
        listQuiz.forEach(qImpl -> listCreatedQuiz.add( this.createQuiz(qImpl,currentUser).getBody()));

        return listCreatedQuiz;
    }

    public ResponseEntity<Quiz> updateQuiz(QuizImpl quizImpl,User currentUser)
    {
        System.out.println("QuizImpl à modifier : "+quizImpl);
        Quiz quiz = this.quizRepository.findById(quizImpl.getIdQuiz()).orElseThrow();
        quiz.setName(quizImpl.getName());
        quiz.setNiveau(niveauRepository.findById(quizImpl.getIdNiveau()).orElseThrow());
        quiz.setTheme(quizImpl.getTheme());
        quiz.setSeuilValidation(quizImpl.getSeuilValidation());
        quiz.setDuree(quizImpl.getDuree());
        quiz.setDateOfCreation(quizImpl.getDateOfCreation());
        quiz.setQuizCompetence(competenceRepository.findById(quizImpl.getIdCompetence()).orElseThrow());
        quiz.setIdQuiz(quizImpl.getIdQuiz());
        quiz.setQuizQuestions(new HashSet<>());

        System.out.println("quizImpl question : "+quizImpl.getQuizQuestionsId());
        quizImpl.getQuizQuestionsId().forEach(qId -> {
            System.out.println("questionId : "+qId+" find : "+this.questionRepository.existsById(qId));
            quiz.getQuizQuestions().add(this.questionRepository.findById(qId).orElse(null));
        });

        this.setQuizQuestions(quizImpl.getIdQuiz(),quiz.getQuizQuestions());

        return  new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    public void setQuizQuestions(Long quizId, Set<Question> questions)
    {
        Set<Question> putQuestions= new HashSet<>();
        questions.forEach(q-> putQuestions.add(this.questionRepository.findById(q.getIdQuestion()).orElseThrow()));

        Quiz putQuiz=this.quizRepository.findById(quizId).orElseThrow();
        putQuiz.setQuizQuestions(putQuestions);

        putQuestions.forEach(question -> {
            question.getListQuiz().add(putQuiz);
            this.questionRepository.saveAndFlush(question);
        });

        this.quizRepository.saveAndFlush(putQuiz);
    }

    public ResponseEntity<Quiz> getQuiz(Long quizId)
    {
        return  new ResponseEntity<>(this.quizRepository.findById(quizId).orElseThrow(NullPointerException::new), HttpStatus.OK);
    }

    public Set<Quiz> getAllQuiz() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    public Set<Quiz> getAllQuizByUser(Long userId) {

        return quizRepository.findAll()
                            .stream()
                            .filter(quiz -> quiz.getUser().equals(userRepository.findById(userId).orElseThrow()))
                            .collect(Collectors.toSet());
    }

    public Set<Question> getQuizQuestions(Long quizId) {
        return new HashSet<>(this.quizRepository.findById(quizId).orElseThrow().getQuizQuestions());
    }

    public boolean addQuestionToQuiz(Long quizId, Long questionId)
    {
        Quiz quiz = this.quizRepository.findById(quizId).orElse(null);
        Question question = this.questionRepository.findById(questionId).orElse(null);
        if(quiz!=null && question !=null)
        {
            question.getListQuiz().add(quiz);
            quiz.getQuizQuestions().add(question);
            this.questionRepository.saveAndFlush(question);
            this.quizRepository.saveAndFlush(quiz);
            return true;
        }
        else return false;
    }

    public void deleteQuestionFromQuiz(Long quizId, Long questionId) {
        Question deleteQuestion = this.questionRepository.findById(questionId).orElseThrow();
        Quiz quiz = this.quizRepository.findById(quizId).orElseThrow();

        quiz.getQuizQuestions().remove(deleteQuestion);
        deleteQuestion.getListQuiz().remove(quiz);
        this.questionRepository.saveAndFlush(deleteQuestion);
        this.quizRepository.saveAndFlush(quiz);
    }

    public void deleteQuiz(Long quizId)
    {
        Quiz deletedQuiz = this.quizRepository.findById(quizId).orElseThrow();
        if(this.quizRepository.existsById(quizId))
        {
            Set<Question> questions = deletedQuiz.getQuizQuestions();
           // Set<Competence> competences = deletedQuiz.getQuizCompetences();
            questions.forEach(question -> {
                this.questionRepository.findById(question.getIdQuestion()).orElseThrow().getListQuiz().remove(deletedQuiz);
                this.questionRepository.saveAndFlush(question);
            });

//            competences.forEach(competence -> {
//                this.competenceRepository.findById(competence.getId()).orElseThrow().getListQuiz().remove(deletedQuiz);
//                this.competenceRepository.saveAndFlush(competence);
//            });

            questions.clear();
            deletedQuiz.setNiveau(null);
            this.competenceRepository.findById(deletedQuiz.getQuizCompetence().getId()).orElseThrow().getListQuiz().remove(deletedQuiz);
            deletedQuiz.setQuizCompetence(null);
            this.userRepository.findById(deletedQuiz.getUser().getId()).orElseThrow().getMyCreatedQuiz().remove(deletedQuiz);
            deletedQuiz.setUser(null);

            this.quizRepository.saveAndFlush(deletedQuiz);
            this.quizRepository.deleteById(quizId);
        }
    }

    public void deleteAllQuiz() {
        this.quizRepository.findAll().forEach(quiz -> this.deleteQuiz(quiz.getIdQuiz()));
    }
}
