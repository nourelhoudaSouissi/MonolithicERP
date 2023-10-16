package com.csidigital.management.service;

import com.csidigital.shared.dto.request.BankAccountRequest;
import com.csidigital.shared.dto.response.BankAccountResponse;

import java.util.List;

public interface PartnerBankAccountService {
    BankAccountResponse createBankAccount(BankAccountRequest request);
    List<BankAccountResponse> getAllBankAccounts();
    BankAccountResponse getBankAccountById(Long id);

    BankAccountResponse updateBankAccount(BankAccountRequest request , Long id );

    void deleteBankAccount(Long id);
}
