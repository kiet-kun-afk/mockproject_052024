package viettridao.mockproject.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.InspectionDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.Inspection;
import viettridao.mockproject.models.Inspector;
import viettridao.mockproject.models.Property;
import viettridao.mockproject.repositories.InspectionRepository;
import viettridao.mockproject.repositories.InspectorRepository;
import viettridao.mockproject.repositories.PropertyRepository;
import viettridao.mockproject.responses.InspectionResponse;
import viettridao.mockproject.services.IInspectionService;

/**
 * InspectionService
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class InspectionService implements IInspectionService {

	private final InspectionRepository inspectionRepository;
	private final InspectorRepository inspectorRepository;
	private final PropertyRepository propertyRepository;

	@Override
	public InspectionResponse createInspection(InspectionDTO inspectionDTO) throws Exception {

		Property property = propertyRepository.findByIdAndDeletedFalse(inspectionDTO.getPropertyId());

		Inspector inspector = inspectorRepository.findByIdAndActiveTrue(inspectionDTO.getPropertyId());

		if (property == null || inspector == null) {
			throw new DataNotFoundException("Property or Inspector not found");
		}

		Inspection inspection = Inspection.builder()
				.content(inspectionDTO.getContent())
				.property(property)
				.inspector(inspector)
				.build();

		inspectionRepository.save(inspection);
		return InspectionResponse.fromInspection(inspection);
	}

	@Override
	public List<InspectionResponse> getInspectionsByInspectorId(Long inspectorId) throws Exception {
		List<Inspection> inspections = inspectionRepository.findByInspectorId(inspectorId);
		return inspections.stream().map(InspectionResponse::fromInspection).toList();
	}

	@Override
	public List<InspectionResponse> getInspectionsByPropertyId(Long propertyId) throws Exception {
		List<Inspection> inspections = inspectionRepository.findByPropertyId(propertyId);
		return inspections.stream().map(InspectionResponse::fromInspection).toList();
	}

	@Override
	public InspectionResponse getInspection(Long id) throws Exception {
		Inspection inspection = inspectionRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Inspection not found"));
		return InspectionResponse.fromInspection(inspection);
	}

	@Override
	public InspectionResponse updateInspection(InspectionDTO inspectionDTO) throws Exception {
		Property property = propertyRepository.findByIdAndDeletedFalse(inspectionDTO.getPropertyId());

		Inspector inspector = inspectorRepository.findByIdAndActiveTrue(inspectionDTO.getPropertyId());

		Inspection inspection = inspectionRepository.findById(inspectionDTO.getId())
				.orElseThrow(() -> new DataNotFoundException(
						"Inspection not found or already deleted"));

		inspection.setContent(inspectionDTO.getContent());
		inspection.setProperty(inspectionDTO.getPropertyId() == null ? inspection.getProperty() : property);
		inspection.setInspector(inspectionDTO.getInspectorId() == null ? inspection.getInspector() : inspector);

		inspectionRepository.save(inspection);
		return InspectionResponse.fromInspection(inspection);
	}

	@Override
	public void deleteInspection(Long id) throws Exception {
		Inspection inspection = inspectionRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException(
						"Inspection not found or already deleted"));
		inspectionRepository.delete(inspection);
	}
}
