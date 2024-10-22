package viettridao.mockproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import viettridao.mockproject.models.PasswordResetToken;;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
