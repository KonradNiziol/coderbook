package pl.kniziol.coderbook.validator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, String> {

    private String passwordRegexp;
    private String message;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.passwordRegexp = constraintAnnotation.passwordRegexp();
        this.message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile(passwordRegexp).matcher(password).matches();
    }
}
