package viettridao.mockproject.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.Inspection;
import viettridao.mockproject.models.Inspector;

/**
 * InspectionResponse
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
public class InspectionResponse {

    private Long id;

    private Inspector inspector;

    private Long propertyId;

    private String content;

    public InspectionResponse(Inspection inspection) {
        this.id = inspection.getId();
        this.inspector = inspection.getInspector();
        this.propertyId = inspection.getProperty().getId();
        this.content = inspection.getContent();
    }

    public static InspectionResponse fromInspection(Inspection inspection) {
        return new InspectionResponse(inspection);
    }
}
