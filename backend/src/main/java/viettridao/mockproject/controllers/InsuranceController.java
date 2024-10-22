package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.InsuranceDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IInsuranceService;

/**
 * InsuranceController
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/insurances")
public class InsuranceController {

    private final IInsuranceService insuranceService;

    /**
     * get all insurances
     */
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAll() {
        try {
            List<InsuranceDTO> insurances = insuranceService.getAllInsurances();
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all insurances")
                    .data(insurances)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get all insurances failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * get an insurance by id
     * 
     * @param insuranceId
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> getById(@RequestParam("id") Long insuranceId) {
        try {
            InsuranceDTO insurance = insuranceService.getInsuranceById(insuranceId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get insurance successfully")
                    .data(insurance)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get insurance failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * create a new insurance
     * 
     * @param insuranceDTO
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> create(
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create insurance failed")
                    .data(errors)
                    .build());
        }
        try {
            InsuranceDTO newInsurance = insuranceService.createInsurance(insuranceDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create insurance successfully")
                    .data(newInsurance)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create insurance failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Updates an existing insurance
     * 
     * @param insuranceDTO
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> update(
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update insurance failed")
                    .data(errors)
                    .build());
        }
        try {
            InsuranceDTO newInsurance = insuranceService.updateInsurance(insuranceDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Update insurance successfully")
                    .data(newInsurance)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update insurance failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * delete an insurance
     * 
     * @param insuranceId
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> delete(@RequestParam("id") Long insuranceId) {
        try {
            insuranceService.deleteInsurance(insuranceId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Delete insurance successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Delete insurance failed")
                    .data(e.getMessage())
                    .build());
        }
    }
}
