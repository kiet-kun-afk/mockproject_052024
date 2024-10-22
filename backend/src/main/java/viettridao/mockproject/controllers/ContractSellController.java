package viettridao.mockproject.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import viettridao.mockproject.dtos.contracts.ContractSellDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IContractSellService;

/**
 * ContractSellController
 * Version: 1.0
 * Date: 5/24/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/24/2024 diepit9x Create
 * 5/28/2024 kiet-kun-afk Update
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/contract-sell")
public class ContractSellController {
    private final IContractSellService contractSellService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> createContractSell(
            @Valid @ModelAttribute ContractSellDTO contractSellDTO,
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
            ContractSellDTO contractSell = contractSellService.createContractSell(contractSellDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Create contract successfully")
                    .data(contractSell)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> updateContractSell(
            @Valid @ModelAttribute ContractSellDTO contractSellDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update contract failed")
                    .data(errors)
                    .build());
        }
        try {
            ContractSellDTO contractSell = contractSellService.updateContractSell(contractSellDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Update contract successfully")
                    .data(contractSell)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Update contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getContractSellById(@RequestParam("id") Long id) {
        try {
            ContractSellDTO contractSell = contractSellService.getContractSellById(id);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get contract successfully")
                    .data(contractSell)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get contract failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllContractSells() {
        try {
            List<ContractSellDTO> contractSells = contractSellService.getAllContractSells();
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Get all contracts successfully")
                    .data(contractSells)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Get all contracts failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> delete(@RequestParam Long id) {
        try {
            contractSellService.delete(id);
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
            contractSellService.recover(id);
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
}
