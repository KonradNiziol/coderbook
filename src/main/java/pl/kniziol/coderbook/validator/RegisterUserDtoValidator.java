package pl.kniziol.coderbook.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.repository.UserRepository;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RegisterUserDtoValidator implements Validator {

    private final UserRepository userRepository;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegistrationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o instanceof UserRegistrationDto registerUserDto){

            validName(registerUserDto.getUsername(), errors);
            validEmail(registerUserDto.getEmail(), errors);
            validPassword(registerUserDto.getPassword(), registerUserDto.getPasswordConfirmation(), errors);

        }


    }

    private void validName(String username, Errors errors){
        if (Objects.isNull(username) || username.isEmpty() || username.length() < 2){
            errors.rejectValue("username", "The user name should be longer than 3 characters");
        }
    }

    private void validEmail(String email, Errors errors){
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()){
            errors.rejectValue("email", "The e-mail is not correct");
        } else if (userRepository.findByEmail(email)){
            errors.rejectValue("email", email + " is already taken");
        }
    }

    private void validPassword(String password, String confirmPassword, Errors errors){
        if (!password.equals(confirmPassword)){
            errors.rejectValue("password", "The passwords given are not the same");
        }
    }
}
