package viettridao.mockproject.services;

import viettridao.mockproject.dtos.locations.LocationDTO;
import viettridao.mockproject.models.Location;

import java.util.List;

/**
 * ILocationService
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 diepit9x Create
 */
public interface ILocationService {
    Location addLocation(LocationDTO locationDTO) throws Exception;

    Location updateLocation(LocationDTO locationDTO) throws Exception;

    List<Location> getAllLocations(String keyword) throws Exception;

    Location getLocationById(Long locationId) throws Exception;

    void deleteLocationById(Long locationId) throws Exception;

    void recover(Long id) throws Exception;
}
