package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.RequestTourDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IRequestTourService;

/**
 * RequestTourController
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@RestController
@RequestMapping("${api.prefix}/request-tour")
@RequiredArgsConstructor
public class RequestTourController {

    private final IRequestTourService requestTourService;

    /**
     * get all request tours
     * 
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAll() {
        try {
            List<RequestTourDTO> list = requestTourService.getAll();
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all request tours")
                    .data(list)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get all request tours failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * get request tour by id
     * 
     * @param id
     * @return requestTourDTO
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> getById(@RequestParam Long id) {
        try {
            RequestTourDTO requestTour = requestTourService.getRequestTour(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get request tour successfully")
                    .data(requestTour)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get request tour failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * create request tour
     * 
     * @param requestTourDTO
     * @return requestTourDTO
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("")
    public ResponseEntity<ResponseObject> create(@Valid @ModelAttribute RequestTourDTO requestTourDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create a new property type Failed")
                    .data(errors)
                    .build());
        }
        try {
            RequestTourDTO requestTour = requestTourService.createNewRequestTour(requestTourDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create request tour successfully")
                    .data(requestTour)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create request tour failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * update request tour
     * 
     * @param requestTourDTO
     * @return requestTourDTO
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> update(@Valid @ModelAttribute RequestTourDTO requestTourDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update a request tour Failed")
                    .data(errors)
                    .build());
        }
        try {
            RequestTourDTO requestTour = requestTourService.updateRequestTour(requestTourDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Update request tour successfully")
                    .data(requestTour)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update request tour failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * delete a request tour
     * 
     * @param id
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> delete(@RequestParam Long id) {
        try {
            requestTourService.deleteRequestTour(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Delete request tour successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Delete request tour failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * recover the request tour
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/recover")
    public ResponseEntity<ResponseObject> recover(@RequestParam Long id) {
        try {
            requestTourService.recover(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Recover request tour successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Recover request tour failed")
                    .data(e.getMessage())
                    .build());
        }
    }
}
