package viettridao.mockproject.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.InspectorDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.exceptions.InvalidParamException;
import viettridao.mockproject.models.Inspector;
import viettridao.mockproject.repositories.InspectorRepository;
import viettridao.mockproject.services.IInspectorService;

/**
 * InspectorService
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class InspectorService implements IInspectorService {

    private final InspectorRepository inspectorRepository;

    /**
     * create a new inspector
     * 
     * @param inspectorDTO
     * @return inspectorDTO
     * @throws Exception
     */
    @Override
    public InspectorDTO createInspector(InspectorDTO inspectorDTO) throws Exception {
        if (inspectorRepository.findByPhoneNumber(inspectorDTO.getPhoneNumber()) != null) {
            throw new InvalidParamException("Phone number is existing");
        }

        Inspector inspector = Inspector.builder()
                .active(true)
                .company(inspectorDTO.getCompany())
                .firstName(inspectorDTO.getFirstName())
                .lastName(inspectorDTO.getLastName())
                .phoneNumber(inspectorDTO.getPhoneNumber())
                .build();

        inspectorRepository.save(inspector);
        return InspectorDTO.fromInspector(inspector);
    }

    public List<InspectorDTO> getExistInspectorExcludingCurrent(InspectorDTO inspector, String phoneNumber) {
        List<Inspector> inspectors = inspectorRepository.findAllByPhoneNumberAndIdNot(phoneNumber, inspector.getId());
        return inspectors.stream().map(InspectorDTO::fromInspector).toList();
    }

    @Override
    public InspectorDTO updateInspector(InspectorDTO inspectorDTO) throws Exception {
        if (!getExistInspectorExcludingCurrent(inspectorDTO, inspectorDTO.getPhoneNumber()).isEmpty()) {
            throw new InvalidParamException("Phone number is existing");
        }
        Inspector inspector = inspectorRepository.findById(inspectorDTO.getId())
                .orElseThrow(() -> new DataNotFoundException("Inspector not found"));

        inspector.setFirstName(inspectorDTO.getFirstName());
        inspector.setLastName(inspectorDTO.getLastName());
        inspector.setPhoneNumber(inspectorDTO.getPhoneNumber());
        inspector.setCompany(inspectorDTO.getCompany());
        inspector.setActive(inspectorDTO.getActive() == null ? inspector.getActive() : inspectorDTO.getActive());

        inspectorRepository.save(inspector);
        return InspectorDTO.fromInspector(inspector);
    }

    @Override
    public InspectorDTO getInspectorById(Long id) throws Exception {
        Inspector inspector = inspectorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Inspector not found"));

        return InspectorDTO.fromInspector(inspector);
    }

    @Override
    public InspectorDTO getInspectorByPhoneNumber(String phoneNumber) throws Exception {
        Inspector inspector = inspectorRepository.findByPhoneNumber(phoneNumber);
        if (inspector == null) {
            throw new InvalidParamException("Inspector not found");
        }

        return InspectorDTO.fromInspector(inspector);
    }

    @Override
    public InspectorDTO deleteInspector(Long id) throws Exception {
        Inspector inspector = inspectorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Inspector not found"));
        inspector.setActive(false);
        inspectorRepository.save(inspector);
        return InspectorDTO.fromInspector(inspector);
    }

    @Override
    public InspectorDTO recoverInspector(Long id) throws Exception {
        Inspector inspector = inspectorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Inspector not found"));
        inspector.setActive(true);
        inspectorRepository.save(inspector);
        return InspectorDTO.fromInspector(inspector);
    }

    @Override
    public List<InspectorDTO> getInspectorList() throws Exception {
        List<Inspector> inspectors = inspectorRepository.findAll();
        return inspectors.stream().map(InspectorDTO::fromInspector).toList();
    }

    @Override
    public List<InspectorDTO> getActiveInspectorList() throws Exception {
        List<Inspector> inspectors = inspectorRepository.findByActiveTrue();
        return inspectors.stream().map(InspectorDTO::fromInspector).toList();
    }

}
