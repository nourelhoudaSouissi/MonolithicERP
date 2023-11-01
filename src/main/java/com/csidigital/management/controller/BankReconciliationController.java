package com.csidigital.management.controller;

import com.csidigital.management.service.BankReconciliationService;
import com.csidigital.shared.dto.request.BankReconciliationDtoRequest;
import com.csidigital.shared.dto.response.BankReconciliationDtoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fm/bankReconciliation")
@CrossOrigin("*")
public class BankReconciliationController {
    private final BankReconciliationService bankReconciliationService;

    public BankReconciliationController(BankReconciliationService bankReconciliationService) {
        this.bankReconciliationService = bankReconciliationService;
    }

    @GetMapping
    public List<BankReconciliationDtoResponse> getAllBankReconciliation() {
        return bankReconciliationService.getAllBankReconciliation();
    }

    @GetMapping("/{id}")
    public BankReconciliationDtoResponse getBankReconciliationById(@PathVariable Long id) {
        return bankReconciliationService.getBankReconciliationById(id);
    }

    @PostMapping
    public BankReconciliationDtoResponse createBankReconciliation(@RequestBody BankReconciliationDtoRequest bankReconciliationDtoRequest) {
        return bankReconciliationService.createBankReconciliation(bankReconciliationDtoRequest);
    }

    @PutMapping("/{id}")
    public BankReconciliationDtoResponse updateBankReconciliation(@RequestBody BankReconciliationDtoRequest bankReconciliationDtoRequest, @PathVariable Long id) {
        return bankReconciliationService.updateBankReconciliation(bankReconciliationDtoRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBankReconciliation(@PathVariable Long id) {
        bankReconciliationService.deleteBankReconciliationById(id);
    }
}
