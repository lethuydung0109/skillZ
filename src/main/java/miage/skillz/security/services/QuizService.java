package miage.skillz.service;

import lombok.extern.slf4j.Slf4j;
import miage.skillz.entity.Competence;
import miage.skillz.entity.Question;
import miage.skillz.entity.Quiz;
import miage.skillz.models.QuizImpl;
import miage.skillz.repository.CompetenceRepository;
import miage.skillz.repository.QuestionRepository;
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
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CompetenceRepository competenceRepository;

    public ResponseEntity<Quiz> createQuiz(QuizImpl quizImpl)
    {
        System.out.println("Received quiz : "+ quizImpl.toString());

        Quiz quiz = new Quiz(quizImpl.getName(), quizImpl.getNiveau(), quizImpl.getTheme(),
                            quizImpl.getPourcentageValidation(), quizImpl.getDuree());
        quiz.setQuizQuestions(new HashSet<>());
        quiz.setQuizCompetences(new HashSet<>());

        //Association question-quiz
        Set<Question> questions = new HashSet<>();
        quizImpl.getQuizQuestions().forEach(qId -> {
            questions.add(this.questionRepository.findById(qId).get());
        });

        //Association competence-quiz
        Set<Competence> competences = new HashSet<>();
        quizImpl.getQuizCompetences().forEach(cId -> {
            competences.add(this.competenceRepository.findById(cId).get());
        });

        // Ajout de l'entity à la bdd
        Quiz createdQuiz = this.quizRepository.save(quiz);
        createdQuiz.getQuizQuestions().addAll(questions);
        createdQuiz.getQuizCompetences().addAll(competences);

        System.out.println("created quiz : "+ createdQuiz.toString());

        return  new ResponseEntity<>(this.quizRepository.saveAndFlush(createdQuiz), HttpStatus.OK);
    }

    public ResponseEntity<Quiz> updateQuiz(QuizImpl quizImpl)
    {
        System.out.println("QuizImpl à modifier : "+quizImpl);
        Quiz quiz = new Quiz(quizImpl.getName(), quizImpl.getNiveau(), quizImpl.getTheme(),
                quizImpl.getPourcentageValidation(), quizImpl.getDuree());
        quiz.setIdQuiz(quizImpl.getIdQuiz());
        quiz.setQuizQuestions(new HashSet<>());
        quiz.setQuizCompetences(new HashSet<>());

        System.out.println("quizImpl question : "+quizImpl.getQuizQuestions());
        quizImpl.getQuizQuestions().forEach(qId -> {
            System.out.println("questionId : "+qId+" find : "+this.questionRepository.existsById(qId));
            quiz.getQuizQuestions().add(this.questionRepository.findById(qId).orElse(null));
        });

        quizImpl.getQuizCompetences().forEach(cId -> {
            System.out.println("competenceId : "+cId+" find : "+this.competenceRepository.existsById(cId));
            quiz.getQuizCompetences().add(this.competenceRepository.findById(cId).orElse(null));
        });

        Quiz quizUpdate = new Quiz();
        if(this.quizRepository.findById(quizImpl.getIdQuiz()).isPresent())
        {
            this.setQuizQuestions(quizImpl.getIdQuiz(),quiz.getQuizQuestions());
            this.setQuizCompetences(quizImpl.getIdQuiz(),quiz.getQuizCompetences());
            quizUpdate=this.quizRepository.save(quiz);
        }
        System.out.println("UpdateQuiz : "+quizUpdate);

        return  new ResponseEntity<>(quizUpdate, HttpStatus.OK);
    }

    public void setQuizQuestions(Long quizId, Set<Question> questions)
    {
        Set<Question> putQuestions= new HashSet<>();
        questions.forEach(q->{
            putQuestions.add(this.questionRepository.findById(q.getIdQuestion()).get());
        });

        Quiz putQuiz=this.quizRepository.findById(quizId).get();
        putQuiz.setQuizQuestions(putQuestions);

        putQuestions.forEach(question -> {
            question.getListQuiz().add(putQuiz);
            this.questionRepository.saveAndFlush(question);
        });

        this.quizRepository.saveAndFlush(putQuiz);
    }

    public void setQuizCompetences (Long quizId,Set<Competence> competences)
    {
        Set<Competence> putCompetences= new HashSet<>();
        competences.forEach(c->{
            putCompetences.add(this.competenceRepository.findById(c.getId()).get());
        });

        Quiz putQuiz=this.quizRepository.findById(quizId).get();
        putQuiz.setQuizCompetences(putCompetences);

        putCompetences.forEach(competence -> {
            competence.getListQuiz().add(putQuiz);
            this.competenceRepository.saveAndFlush(competence);
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

    public Set<Question> getQuizQuestions(Long quizId) {
        return new HashSet<>(this.quizRepository.findById(quizId).get().getQuizQuestions());
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
        Question deleteQuestion = this.questionRepository.findById(questionId).get();
        Quiz quiz = this.quizRepository.findById(questionId).get();

        quiz.getQuizQuestions().remove(deleteQuestion);
        deleteQuestion.getListQuiz().remove(quiz);
        this.questionRepository.saveAndFlush(deleteQuestion);
        this.quizRepository.saveAndFlush(quiz);
    }

    public void deleteQuiz(Long quizId)
    {
        Quiz deletedQuiz = this.quizRepository.findById(quizId).get();
        if(this.quizRepository.existsById(quizId))
        {
            Set<Question> questions = deletedQuiz.getQuizQuestions();
            Set<Competence> competences = deletedQuiz.getQuizCompetences();
            questions.forEach(question -> {
                this.questionRepository.findById(question.getIdQuestion()).get().getListQuiz().remove(deletedQuiz);
                this.questionRepository.saveAndFlush(question);
            });

            competences.forEach(competence -> {
                this.competenceRepository.findById(competence.getId()).get().getListQuiz().remove(deletedQuiz);
                this.competenceRepository.saveAndFlush(competence);
            });

            questions.clear();
            competences.clear();

            this.quizRepository.saveAndFlush(deletedQuiz);
            this.quizRepository.deleteById(quizId);
        }
    }

    public void deleteAllQuiz() {
        this.quizRepository.findAll().forEach(quiz -> {
            this.deleteQuiz(quiz.getIdQuiz());
        });
    }
}
