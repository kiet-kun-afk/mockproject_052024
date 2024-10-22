package viettridao.mockproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import viettridao.mockproject.models.Location;

import java.util.List;

/**
 * LocationRepository
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 diepit9x Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByZipCode(String zipCode);

    @Query("SELECT l FROM Location l WHERE l.deleted = false AND (:keyword IS NULL OR :keyword = '' " +
            "OR CONCAT(l.street, ', ', l.city, ', ', l.state, ', ', l.zipCode, ', ', l.country) LIKE %:keyword%)")
    List<Location> findAllLocationsByKeyword(@Param("keyword") String keyword);

    boolean existsByStreetAndCityAndStateAndCountry(String street, String city, String state, String country);

    Location findByIdAndDeletedFalse(Long id);
}
