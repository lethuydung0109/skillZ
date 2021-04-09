package miage.skillz.validation;

import miage.skillz.entity.Question;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Set;

public class CheckQuestionValidator implements ConstraintValidator<CheckQuestion, Question> {

    public void initialize(Question question) { }

    @Override
    public boolean isValid(Question question, ConstraintValidatorContext cxt) {
        if(null == question) return false; // question ne peut pas être vide

        try{
            if(question.getName() == null) throw  new IllegalArgumentException("Un libelle doit être défini");
            if(question.getNiveau() ==null) throw  new IllegalArgumentException("Un Niveau doit être défini");
            if(isNullOrEmpty(question.getReponsesQuestions())) throw  new IllegalArgumentException("Une reponse doit être défini");
            // au moins 2 repponses dont une bonne
            //if(isNullOrEmpty(question.getCompetences())) throw  new IllegalArgumentException("Une competence doit être défini");
        }
        catch (IllegalArgumentException e)
        {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
            return false;
        }

        return true;
    }

    private <T> boolean isNullOrEmpty(Set<T> object)
    {
        return object == null || object.isEmpty();
    }

    private <K,V> boolean isNullOrEmpty(Map<K,V> object)
    {
        return object == null || object.isEmpty();
    }
}
