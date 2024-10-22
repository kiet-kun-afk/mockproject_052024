package viettridao.mockproject.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ContractSellBuy
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 */
@Entity
@Table(name = "contract_sell_buy")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractSellBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "contract_buy_id", nullable = false)
    private ContractBuy contractBuy;

    @OneToOne
    @JoinColumn(name = "contract_sell_id", nullable = false)
    private ContractSell contractSell;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "price", nullable = false, columnDefinition = "float default 0")
    private Double price;

    @Column(name = "deposit", nullable = false, columnDefinition = "float default 0")
    private Double deposit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hand_over_date", nullable = false)
    private Date handOverDate;

    @ManyToMany
    @JoinTable(name = "contract_sell_buy_tax", joinColumns = @JoinColumn(name = "contract_sell_buy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tax_id", referencedColumnName = "id"))
    private Set<Tax> taxes;

    @OneToMany(mappedBy = "contractSellBuy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Insurance> insurances;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "progress", nullable = false, columnDefinition = "int default 1")
    private Integer progress;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    private Boolean deleted;

    @Column(name = "attachment", nullable = false, length = 1000)
    private String attachment;
}
