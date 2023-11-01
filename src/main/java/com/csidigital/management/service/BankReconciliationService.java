package com.csidigital.management.service;

import com.csidigital.shared.dto.request.BankReconciliationDtoRequest;
import com.csidigital.shared.dto.response.BankReconciliationDtoResponse;

import java.util.List;

public interface BankReconciliationService {
    List<BankReconciliationDtoResponse> getAllBankReconciliation();

    BankReconciliationDtoResponse getBankReconciliationById(Long id);

    BankReconciliationDtoResponse createBankReconciliation(BankReconciliationDtoRequest bankReconciliationDtoRequest);

    BankReconciliationDtoResponse updateBankReconciliation(BankReconciliationDtoRequest bankReconciliationDtoRequest, Long id);

    void deleteBankReconciliationById(Long id);
}
