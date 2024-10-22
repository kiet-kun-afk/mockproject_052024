package viettridao.mockproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Localtion
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT ''")
    private String street;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT ''")
    private String city;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT ''")
    private String state;

    @Column(name = "zip_code", columnDefinition = "varchar(50) DEFAULT ''")
    private String zipCode;

    @Column(nullable = false, columnDefinition = "varchar(50) DEFAULT ''")
    private String country;

    private Boolean deleted;
}
