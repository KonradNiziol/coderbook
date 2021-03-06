package pl.kniziol.coderbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kniziol.coderbook.model.enums.Role;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;
}
