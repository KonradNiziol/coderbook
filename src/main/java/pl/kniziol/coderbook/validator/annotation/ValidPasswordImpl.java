package pl.kniziol.coderbook.validator.annotation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, char[]> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(char[] chars, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(getCharacterRuleForPassword());
        RuleResult ruleResult = passwordValidator.validate(new PasswordData(String.valueOf(chars)));
        if (ruleResult.isValid()){
            return true;
        }
        context.buildConstraintViolationWithTemplate(
                passwordValidator.getMessages(ruleResult)
                        .stream()
                        .collect(Collectors.joining(",")))
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

    private List<Rule> getCharacterRuleForPassword(){
        return Arrays.asList(

                new LengthRule(8, 30),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespaces
                new WhitespaceRule());
    }

}
