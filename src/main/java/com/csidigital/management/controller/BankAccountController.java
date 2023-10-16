package com.csidigital.management.controller;

import com.csidigital.management.service.impl.BankAccountServiceImpl;
import com.csidigital.shared.dto.request.BankAccountRequest;
import com.csidigital.shared.dto.response.BankAccountResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/bankAccounts")
//@CrossOrigin(origins = "${cross.origin.url}")
public class BankAccountController {
    @Autowired
    private BankAccountServiceImpl accountService ;

    @GetMapping()
    public List<BankAccountResponse> getAllBankAccounts() {
        return accountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public BankAccountResponse getBankAccountById(@PathVariable Long id){
        return accountService.getBankAccountById(id);
    }

    @PostMapping()
    public BankAccountResponse createBankAccount(@Valid @RequestBody BankAccountRequest request){
        return accountService.createBankAccount(request);
    }

    @PutMapping("/{id}")
    public BankAccountResponse updateBankAccount(@Valid @RequestBody BankAccountRequest request,
                                                 @PathVariable Long id){
        return accountService.updateBankAccount(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable Long id){
        accountService.deleteBankAccount(id);
    }
}
