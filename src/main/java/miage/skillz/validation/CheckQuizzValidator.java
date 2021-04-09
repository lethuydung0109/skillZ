package miage.skillz.validation;

import miage.skillz.entity.Question;
import miage.skillz.entity.Quizz;
import miage.skillz.enumeration.ENiveau;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class CheckQuizzValidator implements ConstraintValidator<CheckQuizz, Quizz> {

    public void initialize(Quizz quizz) { }

    @Override
    public boolean isValid(Quizz quizz, ConstraintValidatorContext cxt) {
        if(null == quizz) return false; // quizz ne peut pas être null

        try{
            if(quizz.getName() == null) throw  new IllegalArgumentException("Un libelle doit être défini");
            if(quizz.getTheme() == null) throw  new IllegalArgumentException("Un theme doit être défini");
            if(quizz.getNiveau() == null) throw  new IllegalArgumentException("Un niveau doit être défini");
            if(quizz.getPourcentageValidation() == null) throw  new IllegalArgumentException("Un pourcentage de validation doit être défini");
            if(quizz.getDuree() == null) throw  new IllegalArgumentException("Une duree doit être défini");
            //if(isNullOrEmpty(quizz.getCompetences())) throw  new IllegalArgumentException("Une liste de competences doit être défini");
            if(isNullOrEmpty(quizz.getQuestionsQuizz())) throw  new IllegalArgumentException("Une liste de question doit être défini");
            if(quizzQuestionIsValid(quizz.getQuestionsQuizz(), quizz.getNiveau())) throw  new IllegalArgumentException("Une question doit être de même niveau que le quizz");
            if(sommePoidsQuestionIsValid(quizz.getQuestionsQuizz())) throw  new IllegalArgumentException("La somme des poids des questions d'un quizz doit être égal à 100");
            // pour chaque question comparer le niveau avec celui du quizz et vérifier que son poids est different de 0
            // somme des poids des questions != 100
        }
        catch (IllegalArgumentException e)
        {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean quizzQuestionIsValid(Set<Question> questionsQuizz, String quizzENiveau)
    {
        for (Question question : questionsQuizz) {
            if (!question.getNiveau().equals(quizzENiveau)) return false;
        }
        return true;
    }

    private boolean sommePoidsQuestionIsValid(Set<Question> questionsQuizz)
    {
        long poidsQuizz = 0;

        for (Question question : questionsQuizz) {
            if (question.getPoids() == 0 ) throw  new IllegalArgumentException("Une question doit avoir un poids");
            else poidsQuizz += question.getPoids();
        }

        return poidsQuizz == 100;
    }

    private <T> boolean isNullOrEmpty(Set<T> object) {
        return object == null || object.isEmpty();
    }


}
