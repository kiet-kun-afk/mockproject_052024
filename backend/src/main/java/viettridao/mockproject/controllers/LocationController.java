package viettridao.mockproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import viettridao.mockproject.dtos.locations.LocationDTO;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.ILocationService;

import java.util.List;

/**
 * LocationController
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 diepit9x Create
 */
@RestController
@RequestMapping("${api.prefix}/locations")
@RequiredArgsConstructor
public class LocationController {
	private final ILocationService locationService;

	/**
	 * Add a new location
	 * 
	 * @param locationDTO
	 * @param bindingResult
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public ResponseEntity<ResponseObject> addLocation(
			@Valid @ModelAttribute LocationDTO locationDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();
				return ResponseEntity.badRequest().body(
						ResponseObject.builder()
								.status(400)
								.message("Add a new location Failed")
								.data(errors)
								.build());
			}
			Location newLocation = locationService.addLocation(locationDTO);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Add a new location successfully")
							.data(newLocation)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Add a new location Failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * update a existing location
	 * 
	 * @param locationId
	 * @param locationDTO
	 * @param bindingResult
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PutMapping("")
	public ResponseEntity<ResponseObject> updateLocation(
			@Valid @ModelAttribute LocationDTO locationDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();

				return ResponseEntity.badRequest().body(
						ResponseObject.builder()
								.status(400)
								.message("Update location Failed")
								.data(errors)
								.build());
			}
			Location updateLocation = locationService.updateLocation(locationDTO);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Update location successfully")
							.data(updateLocation)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Update location Failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * Get all locations by keyword
	 * 
	 * @param keyword
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllLocations(@RequestParam(defaultValue = "") String keyword) {
		try {
			List<Location> locations = locationService.getAllLocations(keyword);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Get all locations successfully")
							.data(locations)
							.build());
		} catch (Exception e) {
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Get all locations failed")
							.data(e.getMessage())
							.build());
		}
	}

	/**
	 * get a location by locationId
	 * 
	 * @param locationId
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<ResponseObject> getLocationById(@RequestParam("id") Long locationId) {
		try {
			Location location = locationService.getLocationById(locationId);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Get all locations successfully")
							.data(location)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Get all locations failed")
							.data(e.getMessage())
							.build());
		}
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> deleteLocationById(@RequestParam("id") Long locationId) {
		try {
			locationService.deleteLocationById(locationId);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Deleted location with id = " + locationId
									+ " successfully")
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseObject.builder()
							.status(400)
							.message("Deleted location with id = " + locationId + " failed")
							.data(e.getMessage())
							.build());
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("/recover")
	public ResponseEntity<ResponseObject> recover(@RequestParam Long id) {
		try {
			locationService.recover(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Recover location with id = " + id
							+ " successfully")
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Recovered location with id = " + id + " failed")
					.data(e.getMessage())
					.build());
		}
	}
}
