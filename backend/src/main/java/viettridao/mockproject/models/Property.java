package viettridao.mockproject.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Property
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 */
@Entity
@Table(name = "properties")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "land_area", nullable = false)
    private Integer landArea;

    @Column(name = "usable_area", nullable = false)
    private Integer usableArea;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @Column(name = "legal_status", length = 50)
    private String legalStatus;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "description", columnDefinition = "varchar(max)")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "number_bedroom")
    private Integer numberBedroom;

    @Column(name = "number_bathroom")
    private Integer numberBathroom;

    @Column(name = "number_car_space")
    private Integer numberCarSpace;

    @Column(name = "number_living_room")
    private Integer numberLivingRoom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "listing_date", columnDefinition = "datetime default GETDATE()", nullable = false)
    private Date listingDate;

    @Column(name = "notes", columnDefinition = "varchar(1000)")
    private String notes;

    @Column(name = "status", columnDefinition = "int default 1")
    private Integer status;

    @Column(name = "deleted", columnDefinition = "bit default 0")
    private Boolean deleted;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Photo> photos;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RequestTour> requestTours;
}
