package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.contracts.ContractSellBuyDTO;

/**
 * IContractSellBuyService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
public interface IContractSellBuyService {

    List<ContractSellBuyDTO> getAll() throws Exception;

    ContractSellBuyDTO createContractSellBuy(ContractSellBuyDTO sellBuyDTO) throws Exception;

    ContractSellBuyDTO updateContractSellBuy(ContractSellBuyDTO sellBuyDTO) throws Exception;

    void deleteContractSellBuy(Long id) throws Exception;

    void recover(Long id) throws Exception;

    ContractSellBuyDTO getContractSellBuyById(Long id) throws Exception;

    ContractSellBuyDTO getContractSellBuyByPropertyId(Long propertyId) throws Exception;

    List<ContractSellBuyDTO> getContractSellBuyByUserId(Long userId) throws Exception;
}
