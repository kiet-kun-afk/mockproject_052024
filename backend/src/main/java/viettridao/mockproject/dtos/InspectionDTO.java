package viettridao.mockproject.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * InspectionDTO
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InspectionDTO {

    private Long id;

    private Long inspectorId;

    private Long propertyId;

    @NotBlank(message = "Content is required")
    @Size(max = 255, message = "Content must be less than 255 characters")
    private String content;
}
