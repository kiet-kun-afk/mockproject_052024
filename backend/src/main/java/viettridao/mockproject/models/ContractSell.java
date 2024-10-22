package viettridao.mockproject.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ContractSell
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract_sell")
@Builder
public class ContractSell extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "desired_price", nullable = false, columnDefinition = "float default 0")
    private Double desiredPrice;

    @Column(name = "deposit")
    private Double deposit;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "brokerage_fee", nullable = false, columnDefinition = "float default 0")
    private Double brokerageFee;

    @Column(name = "progress", nullable = false, columnDefinition = "int default 1")
    private Integer progress;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    private Boolean deleted;

    @Column(name = "attachment", nullable = false, length = 1000)
    private String attachment;

    private LocalDateTime approvedAt;
}
