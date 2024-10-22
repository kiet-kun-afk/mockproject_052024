package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.InspectorDTO;

/**
 * IInspectorService
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
public interface IInspectorService {

    InspectorDTO createInspector(InspectorDTO inspectorDTO) throws Exception;

    InspectorDTO updateInspector(InspectorDTO inspectorDTO) throws Exception;

    InspectorDTO getInspectorById(Long id) throws Exception;

    InspectorDTO getInspectorByPhoneNumber(String phoneNumber) throws Exception;

    InspectorDTO deleteInspector(Long id) throws Exception;

    InspectorDTO recoverInspector(Long id) throws Exception;

    List<InspectorDTO> getInspectorList() throws Exception;

    List<InspectorDTO> getActiveInspectorList() throws Exception;
}
