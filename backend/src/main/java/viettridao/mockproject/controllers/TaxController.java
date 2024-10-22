package viettridao.mockproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.models.Tax;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.ITaxService;

/**
 * TaxController
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 */
@RestController
@RequestMapping("${api.prefix}/taxes")
@RequiredArgsConstructor
public class TaxController {

    private final ITaxService taxService;

    /**
     * get all tax
     */
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllTax() {
        return ResponseEntity.ok(ResponseObject.builder()
                .status(200)
                .data(taxService.getAll())
                .build());
    }

    /**
     * create a new tax
     * 
     * @param Tax
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> createTax(@ModelAttribute Tax tax) {
        try {
            return ResponseEntity.ok(ResponseObject.builder()
                    .data(taxService.createTax(tax))
                    .status(200)
                    .message("Created tax")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Error creating tax")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * find a tax by id
     * 
     * @param id
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> findTaxById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Found tax")
                    .data(taxService.findTaxById(id))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Error finding tax")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * update a tax
     * 
     * @param Tax
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> updateTax(@ModelAttribute Tax tax) {
        try {
            return ResponseEntity.ok(ResponseObject.builder()
                    .data(taxService.updateTax(tax))
                    .status(200)
                    .message("Updated tax")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Error updating tax")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * delete a tax
     * 
     * @param Id
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteTax(@RequestParam Long id) {
        try {
            taxService.deleteTax(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Deleted tax")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Error deleting tax")
                    .data(e.getMessage())
                    .build());
        }
    }
}
