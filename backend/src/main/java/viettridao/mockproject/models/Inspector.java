package viettridao.mockproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Inspector
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 */
@Entity
@Table(name = "inspectors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inspector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "phone_number", length = 50, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "company", length = 50)
    private String company;

    @Column(name = "isActive", nullable = false, columnDefinition = "bit default 1")
    private Boolean active;
}
