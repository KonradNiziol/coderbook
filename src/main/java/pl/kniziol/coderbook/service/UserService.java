package pl.kniziol.coderbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.exception.AppException;
import pl.kniziol.coderbook.maper.Mappers;
import pl.kniziol.coderbook.model.User;
import pl.kniziol.coderbook.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public User registerUser(UserRegistrationDto userRegistrationDto){
        if (userRegistrationDto == null){
            throw new AppException("User for registration can't be null!");
        }
        var user = Mappers.fromRegisterUserToUser(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword().toString()));
        User registeredUser = userRepository.save(user);

        tokenService.createToken(user);

        return registeredUser;
    }




}
