package pl.kniziol.coderbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.kniziol.coderbook.dto.LoginCredentials;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.service.UserService;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/register")
    public String getUserRegistration(Model model){
        model.addAttribute("error", "");
        model.addAttribute("user", new UserRegistrationDto());
        return "users/register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@Valid @ModelAttribute UserRegistrationDto userRegistrationDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            var errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));

            model.addAttribute("user", userRegistrationDto);
            model.addAttribute("errors", errors);
            return "users/register";
        }
        userService.registerUser(userRegistrationDto);
        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("error", "");
        model.addAttribute("credentials", new LoginCredentials());
        return "users/login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute LoginCredentials loginCredentials,  Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            var errors = getErrorsFrom(bindingResult);
            model.addAttribute("credentials", loginCredentials);
            model.addAttribute("error", errors);
            return "users/login";
        }

        return "index";
    }

    private Map<String, String> getErrorsFrom(BindingResult bindingResult){
        return bindingResult
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));
    }

}
