package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.RequestTourDTO;

/**
 * IRequestTourService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
public interface IRequestTourService {

    RequestTourDTO createNewRequestTour(RequestTourDTO request) throws Exception;

    RequestTourDTO getRequestTour(Long id) throws Exception;

    RequestTourDTO updateRequestTour(RequestTourDTO request) throws Exception;

    void deleteRequestTour(Long id) throws Exception;

    void recover(Long id) throws Exception;

    List<RequestTourDTO> getAll();
}
