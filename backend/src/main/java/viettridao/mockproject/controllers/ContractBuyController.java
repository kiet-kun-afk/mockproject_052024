package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.contracts.ContractBuyDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IContractBuyService;

/**
 * ContractBuyController
 * Version: 1.0
 * Date: 5/28/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/28/2024 kiet-kun-afk Create
 */
@RestController
@RequestMapping("${api.prefix}/contract-buy")
@RequiredArgsConstructor
public class ContractBuyController {

    private final IContractBuyService contractBuyService;

    /**
     * Create a new contract buy
     * 
     * @param contractBuyDTO
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> createContract(
            @Valid @ModelAttribute ContractBuyDTO contractBuyDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(400)
                            .message("Create contract failed")
                            .data(errors)
                            .build());
        }
        try {
            ContractBuyDTO contractBuy = contractBuyService.createContractBuy(contractBuyDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create contract successfully")
                    .data(contractBuy)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Get contract buy by id
     * 
     * @param contractBuyId
     * 
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public ResponseEntity<ResponseObject> getContractBuyById(@RequestParam Long id) {
        try {
            ContractBuyDTO contractBuy = contractBuyService.getContractBuyById(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get contract successfully")
                    .data(contractBuy)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Update contract buy
     * 
     * @param contractBuyDTO
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> updateContract(
            @Valid @ModelAttribute ContractBuyDTO contractBuyDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(400)
                            .message("Update contract failed")
                            .data(errors)
                            .build());
        }
        try {
            ContractBuyDTO contractBuy = contractBuyService.updateContractBuy(contractBuyDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Update contract successfully")
                    .data(contractBuy)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Deletes a contract
     * 
     * @param contractBuyId
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteContract(@RequestParam Long id) {
        try {
            contractBuyService.deleteContractBuy(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Delete contract successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Delete contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/recover")
    public ResponseEntity<ResponseObject> recover(@RequestParam Long id) {
        try {
            contractBuyService.recover(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Recover contract successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Recover contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Get all contracts
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllContracts() {
        try {
            List<ContractBuyDTO> contracts = contractBuyService.getAllContractBuys();
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all contracts successfully")
                    .data(contracts)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get all contracts failed")
                    .data(e.getMessage())
                    .build());
        }
    }
}
