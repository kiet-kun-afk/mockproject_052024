package viettridao.mockproject.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viettridao.mockproject.dtos.locations.LocationDTO;
import viettridao.mockproject.exceptions.InvalidParamException;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.repositories.LocationRepository;
import viettridao.mockproject.services.ILocationService;

import java.util.List;

/**
 * LocationService
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 diepit9x Create
 */
@Service
@RequiredArgsConstructor
public class LocationService implements ILocationService {
    private final LocationRepository locationRepository;

    /**
     * insert data for location
     * 
     * @param locationDTO
     * @return Location
     * @throws Exception if Localtion already exists
     */
    @Override
    public Location addLocation(LocationDTO locationDTO) throws Exception {
        if (locationRepository.existsByZipCode(locationDTO.getZipCode())) {
            throw new InvalidParamException("Zipcode already exists");
        }
        if (locationRepository.existsByStreetAndCityAndStateAndCountry(
                locationDTO.getStreet(),
                locationDTO.getCity(),
                locationDTO.getState(),
                locationDTO.getCountry())) {
            throw new InvalidParamException("This localtion already exists");
        }

        Location newLocation = Location.builder()
                .street(locationDTO.getStreet())
                .city(locationDTO.getCity())
                .state(locationDTO.getState())
                .zipCode(locationDTO.getZipCode())
                .country(locationDTO.getCountry())
                .deleted(false)
                .build();
        return locationRepository.save(newLocation);
    }

    /**
     * update location data
     * 
     * @param locationId
     * @param locationDTO
     * @return
     * @throws Exception
     */
    @Override
    public Location updateLocation(LocationDTO locationDTO) throws Exception {
        Location existingLocation = getLocationById(locationDTO.getId());
        if (!existingLocation.getZipCode().equals(locationDTO.getZipCode()) &&
                locationRepository.existsByZipCode(locationDTO.getZipCode())) {
            throw new InvalidParamException("Zipcode already exists");
        }
        if (!existingLocation.getZipCode().equals(locationDTO.getZipCode()) &&
                locationRepository.existsByStreetAndCityAndStateAndCountry(
                        locationDTO.getStreet(),
                        locationDTO.getCity(),
                        locationDTO.getState(),
                        locationDTO.getCountry())) {
            throw new InvalidParamException("This localtion already exists");
        }
        existingLocation.setStreet(locationDTO.getStreet());
        existingLocation.setCity(locationDTO.getCity());
        existingLocation.setState(locationDTO.getState());
        existingLocation.setZipCode(locationDTO.getZipCode());
        existingLocation.setCountry(locationDTO.getCountry());
        existingLocation.setDeleted(locationDTO.getDeleted());

        return locationRepository.save(existingLocation);
    }

    /**
     * get all location by keyword
     * 
     * @param keyword
     * @return
     */
    @Override
    public List<Location> getAllLocations(String keyword) {
        return locationRepository.findAllLocationsByKeyword(keyword);
    }

    /**
     * get a location by locationId
     * 
     * @param locationId
     * @return
     * @throws Exception
     */
    @Override
    public Location getLocationById(Long locationId) throws Exception {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new InvalidParamException("This location does not exist"));
    }

    /**
     * Soft delete a location by id
     * 
     * @param locationId
     * @throws Exception
     */
    @Override
    public void deleteLocationById(Long locationId) throws Exception {
        Location location = getLocationById(locationId);
        location.setDeleted(true);
        locationRepository.save(location);
    }

    @Override
    public void recover(Long id) throws Exception {
        Location location = getLocationById(id);
        location.setDeleted(false);
        locationRepository.save(location);
    }
}
