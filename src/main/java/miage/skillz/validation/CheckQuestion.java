package miage.skillz.validation;


import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = CheckQuestionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface CheckQuestion {
    String message() default "Invalid Question";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
