package viettridao.mockproject.dtos.locations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LocationDTO
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 diepit9x Create
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;

    @NotBlank(message = "Street is not blank")
    @Size(min = 2, max = 40, message = "Street must be between 2 to 20 characters")
    private String street;

    @NotBlank(message = "City is not blank")
    @Size(min = 2, max = 40, message = "City must be between 2 to 20 characters")
    private String city;

    @NotBlank(message = "State is not blank")
    @Size(min = 2, max = 40, message = "State must be between 2 to 20 characters")
    private String state;

    @NotBlank(message = "Zip code is not blank")
    @Size(min = 2, max = 40, message = "Zip code must be between 2 to 20 characters")
    private String zipCode;

    @NotBlank(message = "Country is not blank")
    @Size(min = 2, max = 40, message = "Country must be between 2 to 20 characters")
    private String country;

    private Boolean deleted = false;
}
