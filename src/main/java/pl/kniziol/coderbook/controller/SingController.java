package pl.kniziol.coderbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.kniziol.coderbook.dto.MessageInformation;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.model.enums.Role;
import pl.kniziol.coderbook.service.TokenService;
import pl.kniziol.coderbook.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/sing")
@RequiredArgsConstructor
public class SingController {

    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping(value = "/register")
    public String getUserRegistration(UserRegistrationDto userRegistrationDto, Model model){
        model.addAttribute("roles", Role.values());
        return "sing/register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@Valid @ModelAttribute UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "sing/register";
        }
        userService.registerUser(userRegistrationDto);
        return "redirect:/sing/login";
    }


    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("error", "");
        return "sing/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("error", "Authentication error");
        return "sing/login";
    }

    @GetMapping("/activate")
    public String activateAccount(@RequestParam String token, Model model){
        if (token == null || token.isBlank()){
            model.addAttribute("message", MessageInformation.builder()
                    .cssStyle("alert-danger")
                    .messageStatus("Danger!")
                    .message("Verification Token is empty! Try again!")
                    .build());
            return "infoMessage";
        }
        String message = tokenService.activateAccount(token);
        model.addAttribute("message", MessageInformation.builder()
                .cssStyle("alert-success")
                .messageStatus("Success!")
                .message(message)
                .build());
        return "infoMessage";
    }

}
