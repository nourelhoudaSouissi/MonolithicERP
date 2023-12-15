package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;

import java.util.List;

public interface ServiceService {
    ServiceResponse createService(ServiceRequest request);
    List<ServiceResponse> getAllServices();
    ServiceResponse getServiceById(Long id);

    ServiceResponse updateService(ServiceRequest request, Long id);

    void deleteService(Long id);
}
