package viettridao.mockproject.dtos.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PropertyTypeDTO
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeDTO {

    @NotBlank(message = "Property name is not blank")
    @Size(min = 2, max = 40, message = "Property name must be between 2 to 20 characters")
    private String name;

    private Boolean deleted = false;
}
