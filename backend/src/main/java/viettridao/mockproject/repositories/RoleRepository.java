package viettridao.mockproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import viettridao.mockproject.models.Role;

/**
 * RoleRepository
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
