package pl.kniziol.coderbook.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatcherImpl.class)
public @interface FieldMatcher {

    String firstFieldName();
    String secondFieldName();
    String message() default "Objects are not the same";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
