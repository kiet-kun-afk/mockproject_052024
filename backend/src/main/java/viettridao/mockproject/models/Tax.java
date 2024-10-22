package viettridao.mockproject.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Tax
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 */
@Entity
@Table(name = "taxes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "amount", nullable = false, columnDefinition = "float default 0")
    private Double amount;

    @ManyToMany(mappedBy = "taxes")
    Set<ContractSellBuy> contractSellBuys;
}
