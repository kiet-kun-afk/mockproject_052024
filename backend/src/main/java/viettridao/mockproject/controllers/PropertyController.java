package viettridao.mockproject.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.properties.PropertyDTO;
import viettridao.mockproject.responses.PropertyResponse;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IPropertyService;

/**
 * PropertyController
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kiet-kun-afk Create
 * 5/23/2024 kiet-kun-afk Update
 * 5/24/2024 kiet-kun-afk Update
 * 5/27/2024 kiet-kun-afk Update
 */
@RestController
@RequestMapping("${api.prefix}/properties")
@RequiredArgsConstructor
public class PropertyController {

	private final IPropertyService propertyService;

	/**
	 * get all properties
	 * customer can't call this method
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAll(
			@RequestParam Optional<Integer> pageNumber,
			@RequestParam Optional<Integer> pageSize) {
		try {
			Page<PropertyResponse> properties = propertyService.findAll(pageNumber.orElse(0), pageSize.orElse(10));
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("List properties.")
					.data(properties).build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get failed!")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * get properties not deleted
	 * show in home page or list properties for users
	 * 
	 * @return
	 */
	@GetMapping("/all-not-deleted")
	public ResponseEntity<ResponseObject> getPropertiesNotDeleted(
			@RequestParam Optional<Integer> pageNumber,
			@RequestParam Optional<Integer> pageSize) {
		try {
			Page<PropertyResponse> properties = propertyService
					.findAllPropertiesNotDeleted(pageNumber.orElse(0), pageSize.orElse(10));
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("List properties.")
					.data(properties)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get failed!")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Create a new property
	 * 
	 * @param propertyDTO
	 * @param images
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public ResponseEntity<ResponseObject> create(
			@ModelAttribute @Valid PropertyDTO propertyDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(FieldError::getDefaultMessage).toList();

			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Creation failed")
					.data(errors)
					.build());
		}
		try {
			PropertyResponse newProperty = propertyService.createProperty(propertyDTO);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Create successful.")
					.data(newProperty)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Create failed!")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * get information property
	 * 
	 * @param id property
	 * @return property
	 * @throws Exception
	 */
	@GetMapping("")
	public ResponseEntity<ResponseObject> getInformation(@RequestParam("id") Long id) throws Exception {
		try {
			PropertyResponse property = propertyService.getPropertyById(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get information successfully")
					.data(property).build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get information failed!")
					.data(e.getMessage())
					.build());
		}
		// PropertyDTO propertyDTO = propertyService.getPropertyById(id);
		// return ResponseEntity.ok(propertyDTO);
	}

	/**
	 * update property
	 * 
	 * @param id
	 * @param propertyDTO
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PutMapping("")
	public ResponseEntity<ResponseObject> update(
			@ModelAttribute @Valid PropertyDTO propertyDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getFieldErrors().stream()
					.map(FieldError::getDefaultMessage).toList();
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Update failed")
					.data(errors)
					.build());
		}
		try {
			propertyDTO.setFiles(
					propertyDTO.getFiles() != null ? propertyDTO.getFiles() : new ArrayList<>());
			PropertyResponse propertyResponse = propertyService.updateProperty(propertyDTO);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Update successfully")
					.data(propertyResponse)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Update failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * delete a property
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> delete(@RequestParam("id") Long id) {
		try {
			propertyService.deleteProperty(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Delete successfully")
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Delete failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * recover property
	 */
	@PutMapping("/recover")
	public ResponseEntity<ResponseObject> recover(@RequestParam("id") Long id) {
		try {
			propertyService.recover(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Recover property successfully")
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Recover property failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * get properties by street/state/city
	 * 
	 * @param street/state/city
	 * @return list of properties
	 */
	@GetMapping("/search")
	public ResponseEntity<ResponseObject> getPropertiesBySearch(
			@RequestParam(required = false) @Nullable Integer numberBathroom,
			@RequestParam(required = false) @Nullable Integer numberBedroom,
			@RequestParam(required = false) @Nullable Double price,
			@RequestParam(required = false) @Nullable String sortBy,
			@RequestParam Optional<Integer> pageNumber,
			@RequestParam Optional<Integer> pageSize) {
		try {
			Page<PropertyResponse> properties = propertyService.getPropertiesBySearch(numberBedroom,
					numberBathroom, price, sortBy, pageNumber.orElse(0), pageSize.orElse(10));
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get properties successfully")
					.data(properties)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get properties failed")
					.data(e.getMessage())
					.build());
		}
	}

	@GetMapping("/search-by-keyword")
	public ResponseEntity<ResponseObject> getPropertiesBySearch(@RequestParam("keyword") String keyword,
			@RequestParam Optional<Integer> pageNumber,
			@RequestParam Optional<Integer> pageSize) {
		try {
			Page<PropertyResponse> properties = propertyService.getPropertiesBySearch(keyword, pageNumber.orElse(0),
					pageSize.orElse(10));
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get properties successfully")
					.data(properties)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get properties failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * get properties by owner id
	 * 
	 * @param ownerId
	 * @return list of properties
	 */
	@GetMapping("/owner")
	public ResponseEntity<ResponseObject> getPropertiesByOwnerId(@RequestParam("id") Long ownerId) {
		try {
			List<PropertyResponse> properties = propertyService.getPropertiesByOwnerId(ownerId);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get properties by owner id successfully")
					.data(properties)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get properties by owner id failed")
					.data(e.getMessage())
					.build());
		}
	}
}
