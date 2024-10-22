package viettridao.mockproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import viettridao.mockproject.models.Photo;

/**
 * PhotoRepository
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 kiet-kun-afk Create
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Photo p WHERE p.property.id = :propertyId")
    void deleteByPropertyId(@Param("propertyId") Long propertyId);
}
