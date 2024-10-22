package viettridao.mockproject.services.imp;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.contracts.ContractSellBuyDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.ContractBuy;
import viettridao.mockproject.models.ContractSell;
import viettridao.mockproject.models.ContractSellBuy;
import viettridao.mockproject.models.Insurance;
import viettridao.mockproject.models.Property;
import viettridao.mockproject.models.Tax;
import viettridao.mockproject.repositories.ContractBuyRepository;
import viettridao.mockproject.repositories.ContractSellBuyRepository;
import viettridao.mockproject.repositories.ContractSellRepository;
import viettridao.mockproject.repositories.PropertyRepository;
import viettridao.mockproject.services.IContractSellBuyService;
import viettridao.mockproject.services.IFileService;

/**
 * ContractSellBuyService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class ContractSellBuyService implements IContractSellBuyService {

	private final ContractSellBuyRepository contractSellBuyRepository;
	private final ContractBuyRepository contractBuyRepository;
	private final ContractSellRepository contractSellRepository;
	private final PropertyRepository propertyRepository;
	private final IFileService saveFileService;

	@Override
	public List<ContractSellBuyDTO> getAll() throws Exception {
		return contractSellBuyRepository.findAll().stream().map(ContractSellBuyDTO::fromContract).toList();
	}

	/*
	 * calculate the price
	 */
	public Double getPrice(ContractSellBuyDTO sellBuyDTO, ContractSell property) throws Exception {
		Set<Tax> taxSet = sellBuyDTO.getTaxes();
		List<Insurance> insuranceList = sellBuyDTO.getInsurances();
		Double taxes = 0.0;
		Double insurances = 0.0;
		if (taxSet != null) {

			for (Tax tax : taxSet) {
				taxes += tax.getAmount();
			}
		}
		if (insuranceList != null) {

			for (Insurance insurance : insuranceList) {
				insurances += insurance.getPrice();
			}
		}

		return property.getDesiredPrice() + taxes + insurances;
	}

	@Override
	public ContractSellBuyDTO createContractSellBuy(ContractSellBuyDTO sellBuyDTO) throws Exception {
		ContractBuy contractBuy = contractBuyRepository.findById(sellBuyDTO.getContractBuyId())
				.orElseThrow(() -> new DataNotFoundException("Contract buy not found"));
		ContractSell contractSell = contractSellRepository.findById(sellBuyDTO.getContractSellId())
				.orElseThrow(() -> new DataNotFoundException("Contract sell not found"));
		Property property = propertyRepository.findById(sellBuyDTO.getPropertyId())
				.orElseThrow(() -> new DataNotFoundException("Property not found"));

		if (contractSell.getProperty().getId() != property.getId()) {
			throw new DataNotFoundException("Property is not belong to contract sell");
		}

		Double price = getPrice(sellBuyDTO, contractSell);

		ContractSellBuy contractSellBuy = ContractSellBuy.builder()
				.contractBuy(contractBuy)
				.contractSell(contractSell)
				.property(property)
				.taxes(sellBuyDTO.getTaxes())
				.price(price)
				.deposit(sellBuyDTO.getDeposit() == null ? 0 : sellBuyDTO.getDeposit())
				.paymentDate(sellBuyDTO.getPaymentDate() == null ? new Date()
						: sellBuyDTO.getPaymentDate())
				.handOverDate(sellBuyDTO.getHandOverDate() == null ? new Date()
						: sellBuyDTO.getHandOverDate())
				.createDate(sellBuyDTO.getCreateDate() == null ? new Date()
						: sellBuyDTO.getCreateDate())
				.progress(sellBuyDTO.getProgress() == null ? 1 : sellBuyDTO.getProgress())
				.deleted(sellBuyDTO.getDeleted() == null ? false : sellBuyDTO.getDeleted())
				.attachment(saveFileService.saveAttachment(sellBuyDTO.getAttachmentFile()))
				.build();
		return ContractSellBuyDTO.fromContract(contractSellBuyRepository.save(contractSellBuy));
	}

	@Override
	public ContractSellBuyDTO updateContractSellBuy(ContractSellBuyDTO sellBuyDTO) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository.findById(sellBuyDTO.getId())
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		Property property = propertyRepository.findById(sellBuyDTO.getPropertyId())
				.orElseThrow(() -> new DataNotFoundException("Property not found"));
		ContractBuy contractBuy = contractBuyRepository.findById(sellBuyDTO.getContractBuyId())
				.orElseThrow(() -> new DataNotFoundException("Contract buy not found"));
		ContractSell contractSell = contractSellRepository.findById(sellBuyDTO.getContractSellId())
				.orElseThrow(() -> new DataNotFoundException("Contract sell not found"));

		Double price = getPrice(sellBuyDTO, contractSell);
		contractSellBuy.setPrice(price);
		contractSellBuy.setContractBuy(contractBuy);
		contractSellBuy.setContractSell(contractSell);
		contractSellBuy.setProperty(property);
		contractSellBuy.setTaxes(sellBuyDTO.getTaxes() == null ? contractSellBuy.getTaxes()
				: sellBuyDTO.getTaxes());
		contractSellBuy.setDeposit(sellBuyDTO.getDeposit() == null ? contractSellBuy.getDeposit()
				: sellBuyDTO.getDeposit());
		contractSellBuy.setPaymentDate(sellBuyDTO.getPaymentDate() == null ? contractSellBuy.getPaymentDate()
				: sellBuyDTO.getPaymentDate());
		contractSellBuy.setHandOverDate(sellBuyDTO.getHandOverDate() == null ? contractSellBuy.getHandOverDate()
				: sellBuyDTO.getHandOverDate());
		contractSellBuy.setCreateDate(sellBuyDTO.getCreateDate() == null ? contractSellBuy.getCreateDate()
				: sellBuyDTO.getCreateDate());
		contractSellBuy.setProgress(sellBuyDTO.getProgress() == null ? contractSellBuy.getProgress()
				: sellBuyDTO.getProgress());
		contractSellBuy.setDeleted(sellBuyDTO.getDeleted() == null ? contractSellBuy.getDeleted()
				: sellBuyDTO.getDeleted());
		contractSellBuy.setAttachment(sellBuyDTO.getAttachment() == null ? contractSellBuy.getAttachment()
				: saveFileService.saveAttachment(sellBuyDTO.getAttachmentFile()));

		return ContractSellBuyDTO.fromContract(contractSellBuyRepository.save(contractSellBuy));
	}

	@Override
	public void deleteContractSellBuy(Long id) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		contractSellBuy.setDeleted(true);
		contractSellBuyRepository.save(contractSellBuy);
	}

	@Override
	public ContractSellBuyDTO getContractSellBuyById(Long id) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		return ContractSellBuyDTO.fromContract(contractSellBuy);
	}

	@Override
	public ContractSellBuyDTO getContractSellBuyByPropertyId(Long propertyId) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository.getOneByPropertyId(propertyId)
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		return ContractSellBuyDTO.fromContract(contractSellBuy);
	}

	@Override
	public List<ContractSellBuyDTO> getContractSellBuyByUserId(Long userId) throws Exception {
		List<ContractSellBuy> contractSellBuy = contractSellBuyRepository.getAllByUserId(userId);
		return contractSellBuy.stream().map(ContractSellBuyDTO::fromContract).toList();
	}

	@Override
	public void recover(Long id) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		contractSellBuy.setDeleted(false);
		contractSellBuyRepository.save(contractSellBuy);
	}

}
