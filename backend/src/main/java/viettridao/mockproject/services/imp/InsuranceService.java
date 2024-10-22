package viettridao.mockproject.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.InsuranceDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.ContractSellBuy;
import viettridao.mockproject.models.Insurance;
import viettridao.mockproject.repositories.ContractSellBuyRepository;
import viettridao.mockproject.repositories.InsuranceRepository;
import viettridao.mockproject.services.IInsuranceService;

/**
 * InsuranceService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class InsuranceService implements IInsuranceService {

	private final InsuranceRepository insuranceRepository;
	private final ContractSellBuyRepository contractSellBuyRepository;
	private final FormatterService formatter;

	@Override
	public List<InsuranceDTO> getAllInsurances() {
		List<Insurance> insuranceList = insuranceRepository.findAll();
		return insuranceList.stream().map(InsuranceDTO::fromInsurance).toList();
	}

	@Override
	public InsuranceDTO createInsurance(InsuranceDTO insuranceDTO) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository
				.findById(insuranceDTO.getContractSellBuyId())
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		Insurance insurance = Insurance.builder()
				.contractSellBuy(contractSellBuy)
				.name(insuranceDTO.getName() == null ? "" : insuranceDTO.getName())
				.price(insuranceDTO.getPrice() == null ? 0.0 : insuranceDTO.getPrice())
				.lien(insuranceDTO.getLien() == null ? false : insuranceDTO.getLien())
				.deed(insuranceDTO.getDeed() == null ? false : insuranceDTO.getDeed())
				.expirationDate(formatter.stringToDateTime(insuranceDTO.getExpirationDate()))
				.build();
		insuranceRepository.save(insurance);
		return InsuranceDTO.fromInsurance(insurance);
	}

	@Override
	public InsuranceDTO updateInsurance(InsuranceDTO insuranceDTO) throws Exception {
		ContractSellBuy contractSellBuy = contractSellBuyRepository
				.findById(insuranceDTO.getContractSellBuyId())
				.orElseThrow(() -> new DataNotFoundException("Contract not found"));
		Insurance insurance = insuranceRepository.findById(insuranceDTO.getId())
				.orElseThrow(() -> new DataNotFoundException("Insurance not found"));
		insurance.setContractSellBuy(contractSellBuy);
		insurance.setName(insuranceDTO.getName() == null ? insurance.getName() : insuranceDTO.getName());
		insurance.setPrice(insuranceDTO.getPrice() == null ? insurance.getPrice() : insuranceDTO.getPrice());
		insurance.setLien(insuranceDTO.getLien() == null ? insurance.getLien() : insuranceDTO.getLien());
		insurance.setDeed(insuranceDTO.getDeed() == null ? insurance.getDeed() : insuranceDTO.getDeed());
		insurance.setExpirationDate(insuranceDTO.getExpirationDate() == null ? insurance.getExpirationDate()
				: formatter.stringToDateTime(insuranceDTO.getExpirationDate()));
		return InsuranceDTO.fromInsurance(insuranceRepository.save(insurance));
	}

	@Override
	public void deleteInsurance(Long insuranceId) throws Exception {
		Insurance insurance = insuranceRepository.findById(insuranceId)
				.orElseThrow(() -> new DataNotFoundException("Insurance not found"));
		insuranceRepository.delete(insurance);
	}

	@Override
	public InsuranceDTO getInsuranceById(Long insuranceId) throws Exception {
		Insurance insurance = insuranceRepository.findById(insuranceId)
				.orElseThrow(() -> new DataNotFoundException("Insurance not found"));

		return InsuranceDTO.fromInsurance(insurance);
	}

}
