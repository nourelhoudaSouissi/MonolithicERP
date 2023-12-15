package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.request.ServiceUpdatedRequest;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.dto.response.ServiceUpdatedResponse;

import java.util.List;

public interface ServiceUpdatedService {
    ServiceUpdatedResponse createServiceUpdated(ServiceUpdatedRequest request);
    List<ServiceUpdatedResponse> getAllServiceUpdated();
    ServiceUpdatedResponse getServiceUpdatedById(Long id);

    ServiceUpdatedResponse updateServiceUpdated(ServiceUpdatedRequest request, Long id);

    void deleteServiceUpdated(Long id);
}
