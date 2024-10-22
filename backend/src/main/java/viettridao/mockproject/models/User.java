package viettridao.mockproject.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

/**
 * User
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(unique = true, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "job_description")
    private String jobDescription;

    @OneToMany(mappedBy = "viewer")
    private List<RequestTour> requestTours;
}
