package pl.kniziol.coderbook.maper;

import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.model.User;

public interface Mappers {

    static User fromRegisterUserToUser(UserRegistrationDto registerUserDto){
        return User.builder()
                .username(registerUserDto.getUsername())
                .email(registerUserDto.getEmail())
                .password(registerUserDto.getPassword())
                .role(registerUserDto.getRole())
                .enabled(false)
                .build();
    }
}
