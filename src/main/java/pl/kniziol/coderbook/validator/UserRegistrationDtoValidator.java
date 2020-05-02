package pl.kniziol.coderbook.validator;

import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRegistrationDtoValidator implements Validator {

    private final MessageResolver messageResolver;
    private final UserRepository userRepository;
    private final Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegistrationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");

        UserRegistrationDto user = (UserRegistrationDto) o;
        isValidPassword(user.getPassword(), errors);

        if (!emailPattern.matcher(user.getEmail()).matches()){
            errors.rejectValue("email", "email.pattern");
        }else {
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(userDB -> errors.rejectValue("email", "email.unique"));
        }

        if (!Arrays.equals(user.getPassword(), user.getPasswordConfirmation())){
            errors.rejectValue("passwordConfirmation", "password.equal");
        }

    }

    public void isValidPassword(char[] chars, Errors errors) {
        PasswordValidator passwordValidator = new PasswordValidator(messageResolver, getCharacterRuleForPassword());
        RuleResult ruleResult = passwordValidator.validate(new PasswordData(String.valueOf(chars)));
        if (!ruleResult.isValid()){
            passwordValidator
                    .getMessages(ruleResult)
                    .stream()
                    .forEach(error -> errors.rejectValue("password", error));
        }

    }

    private List<Rule> getCharacterRuleForPassword(){
        return Arrays.asList(




                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1));
    }
}
