package viettridao.mockproject.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRegisterDTO
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    // @NotBlank(message = "Username is not blank")
    @Size(min = 5, max = 30, message = "Username must be between 5 to 30 characters")
    private String username;

    @Size(min = 9, max = 14, message = "Username must be between 9 to 14 characters")
    private String phone;

    @NotBlank(message = "Email is not blank")
    @Email(message = "Email is invalid")
    @Size(min = 5, max = 40, message = "Email must be between 5 to 40 characters")
    private String email;

    @NotBlank(message = "Password is not blank")
    @Size(min = 5, max = 40, message = "Password must be between 5 to 40 characters")
    private String password;

    @NotBlank(message = "Retype Password is not blank")
    @Size(min = 5, max = 40, message = "Retype Password must be between 5 to 40 characters")
    private String retypePassword;
}
