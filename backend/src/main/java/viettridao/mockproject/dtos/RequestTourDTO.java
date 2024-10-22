package viettridao.mockproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.RequestTour;
import viettridao.mockproject.services.imp.FormatterService;

/**
 * RequestTour
 * Version: 1.0
 * Date: 5/30/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/30/2024 kiet-kun-afk Create
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTourDTO {

    @JsonIgnore
    private FormatterService dateTimeFormatterService = new FormatterService();

    private Long id;

    private Boolean deleted;

    // @NotNull(message = "Viewer id is required")
    private Long viewerId;

    @NotNull(message = "Property id is required")
    private Long propertyId;

    @NotBlank(message = "Request date is required")
    private String requestDate;

    @NotBlank(message = "Request time is required")
    private String requestTime;

    private Integer status;

    public RequestTourDTO(RequestTour requestTour) {
        this.id = requestTour.getId();
        this.deleted = requestTour.getDeleted();
        this.viewerId = requestTour.getViewer().getId();
        this.propertyId = requestTour.getProperty().getId();
        this.requestDate = dateTimeFormatterService.dateToString(requestTour.getRequestDate());
        this.requestTime = dateTimeFormatterService.timeToString(requestTour.getRequestTime());
        this.status = requestTour.getStatus();
    }

    public static RequestTourDTO fromRequestTour(RequestTour requestTour) {
        return new RequestTourDTO(requestTour);
    }
}
