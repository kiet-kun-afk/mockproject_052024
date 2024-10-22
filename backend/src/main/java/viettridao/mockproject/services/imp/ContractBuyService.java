package viettridao.mockproject.services.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.contracts.ContractBuyDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.ContractBuy;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.models.PropertyType;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.ContractBuyRepository;
import viettridao.mockproject.services.IContractBuyService;
import viettridao.mockproject.services.IFileService;
import viettridao.mockproject.services.IUserService;

/**
 * ContractBuyService
 * Version: 1.0
 * Date: 5/28/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/28/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class ContractBuyService implements IContractBuyService {

	private final ContractBuyRepository contractBuyRepository;
	private final PropertyTypeService propertyTypeService;
	private final LocationService locationService;
	private final IFileService saveFileService;
	private final FormatterService formatter;
	private final IUserService userService;

	@Override
	public ContractBuyDTO createContractBuy(ContractBuyDTO contractBuyDTO) throws Exception {
		// get user object from SecurityContextHolder
		User existingSeller = userService.getAuth("USER");

		PropertyType propertyType = propertyTypeService.getPropertyTypeById(contractBuyDTO.getPropertyTypeId());

		Location location = locationService.getLocationById(contractBuyDTO.getDesiredLocationId());

		ContractBuy contractBuy = ContractBuy.builder()
				.buyer(existingSeller)
				.propertyType(propertyType)
				.desiredLocation(location)
				.desiredArea(contractBuyDTO.getDesiredArea() == null ? 0
						: contractBuyDTO.getDesiredArea())
				.desiredPrice(contractBuyDTO.getDesiredPrice() == null ? 0
						: contractBuyDTO.getDesiredPrice())
				.otherRequests(contractBuyDTO.getOtherRequests())
				.brokerageFee(contractBuyDTO.getBrokerageFee() == null ? 0
						: contractBuyDTO.getBrokerageFee())
				.startDate(contractBuyDTO.getStartDate() == null ? LocalDateTime.now()
						: formatter.stringToDateTime(contractBuyDTO.getStartDate()))
				.endDate(contractBuyDTO.getEndDate() == null ? null
						: formatter.stringToDateTime(contractBuyDTO.getEndDate()))
				.progress(contractBuyDTO.getProgress() == null ? 0 : contractBuyDTO.getProgress())
				.deleted(contractBuyDTO.getDeleted() == null ? false : contractBuyDTO.getDeleted())
				.attachment(saveFileService.saveAttachment(contractBuyDTO.getAttachmentFile()))
				.build();

		return ContractBuyDTO.fromContract(contractBuyRepository.save(contractBuy));
	}

	@Override
	public ContractBuyDTO updateContractBuy(ContractBuyDTO contractBuyDTO) throws Exception {
		ContractBuy contractBuy = contractBuyRepository.findById(contractBuyDTO.getId())
				.orElseThrow(() -> new DataNotFoundException(
						"Not found contract with id " + contractBuyDTO.getId()));
		contractBuy.setPropertyType(
				propertyTypeService.getPropertyTypeById(contractBuyDTO.getPropertyTypeId()));
		contractBuy.setDesiredLocation(locationService.getLocationById(contractBuyDTO.getDesiredLocationId()));
		contractBuy.setOtherRequests(contractBuyDTO.getOtherRequests());
		contractBuy.setDesiredArea(contractBuyDTO.getDesiredArea() == null ? contractBuy.getDesiredArea()
				: contractBuyDTO.getDesiredArea());
		contractBuy.setDesiredPrice(contractBuyDTO.getDesiredPrice() == null ? contractBuy.getDesiredPrice()
				: contractBuyDTO.getDesiredPrice());
		contractBuy.setBrokerageFee(contractBuyDTO.getBrokerageFee() == null ? contractBuy.getBrokerageFee()
				: contractBuyDTO.getBrokerageFee());
		contractBuy.setStartDate(contractBuyDTO.getStartDate() == null ? contractBuy.getStartDate()
				: formatter.stringToDateTime(contractBuyDTO.getStartDate()));
		contractBuy.setEndDate(contractBuyDTO.getEndDate() == null ? contractBuy.getEndDate()
				: formatter.stringToDateTime(contractBuyDTO.getEndDate()));
		contractBuy.setProgress(contractBuyDTO.getProgress() == null ? contractBuy.getProgress()
				: contractBuyDTO.getProgress());
		contractBuy.setDeleted(contractBuyDTO.getDeleted() == null ? contractBuy.getDeleted()
				: contractBuyDTO.getDeleted());
		contractBuy.setAttachment(saveFileService.saveAttachment(contractBuyDTO.getAttachmentFile()));
		return ContractBuyDTO.fromContract(contractBuy);
	}

	@Override
	public void deleteContractBuy(Long contractBuyId) throws Exception {
		ContractBuy contractBuy = contractBuyRepository.findById(contractBuyId).orElseThrow(
				() -> new DataNotFoundException("Not found contract with id " + contractBuyId));
		contractBuy.setDeleted(true);
		contractBuyRepository.save(contractBuy);
	}

	@Override
	public ContractBuyDTO getContractBuyById(Long contractBuyId) throws Exception {
		return ContractBuyDTO.fromContract(contractBuyRepository.findById(contractBuyId).orElseThrow(
				() -> new DataNotFoundException("Not found contract with id " + contractBuyId)));
	}

	@Override
	public List<ContractBuyDTO> getAllContractBuys() {
		List<ContractBuy> contractBuys = contractBuyRepository.findAll();
		return contractBuys.stream().map(ContractBuyDTO::fromContract).toList();
	}

	@Override
	public void recover(Long id) throws Exception {
		ContractBuy contractBuy = contractBuyRepository.findById(id).orElseThrow(
				() -> new DataNotFoundException("Not found contract with id " + id));
		contractBuy.setDeleted(false);
		contractBuyRepository.save(contractBuy);
	}

}
