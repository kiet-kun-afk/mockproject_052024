package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.contracts.ContractSellBuyDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IContractSellBuyService;

@RestController
@RequestMapping("${api.prefix}/contract-sell-buy")
@RequiredArgsConstructor
public class ContractSellBuyController {

    private final IContractSellBuyService contractSellBuyService;

    /**
     * Create a new contract sell buy
     * 
     * @param contractSellBuyDTO
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> createContractSellBuy(
            @Valid @ModelAttribute ContractSellBuyDTO contractSellBuyDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(400)
                            .message("Create new contract failed")
                            .data(errors)
                            .build());
        }
        try {
            ContractSellBuyDTO contractSellBuy = contractSellBuyService.createContractSellBuy(contractSellBuyDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create new contract successfully")
                    .data(contractSellBuy)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create new contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Get all contract sell buy
     * 
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllContractSellBuy() {
        try {
            List<ContractSellBuyDTO> contractSellBuys = contractSellBuyService.getAll();
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all contract successfully")
                    .data(contractSellBuys)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get all contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Get contract sell buy by id
     * 
     * @param id
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> getContractSellBuyById(@RequestParam Long id) {
        try {
            ContractSellBuyDTO contractSellBuy = contractSellBuyService.getContractSellBuyById(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get contract successfully")
                    .data(contractSellBuy)
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
     * Update contract sell buy
     * 
     * @param contractSellBuyDTO
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> updateContractSellBuy(
            @Valid @ModelAttribute ContractSellBuyDTO contractSellBuyDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(400)
                            .message("Create new contract failed")
                            .data(errors)
                            .build());
        }
        try {
            ContractSellBuyDTO contractSellBuy = contractSellBuyService.updateContractSellBuy(contractSellBuyDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create new contract successfully")
                    .data(contractSellBuy)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create new contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * Delete a contract
     * 
     * @param contractId
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteContract(@RequestParam("id") Long contractId) {
        try {
            contractSellBuyService.deleteContractSellBuy(contractId);
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
    public ResponseEntity<ResponseObject> recover(@RequestParam("id") Long id) {
        try {
            contractSellBuyService.recover(id);
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
     * Get a contract by property id
     * 
     * @param propertyId
     */
    @GetMapping("/by-property")
    public ResponseEntity<ResponseObject> getContractByPropertyId(@RequestParam("id") Long propertyId) {
        try {
            ContractSellBuyDTO contractSellBuy = contractSellBuyService.getContractSellBuyByPropertyId(propertyId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get contract successfully")
                    .data(contractSellBuy)
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
     * Get contracts by user id
     * 
     * @param userId
     */
    @GetMapping("/by-user")
    public ResponseEntity<ResponseObject> getContractByUserId(@RequestParam("id") Long userId) {
        try {
            List<ContractSellBuyDTO> contractSellBuys = contractSellBuyService.getContractSellBuyByUserId(userId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get contract successfully")
                    .data(contractSellBuys)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }
}
