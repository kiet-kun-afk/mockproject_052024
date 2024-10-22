package viettridao.mockproject.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viettridao.mockproject.dtos.properties.PropertyTypeDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.exceptions.InvalidParamException;
import viettridao.mockproject.models.PropertyType;
import viettridao.mockproject.repositories.PropertyTypeRepository;
import viettridao.mockproject.services.IPropertyTypeService;

import java.util.List;

/**
 * PropertyTypeService
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
@Service
@RequiredArgsConstructor
public class PropertyTypeService implements IPropertyTypeService {
    private final PropertyTypeRepository propertyTypeRepository;

    /**
     * Insert a new property type into property_types table
     * 
     * @param propertyTypeDTO
     * @return
     * @throws Exception
     */
    @Override
    public PropertyType createPropertyType(PropertyTypeDTO propertyTypeDTO) throws Exception {
        if (propertyTypeRepository.existsByName(propertyTypeDTO.getName())) {
            throw new InvalidParamException("Name already exists");
        }

        PropertyType propertyType = new PropertyType(null, propertyTypeDTO.getName(), false);

        return propertyTypeRepository.save(propertyType);
    }

    /**
     * Update a property type
     * 
     * @param propertyTypeId
     * @param propertyTypeDTO
     * @return
     * @throws Exception
     */
    @Override
    public PropertyType updatePropertyType(Long propertyTypeId, PropertyTypeDTO propertyTypeDTO) throws Exception {
        PropertyType existingPropertyType = getPropertyTypeById(propertyTypeId);
        if (!propertyTypeDTO.getName().equals(existingPropertyType.getName()) &&
                propertyTypeRepository.existsByName(propertyTypeDTO.getName())) {
            throw new InvalidParamException("PropertyType's name already exists");
        }
        existingPropertyType.setName(propertyTypeDTO.getName());
        existingPropertyType.setDeleted(propertyTypeDTO.getDeleted());
        return propertyTypeRepository.save(existingPropertyType);
    }

    /**
     * Get a property type by id
     * 
     * @param propertyTypeId
     * @return
     * @throws Exception
     */
    @Override
    public PropertyType getPropertyTypeById(Long propertyTypeId) throws Exception {
        return propertyTypeRepository.findById(propertyTypeId)
                .orElseThrow(() -> new DataNotFoundException("PropertyType with id " + propertyTypeId + " not found"));
    }

    /**
     * Get all property types
     * 
     * @return
     */
    @Override
    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeRepository.findAllByDeleted(false);
    }

    /**
     * Soft delete a property type by id
     * 
     * @param propertyTypeId
     * @throws Exception
     */
    @Override
    public void deletePropertyType(Long propertyTypeId) throws Exception {
        PropertyType propertyType = getPropertyTypeById(propertyTypeId);
        propertyType.setDeleted(true);
        propertyTypeRepository.save(propertyType);
    }

    @Override
    public void recover(Long id) throws Exception {
        PropertyType propertyType = getPropertyTypeById(id);
        propertyType.setDeleted(false);
        propertyTypeRepository.save(propertyType);
    }
}
