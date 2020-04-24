package pl.kniziol.coderbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kniziol.coderbook.model.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {
    private String username;
    private String email;
    private String password;
    private String passwordConfirmation;
    private Role role;
}
