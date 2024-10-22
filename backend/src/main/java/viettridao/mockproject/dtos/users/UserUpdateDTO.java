package viettridao.mockproject.dtos.users;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserUpdateDTO
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 diepit9x Create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private Long id;

    @NotBlank(message = "First name is not blank")
    @Size(min = 2, max = 20, message = "First name must be between 2 to 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is not blank")
    @Size(min = 2, max = 20, message = "Last name must be between 2 to 20 characters")
    private String lastName;

    @NotNull(message = "Location's id is required")
    @Min(value = 1, message = "Location's id mút be > 1")
    private Long locationId;

    @NotBlank(message = "Phone is not blank")
    @Size(min = 5, max = 20, message = "Phone must be between 5 to 20 characters")
    private String phone;

    @Email(message = "Email is invalid")
    @Size(min = 5, max = 40, message = "Email must be between 5 to 40 characters")
    private String email;

    private String username;

    private Boolean active;

    private Long roleId;

    private String jobDescription;

}
