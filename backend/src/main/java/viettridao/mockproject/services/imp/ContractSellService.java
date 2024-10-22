package viettridao.mockproject.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viettridao.mockproject.dtos.contracts.ContractSellDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.ContractSell;
import viettridao.mockproject.models.Property;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.ContractSellRepository;
import viettridao.mockproject.repositories.PropertyRepository;
import viettridao.mockproject.services.IContractSellService;
import viettridao.mockproject.services.IFileService;
import viettridao.mockproject.services.IUserService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ContractSellService
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 diepit9x Create
 * 5/28/2024 kiet-kun-afk Update
 */
@Service
@RequiredArgsConstructor
public class ContractSellService implements IContractSellService {
	private final PropertyRepository propertyRepository;
	private final ContractSellRepository contractSellRepository;
	private final IFileService saveFileService;
	private final IUserService userService;

	@Override
	public ContractSellDTO createContractSell(ContractSellDTO contractSellDTO) throws Exception {
		// get user object from SecurityContextHolder
		User existingSeller = userService.getAuth("USER");

		Long propertyId = contractSellRepository.findPropertyId(contractSellDTO.getPropertyId());

		if (propertyId != null) {
			throw new DataNotFoundException("Already exist contract with property id " + propertyId);
		}

		Property existingProperty = propertyRepository.findByIdAndDeletedFalse(contractSellDTO.getPropertyId());

		if (existingProperty == null) {
			throw new DataNotFoundException("Property with id " + contractSellDTO.getPropertyId() + " not found");
		}

		ContractSell contract = ContractSell.builder()
				.seller(existingSeller)
				.property(existingProperty)
				.desiredPrice(contractSellDTO.getDesiredPrice() == null ? 0
						: contractSellDTO.getDesiredPrice())
				.deposit(contractSellDTO.getDeposit() == null ? 0 : contractSellDTO.getDeposit())
				.paymentMethod(contractSellDTO.getPaymentMethod() == null ? ""
						: contractSellDTO.getPaymentMethod())
				.brokerageFee(contractSellDTO.getBrokerageFee() == null ? 0
						: contractSellDTO.getBrokerageFee())
				.progress(contractSellDTO.getProgress() == null ? 1 : contractSellDTO.getProgress())
				.deleted(contractSellDTO.getDeleted() == null ? false : contractSellDTO.getDeleted())
				.attachment(saveFileService.saveAttachment(contractSellDTO.getAttachmentFile()))
				.approvedAt(LocalDateTime.now())
				.build();

		contractSellRepository.save(contract);
		return ContractSellDTO.fromContract(contract);
	}

	@Override
	public ContractSellDTO updateContractSell(ContractSellDTO contractSellDTO)
			throws Exception {
		ContractSell contract = contractSellRepository.findById(contractSellDTO.getId())
				.orElseThrow(() -> new DataNotFoundException(
						"Contract with id " + contractSellDTO.getId() + " not found"));
		contract.setDesiredPrice(contractSellDTO.getDesiredPrice() == null ? contract.getDesiredPrice()
				: contractSellDTO.getDesiredPrice());
		contract.setDeposit(contractSellDTO.getDeposit() == null ? contract.getDeposit()
				: contractSellDTO.getDeposit());
		contract.setPaymentMethod(contractSellDTO.getPaymentMethod() == null ? contract.getPaymentMethod()
				: contractSellDTO.getPaymentMethod());
		contract.setBrokerageFee(contractSellDTO.getBrokerageFee() == null ? contract.getBrokerageFee()
				: contractSellDTO.getBrokerageFee());
		contract.setProgress(contractSellDTO.getProgress() == null ? contract.getProgress()
				: contractSellDTO.getProgress());
		contract.setDeleted(contractSellDTO.getDeleted() == null ? contract.getDeleted()
				: contractSellDTO.getDeleted());
		contract.setAttachment(contractSellDTO.getAttachmentFile() == null ? contract.getAttachment()
				: saveFileService.saveAttachment(contractSellDTO.getAttachmentFile()));
		contractSellRepository.save(contract);
		return ContractSellDTO.fromContract(contract);
	}

	@Override
	public ContractSellDTO getContractSellById(Long contractSellId) throws Exception {
		ContractSell contract = contractSellRepository.findById(contractSellId)
				.orElseThrow(() -> new DataNotFoundException(
						"Contract with id " + contractSellId + " not found"));
		return ContractSellDTO.fromContract(contract);
	}

	@Override
	public List<ContractSellDTO> getAllContractSells() {
		List<ContractSell> contracts = contractSellRepository.findAll();
		return contracts.stream().map(ContractSellDTO::fromContract).toList();
	}

	@Override
	public void delete(Long id) throws Exception {
		ContractSell contractSell = contractSellRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Contract with id " + id + " not found"));
		contractSell.setDeleted(true);
		contractSellRepository.save(contractSell);
	}

	@Override
	public void recover(Long id) throws Exception {
		ContractSell contractSell = contractSellRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Contract with id " + id + " not found"));
		contractSell.setDeleted(false);
		contractSellRepository.save(contractSell);
	}
}
