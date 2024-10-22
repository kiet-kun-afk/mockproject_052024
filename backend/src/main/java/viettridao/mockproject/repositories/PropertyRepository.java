package viettridao.mockproject.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import viettridao.mockproject.models.Property;

/**
 * PropertyRepository
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 kiet-kun-afk Create
 * 5/23/2024 kiet-kun-afk Update
 * 5/24/2024 kiet-kun-afk Update
 */
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

	List<Property> findByDeletedFalse();

	@Query("SELECT p FROM Property p JOIN p.location l JOIN p.propertyType t " +
			"WHERE p.description LIKE %:keyword% " +
			"OR p.legalStatus LIKE %:keyword% " +
			"OR t.name LIKE %:keyword% " +
			"OR l.street LIKE %:keyword% " +
			"OR l.state LIKE %:keyword% " +
			"OR l.city LIKE %:keyword% " +
			"OR l.zipCode LIKE :keyword " +
			"AND p.deleted = false")
	Page<Property> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT p FROM Property p WHERE p.owner.id = :ownerId AND p.deleted = false")
	List<Property> findByOwnerId(@Param("ownerId") Long ownerId);

	Property findByIdAndDeletedFalse(Long id);

	@Query("SELECT p FROM Property p WHERE p.deleted = false AND (:numberBedroom IS NULL OR :numberBedroom = 0 OR p.numberBedroom = :numberBedroom) AND (:numberBathroom IS NULL OR :numberBathroom = 0 OR p.numberBathroom = :numberBathroom) ")
	List<Property> findAllPropertiesByKeyword(@Param("numberBedroom") Integer numberBedroom,
			@Param("numberBathroom") Integer numberBathroom);

	Page<Property> findByDeletedFalse(Pageable pageable);
}
