package viettridao.mockproject.dtos.properties;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * PropertyDTO
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kiet-kun-afk Create
 * 5/27/2024 kiet-kun-afk Update
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDTO {

    private Long id;

    @NotNull(message = "Location id is not null")
    @Positive(message = "Location id is positive")
    private Long locationId;

    @NotNull(message = "Land area is not null")
    @Positive(message = "Land area is positive")
    private Integer landArea;

    @NotNull(message = "Usable area is not null")
    @Positive(message = "Usable area is positive")
    private Integer usableArea;

    @NotNull(message = "Property type id is not null")
    @Positive(message = "Property type id is positive")
    private Long propertyTypeId;

    @NotBlank(message = "Legal status id is not blank")
    private String legalStatus;

    @NotNull(message = "Owner id is not null")
    @Positive(message = "Owner id is positive")
    private Long ownerId;

    private String description;

    @NotNull(message = "Price is not null")
    @Positive(message = "Price is positive")
    private Double price;

    @NotNull(message = "Number bedroom is not null")
    @Min(value = 1, message = "Min is 1")
    @Max(value = 50, message = "Max is 50")
    private Integer numberBedroom;

    @NotNull(message = "Number bathroom is not null")
    @Min(value = 1, message = "Min is 1")
    @Max(value = 50, message = "Max is 50")
    private Integer numberBathroom;

    @NotNull(message = "Number car space is not null")
    @Min(value = 0, message = "Min is 0")
    @Max(value = 50, message = "Max is 50")
    private Integer numberCarSpace;

    @NotNull(message = "Number living room is not null")
    @Min(value = 0, message = "Min is 0")
    @Max(value = 50, message = "Max is 50")
    private Integer numberLivingRoom;

    @PastOrPresent(message = "Date is present or past")
    private Date listingDate;

    @Size(max = 1000, message = "Length is 1000")
    private String notes;

    @Min(value = 1, message = "Min is 1")
    @Max(value = 5, message = "Max is 5")
    @NotNull(message = "Status is not null")
    private Integer status;

    private Boolean deleted;

    private List<MultipartFile> files;
}
