package pl.kniziol.coderbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.kniziol.coderbook.model.User;
import pl.kniziol.coderbook.model.VerificationToken;
import pl.kniziol.coderbook.repository.TokenRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JavaMailSender javaMailSender;

    public VerificationToken createToken(User user){
        var token = UUID.randomUUID().toString().replaceAll("\\W", "");
        VerificationToken verificationToken =
                VerificationToken.builder()
                        .user(user)
                        .token(token)
                        .build();
        verificationToken = tokenRepository.save(verificationToken);
        sendEmail(user.getEmail(), verificationToken.getToken());
        return verificationToken;
    }

    private void sendEmail(String email, String token) {

        String recipientAddress = email;
        String subject = "Registration confirmation";
        String confirmationUrl = "http://localhost:8080/sing/activate?token=" + token;
        String message = "Click to activate your account: " + confirmationUrl;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);

    }
}
