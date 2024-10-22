package viettridao.mockproject.services;

import viettridao.mockproject.dtos.properties.PropertyTypeDTO;
import viettridao.mockproject.models.PropertyType;

import java.util.List;

/**
 * IPropertyTypeService
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
public interface IPropertyTypeService {
    PropertyType createPropertyType(PropertyTypeDTO propertyTypeDTO) throws Exception;

    PropertyType updatePropertyType(Long propertyTypeId, PropertyTypeDTO propertyTypeDTO) throws Exception;

    PropertyType getPropertyTypeById(Long propertyTypeId) throws Exception;

    List<PropertyType> getAllPropertyTypes();

    void deletePropertyType(Long propertyTypeId) throws Exception;

    void recover(Long id) throws Exception;
}
