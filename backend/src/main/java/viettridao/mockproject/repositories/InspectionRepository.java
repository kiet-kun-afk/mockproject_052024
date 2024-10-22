package viettridao.mockproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viettridao.mockproject.models.Inspection;

/**
 * InspectionRepository
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    List<Inspection> findByPropertyId(Long propertyId);

    List<Inspection> findByInspectorId(Long propertyId);
}
