package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.contracts.ContractBuyDTO;

public interface IContractBuyService {

    ContractBuyDTO createContractBuy(ContractBuyDTO contractBuyDTO) throws Exception;

    ContractBuyDTO updateContractBuy(ContractBuyDTO contractBuyDTO) throws Exception;

    void deleteContractBuy(Long contractBuyId) throws Exception;

    void recover(Long id) throws Exception;

    ContractBuyDTO getContractBuyById(Long contractBuyId) throws Exception;

    List<ContractBuyDTO> getAllContractBuys();
}
