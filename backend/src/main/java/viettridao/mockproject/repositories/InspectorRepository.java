package viettridao.mockproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viettridao.mockproject.models.Inspector;

/**
 * InspectorRepository
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
public interface InspectorRepository extends JpaRepository<Inspector, Long> {

    List<Inspector> findByActiveTrue();

    Inspector findByPhoneNumber(String phoneNumber);

    List<Inspector> findAllByPhoneNumberAndIdNot(String phoneNumber, Long id);

    Inspector findByIdAndActiveTrue(Long id);
}
