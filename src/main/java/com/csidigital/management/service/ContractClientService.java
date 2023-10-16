package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ContractClientRequest;
import com.csidigital.shared.dto.response.ContractClientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractClientService {
    ContractClientResponse createContractClient(ContractClientRequest request);
    List<ContractClientResponse> getAllContractClient();
    ContractClientResponse getContractClientById(Long id);

    ContractClientResponse UpdateContractClient(ContractClientRequest request, Long id);

    void deleteContractClient(Long id);
    void updateStatusById(Long id, String contractStatus);
    void updateStatusToAcceptedById(Long id);
    void updateStatusToRefusedById(Long id);
    void updateStatusToSentById(Long id);
}
