package viettridao.mockproject.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ReviewDTO
 * Version: 1.0
 * Date: 5/27/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/27/2024 kiet-kun-afk Create
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long id;

    private Long inspectorId;

    private Long userId;

    private Integer rating;

    @NotBlank(message = "Comment is required")
    private String comment;
}
