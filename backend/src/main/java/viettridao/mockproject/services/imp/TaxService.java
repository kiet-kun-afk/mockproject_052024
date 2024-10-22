package viettridao.mockproject.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.Tax;
import viettridao.mockproject.repositories.TaxRepository;
import viettridao.mockproject.services.ITaxService;

/**
 * TaxService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class TaxService implements ITaxService {

    private final TaxRepository taxRepository;

    @Override
    public Tax createTax(Tax tax) throws Exception {
        Tax newTax = new Tax();
        newTax.setName(tax.getName());
        newTax.setAmount(tax.getAmount());
        return taxRepository.save(newTax);
    }

    @Override
    public Tax updateTax(Tax tax) throws Exception {
        Tax updateTax = taxRepository.findById(tax.getId())
                .orElseThrow(() -> new DataNotFoundException("Tax not found"));
        updateTax.setName(tax.getName() == null ? updateTax.getName() : tax.getName());
        updateTax.setAmount(tax.getAmount() == null ? updateTax.getAmount() : tax.getAmount());
        return taxRepository.save(updateTax);
    }

    @Override
    public void deleteTax(Long taxId) throws Exception {
        Tax deleteTax = taxRepository.findById(taxId).orElseThrow(() -> new DataNotFoundException("Tax not found"));
        taxRepository.delete(deleteTax);
    }

    @Override
    public Tax findTaxById(Long taxId) throws Exception {
        Tax deleteTax = taxRepository.findById(taxId).orElseThrow(() -> new DataNotFoundException("Tax not found"));
        return deleteTax;
    }

    @Override
    public List<Tax> getAll() {
        return taxRepository.findAll();
    }

    @Override
    public List<Tax> getAllByPropertyId(Long propertyId) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllByPropertyId'");
    }

}
