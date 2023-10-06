package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ResourceContractRequest;
import com.csidigital.shared.dto.response.ResourceContractResponse;

import java.util.List;

public interface ResourceContractService {
    ResourceContractResponse createResourceContract(ResourceContractRequest request);
    List<ResourceContractResponse> getAllResourceContracts();
    ResourceContractResponse getResourceContractById(Long id);

    ResourceContractResponse updateResourceContract(ResourceContractRequest request, Long id);

    void deleteResourceContract(Long id);

}
