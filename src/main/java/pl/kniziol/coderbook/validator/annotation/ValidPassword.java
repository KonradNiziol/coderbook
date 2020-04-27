package pl.kniziol.coderbook.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPasswordImpl.class)
public @interface ValidPassword {

    /*
    ^ # start-of-string
    (?=.*[0-9])       # a digit must occur at least once
    (?=.*[a-z])       # a lower case letter must occur at least once
    (?=.*[A-Z])       # an upper case letter must occur at least once
    (?=.*[@#$%^&+=])  # a special character must occur at least once
    (?=\S+$)          # no whitespace allowed in the entire string
     .{8,}            # anything, at least eight places though
    $                 # end-of-string
    */
    String passwordRegexp() default  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    String message() default """
                # start-of-string
                # a digit must occur at least once
                # a lower case letter must occur at least once
                # an upper case letter must occur at least once
                # a special character must occur at least once
                # no whitespace allowed in the entire string
                # anything, at least eight places though
                # end-of-string
            """;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
