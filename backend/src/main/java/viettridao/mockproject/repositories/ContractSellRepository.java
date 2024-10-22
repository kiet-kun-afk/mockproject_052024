package viettridao.mockproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import viettridao.mockproject.models.ContractSell;

/**
 * ContractSellService
 * Version: 1.0
 * Date: 5/28/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/28/2024 kiet-kun-afk Update
 */
public interface ContractSellRepository extends JpaRepository<ContractSell, Long> {

    @Query("SELECT c.property.id FROM ContractSell c WHERE c.property.id = :propertyId")
    Long findPropertyId(@Param("propertyId") Long propertyId);

    Optional<ContractSell> findBySellerId(Long sellerId);

    Optional<ContractSell> findByStaffId(Long staffId);
}