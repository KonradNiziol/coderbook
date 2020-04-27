package pl.kniziol.coderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kniziol.coderbook.model.VerificationToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);
}
