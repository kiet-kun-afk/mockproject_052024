package viettridao.mockproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import viettridao.mockproject.models.ContractSellBuy;

/**
 * ContractSellBuyRepository
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
public interface ContractSellBuyRepository extends JpaRepository<ContractSellBuy, Long> {

    @Query("SELECT c FROM ContractSellBuy c WHERE c.contractSell.property.id = :propertyId")
    Optional<ContractSellBuy> getOneByPropertyId(@Param("propertyId") Long propertyId);

    @Query("SELECT c FROM ContractSellBuy c WHERE c.contractBuy.buyer.id = :userId")
    List<ContractSellBuy> getAllByUserId(@Param("userId") Long userId);
}
