package viettridao.mockproject.responses;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.models.Photo;
import viettridao.mockproject.models.Property;
import viettridao.mockproject.models.PropertyType;

/**
 * PropertyResponse
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kiet-kun-afk Create
 * 5/23/2024 kiet-kun-afk Update
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {

    private Long id;

    private Long locationId;

    private Location location;

    private Integer landArea;

    private Integer usableArea;

    private Long propertyTypeId;

    private PropertyType propertyType;

    private String legalStatus;

    private Long ownerId;

    private String description;

    private Double price;

    private Integer numberBedroom;

    private Integer numberBathroom;

    private Integer numberCarSpace;

    private Integer numberLivingRoom;

    private Date listingDate;

    private String notes;

    private Integer status;

    private Boolean deleted;

    private List<String> photUrl;

    public PropertyResponse(Property property) {
        this.id = property.getId();
        // this.locationId = property.getLocation().getId();
        this.location = property.getLocation();
        this.landArea = property.getLandArea();
        this.usableArea = property.getUsableArea();
        // this.propertyTypeId = property.getPropertyType().getId();
        this.propertyType = property.getPropertyType();
        this.legalStatus = property.getLegalStatus();
        this.ownerId = property.getOwner().getId();
        this.description = property.getDescription();
        this.price = property.getPrice();
        this.numberBedroom = property.getNumberBedroom();
        this.numberBathroom = property.getNumberBathroom();
        this.numberCarSpace = property.getNumberCarSpace();
        this.numberLivingRoom = property.getNumberLivingRoom();
        this.listingDate = property.getListingDate();
        this.notes = property.getNotes();
        this.status = property.getStatus();
        this.deleted = property.getDeleted();
        this.photUrl = property.getPhotos() != null ? property.getPhotos().stream().map(Photo::getUrl).toList()
                : this.getPhotUrl();
    }

    public static PropertyResponse fromProperty(Property property) {
        return new PropertyResponse(property);
    }

}
