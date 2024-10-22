package viettridao.mockproject.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RequestTour
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Entity
@Table(name = "request_tours")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTour extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "viewer_id", nullable = false, referencedColumnName = "id")
    private User viewer;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, referencedColumnName = "id")
    private Property property;

    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "request_time")
    private LocalTime requestTime;

    private Integer status;
}
