package pl.kniziol.coderbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kniziol.coderbook.model.enums.Role;
import pl.kniziol.coderbook.validator.annotation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {

    @NotBlank
    @Size(min = 2)
    private String firstName;
    @NotBlank
    @Size(min = 2)
    private String lastName;
    @Email
    private String email;
    //@ValidPassword
    private char[] password;
    //@ValidPassword
    private char[] passwordConfirmation;
    private Role role;
}
