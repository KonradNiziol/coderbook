package pl.kniziol.coderbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kniziol.coderbook.dto.UserRegistrationDto;
import pl.kniziol.coderbook.exception.AppException;
import pl.kniziol.coderbook.maper.Mappers;
import pl.kniziol.coderbook.repository.TokenRepository;
import pl.kniziol.coderbook.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JavaMailSender javaMailSender;

    public boolean registerUser(UserRegistrationDto userRegistrationDto){
        if (userRegistrationDto == null){
            throw new AppException("User for registration can't be null!");
        }
        var user = Mappers.fromRegisterUserToUser(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        userRepository.save(user);
        return true;
    }


}
