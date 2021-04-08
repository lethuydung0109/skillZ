package miage.skillz.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckQuestionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface CheckQuizz {
    String message() default "Invalid Quizz";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
