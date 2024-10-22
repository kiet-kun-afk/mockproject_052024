package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.InspectorDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IInspectorService;

/**
 * PropertyController
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 kiet-kun-afk Create
 */
@RestController
@RequestMapping("${api.prefix}/inspectors")
@RequiredArgsConstructor
public class InspectorController {

	private final IInspectorService inspectorService;

	/**
	 * get all inspectors
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAll() {
		try {
			List<InspectorDTO> inspectors = inspectorService.getInspectorList();
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("List inspectors.")
					.data(inspectors)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message(e.getMessage())
					.build());
		}
	}

	/**
	 * get all active inspectors
	 * 
	 * @return
	 */
	@GetMapping("/all-active")
	public ResponseEntity<ResponseObject> getAllActiveInspectors() {
		try {
			List<InspectorDTO> inspectors = inspectorService.getActiveInspectorList();
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("List active inspectors.")
					.data(inspectors)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message(e.getMessage())
					.build());
		}
	}

	/**
	 * Add a new inspector
	 * 
	 * @param inspectorDTO
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public ResponseEntity<ResponseObject> addInspector(
			@Valid @ModelAttribute InspectorDTO inspectorDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();
				return ResponseEntity.badRequest().body(ResponseObject.builder()
						.status(400)
						.message("Add inspector failed")
						.data(errors)
						.build());
			}
			InspectorDTO inspector = inspectorService.createInspector(inspectorDTO);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Add inspector Success")
					.data(inspector)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Add inspector Failed")
					.data(e.getMessage())
					.build());
		}

	}

	/**
	 * Get inspector by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<ResponseObject> getInspectorById(@RequestParam("id") Long id) {
		try {
			InspectorDTO inspector = inspectorService.getInspectorById(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get inspector successfully")
					.data(inspector)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get inspector failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Get inspector by phone number
	 * 
	 * @param phoneNumber
	 * @return
	 */
	@GetMapping("/by-phone")
	public ResponseEntity<ResponseObject> getInspectorByPhoneNumber(
			@RequestParam String phoneNumber) {
		try {
			InspectorDTO inspector = inspectorService.getInspectorByPhoneNumber(phoneNumber);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get inspector successfully")
					.data(inspector)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get inspector failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Update inspector
	 * 
	 * @param inspectorDTO
	 * 
	 */
	@PreAuthorize("isAuthenticated()")
	@PutMapping("")
	public ResponseEntity<ResponseObject> updateInspector(
			@Valid @ModelAttribute InspectorDTO inspectorDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();
				return ResponseEntity.badRequest().body(ResponseObject.builder()
						.status(400)
						.message("Update inspector failed")
						.data(errors)
						.build());
			}
			InspectorDTO inspector = inspectorService.updateInspector(inspectorDTO);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Update inspector successfully")
					.data(inspector)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Add inspector Failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Delete inspector
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> deleteInspector(@RequestParam("id") Long id) {
		try {
			InspectorDTO inspector = inspectorService.deleteInspector(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Delete inspector successfully")
					.data(inspector)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Delete inspector failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Recover inspector
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/recover")
	public ResponseEntity<ResponseObject> recoverInspector(@RequestParam("id") Long id) {
		try {
			InspectorDTO inspector = inspectorService.recoverInspector(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Recover inspector successfully")
					.data(inspector)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Recover inspector failed")
					.data(e.getMessage())
					.build());
		}
	}
}
