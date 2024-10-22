package viettridao.mockproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import viettridao.mockproject.models.User;

import java.util.Optional;

/**
 * UserRepository
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 * 5/28/2024 kiet-kun-afk Update
 */
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByPhone(String phone);

    // @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u
    // WHERE u.id = :userId AND u.role.name = 'USER'")
    // boolean existsByUserIdAndRoleName(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.role.name = 'STAFF' AND u.id = :userId AND u.active = true")
    Optional<User> findStaffByUserIdAndActive(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.role.name = :role AND u.id = :userId AND u.active = true")
    Optional<User> findSellerOrBuyerByUserIdAndActive(@Param("role") String roleName, @Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.role.name = :role AND (u.username = :username OR u.email = :email) AND u.active = true")
    User findAuthentication(@Param("role") String roleName, @Param("username") String username,
            @Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.role.name = :role AND u.id = :userId AND u.active = true")
    User findCustomer(@Param("role") String roleName, @Param("userId") Long userId);

    Optional<User> findByUsernameOrEmailAndActiveTrue(String username, String email);
}
