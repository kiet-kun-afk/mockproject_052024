package viettridao.mockproject.services;

import viettridao.mockproject.dtos.contracts.ContractSellDTO;

import java.util.List;

/**
 * IContractSellService
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 diepit9x Create
 * 5/28/2024 kiet-kun-afk Update
 */
public interface IContractSellService {
    ContractSellDTO createContractSell(ContractSellDTO contractSellDTO) throws Exception;

    ContractSellDTO updateContractSell(ContractSellDTO contractSellDTO) throws Exception;

    ContractSellDTO getContractSellById(Long contractSellId) throws Exception;

    List<ContractSellDTO> getAllContractSells();

    void delete(Long id) throws Exception;

    void recover(Long id) throws Exception;
}
