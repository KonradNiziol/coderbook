package pl.kniziol.coderbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.service.UserService;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "register")
    public UserRegistrationDto getUserRegistration(){
        return new UserRegistrationDto();
    }

    @PostMapping(value = "register")
    public Map<String, String> registerUser(@Valid UserRegistrationDto userRegistrationDto, BindingResult bindingResult){
        Map<String, String> errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));

        if (errors.isEmpty()){
            userService.registerUser(userRegistrationDto);
        }
        return errors;
    }


}
