package viettridao.mockproject.models;

import java.time.LocalDateTime;

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
 * ContractBuy
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract_buy")
@Builder
public class ContractBuy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @ManyToOne
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "desired_location_id")
    private Location desiredLocation;

    @Column(name = "desired_price")
    private Double desiredPrice;

    @Column(name = "desired_area")
    private Integer desiredArea;

    @Column(name = "other_requests", length = 1000)
    private String otherRequests;

    @Column(name = "brokerage_fee", nullable = false, columnDefinition = "float default 0")
    private Double brokerageFee;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "progress", nullable = false, columnDefinition = "int default 1")
    private Integer progress;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    private Boolean deleted;

    @Column(name = "attachment", nullable = false, length = 1000)
    private String attachment;
}
