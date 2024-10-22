package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.models.Tax;

/**
 * ITaxService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
public interface ITaxService {

    Tax createTax(Tax tax) throws Exception;

    Tax updateTax(Tax tax) throws Exception;

    void deleteTax(Long taxId) throws Exception;

    Tax findTaxById(Long taxId) throws Exception;

    List<Tax> getAll();

    List<Tax> getAllByPropertyId(Long propertyId);
}