package pl.kniziol.coderbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "verification_tokens")
public class VerificationToken {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private LocalDateTime expirationDateTime;
    private String token;
}
