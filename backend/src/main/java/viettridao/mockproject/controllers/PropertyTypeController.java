package viettridao.mockproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import viettridao.mockproject.dtos.properties.PropertyTypeDTO;
import viettridao.mockproject.models.PropertyType;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IPropertyTypeService;

import java.util.List;

/**
 * PropertyTypeController
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
@RestController
@RequestMapping("${api.prefix}/property-types")
@RequiredArgsConstructor
public class PropertyTypeController {
	private final IPropertyTypeService propertyTypeService;

	/**
	 * Create a new property type
	 * 
	 * @param propertyTypeDTO
	 * @param bindingResult
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public ResponseEntity<ResponseObject> createPropertyType(
			@Valid @ModelAttribute PropertyTypeDTO propertyTypeDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();
				return ResponseEntity.badRequest().body(
						ResponseObject.builder()
								.status(400)
								.message("Create a new property type Failed")
								.data(errors)
								.build());
			}
			PropertyType newPropertyType = propertyTypeService.createPropertyType(propertyTypeDTO);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Create a new PropertyType successfully")
							.data(newPropertyType)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Create property type Failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * Update a property type
	 * 
	 * @param propertyTypeId
	 * @param propertyTypeDTO
	 * @param bindingResult
	 * @return
	 */
	@PutMapping("")
	public ResponseEntity<ResponseObject> updatePropertyType(
			@RequestParam("id") Long propertyTypeId,
			@Valid @ModelAttribute PropertyTypeDTO propertyTypeDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();
				return ResponseEntity.badRequest().body(
						ResponseObject.builder()
								.status(400)
								.message("Update property type with id = "
										+ propertyTypeId + " failed")
								.data(errors)
								.build());
			}
			PropertyType updatePropertyType = propertyTypeService.updatePropertyType(propertyTypeId,
					propertyTypeDTO);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Update property type with id = " + propertyTypeId
									+ " successfully")
							.data(updatePropertyType)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Update property type with id = " + propertyTypeId
									+ " failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * Get all property types
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllPropertyTypes() {
		List<PropertyType> propertyTypes = propertyTypeService.getAllPropertyTypes();
		return ResponseEntity.ok(
				ResponseObject.builder()
						.status(200)
						.message("Get all property types successfully")
						.data(propertyTypes)
						.build());
	}

	/**
	 * Get a property type by id
	 * 
	 * @param propertyTypeId
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<ResponseObject> getPropertyType(@RequestParam("id") Long propertyTypeId) {
		try {
			PropertyType propertyType = propertyTypeService.getPropertyTypeById(propertyTypeId);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Get a property type with id = " + propertyTypeId
									+ " successfully")
							.data(propertyType)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("get a property type with id = " + propertyTypeId
									+ "  Failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * Soft delete a property type
	 * 
	 * @param propertyTypeId
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> deletePropertyType(@RequestParam("id") Long propertyTypeId) {
		try {
			propertyTypeService.deletePropertyType(propertyTypeId);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Deleted a property type with id= " + propertyTypeId
									+ " successfully")
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Delete a property type with id= " + propertyTypeId
									+ "  Failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * recover a property type
	 */
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/recover")
	public ResponseEntity<ResponseObject> recover(@RequestParam("id") Long id) {
		try {
			propertyTypeService.recover(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Recovered property type successfully")
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Recover property type failed")
					.data(e.getMessage())
					.build());
		}
	}
}
