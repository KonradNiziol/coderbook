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


    private String firstName;

    private String lastName;

    private String email;

    private char[] password;

    private char[] passwordConfirmation;
    private Role role;
}
