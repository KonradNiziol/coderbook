package pl.kniziol.coderbook.maper;

import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.model.User;

public interface Mappers {

    static User fromRegisterUserToUser(UserRegistrationDto registerUserDto){
        return User.builder()
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .email(registerUserDto.getEmail())
                .role(registerUserDto.getRole())
                .enabled(false)
                .build();
    }
}
