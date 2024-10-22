package viettridao.mockproject.services;

import java.util.List;

import org.springframework.data.domain.Page;
import viettridao.mockproject.dtos.properties.PropertyDTO;
import viettridao.mockproject.responses.PropertyResponse;

/**
 * IPropertyService
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kiet-kun-afk Create
 * 5/23/2024 kiet-kun-afk Update
 * 5/24/2024 kiet-kun-afk Update
 */
public interface IPropertyService {

	List<PropertyResponse> getProperties();

	List<PropertyResponse> getPropertiesNotDeleted();

	PropertyResponse getPropertyById(Long id) throws Exception;

	PropertyResponse createProperty(PropertyDTO propertyDTO) throws Exception;

	PropertyResponse updateProperty(PropertyDTO propertyDTO) throws Exception;

	void deleteProperty(Long id) throws Exception;

	void recover(Long id) throws Exception;

	List<PropertyResponse> getPropertiesByOwnerId(Long id) throws Exception;

	Page<PropertyResponse> getPropertiesBySearch(String keyword, Integer pageNumber, Integer pageSize) throws Exception;

	List<PropertyResponse> getPropertiesBySearch(
			Integer numberBedroom, Integer numberBathroom,
			Double price, String sortBy) throws Exception;

	Page<PropertyResponse> findAllPropertiesNotDeleted(Integer pageNumber, Integer pageSize);

	Page<PropertyResponse> findAll(Integer pageNumber, Integer pageSize);

	Page<PropertyResponse> getPropertiesBySearch(
			Integer numberBedroom, Integer numberBathroom,
			Double price, String sortBy,
			Integer pageNumber, Integer pageSize) throws Exception;
}
