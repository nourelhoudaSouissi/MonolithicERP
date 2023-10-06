package com.csidigital.management.service;

import com.csidigital.dao.entity.*;
import com.csidigital.shared.dto.request.ContractRequest;
import com.csidigital.shared.dto.response.ContractResponse;

import java.util.List;

public interface ContractService {
    ContractResponse createContract(ContractRequest request);
    List<ContractResponse> getAllContracts();
    ContractResponse getContractById(Long id);

    ContractResponse updateContract(ContractRequest request, Long id);

    void deleteContract(Long id);
    void updateStatusById(Long id, String contractStatus);
    void updateStatusToAcceptedById(Long id);
    void updateStatusToRefusedById(Long id);
    void updateStatusToExpiredById(Long id);
    List<BenefitRC> getContractBenefits(Long id);
    List<ExceptionalFee> getContractFee(Long id);

    void updateStatusToSentById(Long id);

    int countContractsByStillPendingStatus();

    int countContractsByRefusedStatus();
    int countContractsByAcceptedStatus();
    int countContractsBySentStatus();
    int countContractsByExpiredStatus();

    List<Contract> getAllAccepted();
}
