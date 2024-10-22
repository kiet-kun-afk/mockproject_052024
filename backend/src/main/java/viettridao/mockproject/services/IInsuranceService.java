package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.InsuranceDTO;

/**
 * IInsuranceService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
public interface IInsuranceService {

    List<InsuranceDTO> getAllInsurances();

    InsuranceDTO createInsurance(InsuranceDTO insuranceDTO) throws Exception;

    InsuranceDTO updateInsurance(InsuranceDTO insuranceDTO) throws Exception;

    void deleteInsurance(Long insuranceId) throws Exception;

    InsuranceDTO getInsuranceById(Long insuranceId) throws Exception;
}
