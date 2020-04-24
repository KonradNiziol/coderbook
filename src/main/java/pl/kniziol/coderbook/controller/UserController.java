package pl.kniziol.coderbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kniziol.coderbook.dto.UserRegistrationDto;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @GetMapping(value = "register")
    public UserRegistrationDto getUserRegistration(){
        return new UserRegistrationDto();
    }

    @PostMapping(value = "register")
    public String registerUser(UserRegistrationDto userRegistrationDto){
        //TODO add user to database
        return "User has been creat.";
    }


}
