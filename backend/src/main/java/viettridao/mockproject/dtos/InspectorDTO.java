package viettridao.mockproject.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.Inspector;

/**
 * InspectorDTO
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
public class InspectorDTO {

    private Long id;

    @NotBlank(message = "First name is not blank")
    private String firstName;

    @NotBlank(message = "Last name is not blank")
    private String lastName;

    @NotBlank(message = "Phone number is not blank")
    private String phoneNumber;

    private String company;

    private Boolean active;

    public InspectorDTO(Inspector inspector) {
        this.id = inspector.getId();
        this.firstName = inspector.getFirstName();
        this.lastName = inspector.getLastName();
        this.phoneNumber = inspector.getPhoneNumber();
        this.company = inspector.getCompany();
        this.active = inspector.getActive();
    }

    public static InspectorDTO fromInspector(Inspector inspector) {
        return new InspectorDTO(inspector);
    }
}
