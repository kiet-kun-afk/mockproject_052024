package viettridao.mockproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import viettridao.mockproject.models.PropertyType;

import java.util.List;

/**
 * PropertyTypeRepository
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {

    boolean existsByName(String name);

    List<PropertyType> findAllByDeleted(boolean b);

    PropertyType findByIdAndDeletedFalse(Long id);
}
