package pl.kniziol.coderbook.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPasswordImpl.class)
public @interface ValidPassword {

    String message() default "Invalid password!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
