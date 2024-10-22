package viettridao.mockproject.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Insurance
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurances")
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_sell_buy_id")
    @JsonBackReference
    private ContractSellBuy contractSellBuy;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "float default 0")
    private Double price;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "lien", nullable = false, columnDefinition = "bit default 0")
    private Boolean lien;

    @Column(name = "deed", nullable = false, columnDefinition = "bit default 0")
    private Boolean deed;
}
