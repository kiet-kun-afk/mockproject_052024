package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.InspectionDTO;
import viettridao.mockproject.responses.InspectionResponse;

/**
 * IInspectionService
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
public interface IInspectionService {

    InspectionResponse createInspection(InspectionDTO inspectionDTO) throws Exception;

    List<InspectionResponse> getInspectionsByInspectorId(Long inspectorId) throws Exception;

    List<InspectionResponse> getInspectionsByPropertyId(Long propertyId) throws Exception;

    InspectionResponse getInspection(Long id) throws Exception;

    InspectionResponse updateInspection(InspectionDTO inspectionDTO) throws Exception;

    void deleteInspection(Long id) throws Exception;
}
