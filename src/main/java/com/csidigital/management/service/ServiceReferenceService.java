package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ServiceReferenceRequest;
import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.response.ServiceReferenceResponse;
import com.csidigital.shared.dto.response.ServiceResponse;

import java.util.List;

public interface ServiceReferenceService {
    ServiceReferenceResponse createServiceReference(ServiceReferenceRequest request);
    List<ServiceReferenceResponse> getAllServiceReferences();
    ServiceReferenceResponse getServiceReferenceById(Long id);

    ServiceReferenceResponse updateServiceReference(ServiceReferenceRequest request, Long id);

    void deleteServiceReference(Long id);
}
