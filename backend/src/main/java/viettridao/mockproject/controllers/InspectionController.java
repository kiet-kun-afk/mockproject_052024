package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.InspectionDTO;
import viettridao.mockproject.responses.InspectionResponse;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IInspectionService;

/**
 * InspectionController
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kien-kun-afk Create
 */
@RestController
@RequestMapping("${api.prefix}/inspections")
@RequiredArgsConstructor
public class InspectionController {

    private final IInspectionService inspectionService;

    /**
     * create inspection
     * 
     * @param inspectionDTO
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> createInspection(
            @Valid @ModelAttribute InspectionDTO inspectionDTO,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(
                        ResponseObject.builder()
                                .status(400)
                                .message("Create inspection failed")
                                .data(errors)
                                .build());
            }
            InspectionResponse inspectionResponse = inspectionService.createInspection(inspectionDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create inspection successfully")
                    .data(inspectionResponse)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create inspection failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * get all inspections by id
     * 
     * @param id
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> getInspectionsById(@RequestParam("id") Long inspectionId) {
        try {
            InspectionResponse inspectionResponse = inspectionService.getInspection(inspectionId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all inspections by id successfully")
                    .data(inspectionResponse)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message(e.getMessage())
                    .build());
        }
    }

    /**
     * get all inspections by propertyId/inspectorId
     * 
     * @param property/inspector id
     * @return
     */
    @GetMapping("/get-by-other")
    public ResponseEntity<ResponseObject> getInspectionsByOther(
            @RequestParam(required = false) Long propertyId,
            @RequestParam(required = false) Long inspectorId) {
        try {
            List<InspectionResponse> inspections;
            if (propertyId != null) {
                inspections = inspectionService.getInspectionsByPropertyId(propertyId);
            } else {
                inspections = inspectionService.getInspectionsByInspectorId(inspectorId);
            }
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all inspections by propertyId/inspectorId successfully")
                    .data(inspections)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message(e.getMessage())
                    .build());
        }
    }

    /**
     * update inspection
     * 
     * @param inspectionDTO
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> updateInspection(
            @Valid @ModelAttribute InspectionDTO inspectionDTO,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(
                        ResponseObject.builder()
                                .status(400)
                                .message("Update inspection failed")
                                .data(errors)
                                .build());
            }
            InspectionResponse inspectionResponse = inspectionService.updateInspection(inspectionDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Update inspection successfully")
                    .data(inspectionResponse)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message(e.getMessage())
                    .build());
        }
    }

    /**
     * delete inspection
     * 
     * @param id
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteInspection(@RequestParam("id") Long inspectionId) {
        try {
            inspectionService.deleteInspection(inspectionId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Delete inspection successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message(e.getMessage())
                    .build());
        }
    }

}
