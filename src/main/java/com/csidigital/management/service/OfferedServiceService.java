package com.csidigital.management.service;

import com.csidigital.shared.dto.request.OfferedServiceRequest;
import com.csidigital.shared.dto.response.OfferedServiceResponse;

import java.util.List;

public interface OfferedServiceService {
    OfferedServiceResponse createOfferedService(OfferedServiceRequest offeredServiceRequest);
    List<OfferedServiceResponse> getAllOfferedServices();
    OfferedServiceResponse getOfferedServiceById(Long id);

    OfferedServiceResponse updateOfferedService(OfferedServiceRequest offeredServiceRequest , Long id );

    void deleteOfferedService(Long id);
}
