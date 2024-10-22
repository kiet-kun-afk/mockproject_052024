package viettridao.mockproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import viettridao.mockproject.models.Tax;

/**
 * TaxRepository
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
public interface TaxRepository extends JpaRepository<Tax, Long> {

}
