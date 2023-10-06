package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ExternalResourceRequest;
import com.csidigital.shared.dto.response.ExternalResourceResponse;

import java.util.List;

public interface ExternalResourceService {
    ExternalResourceResponse createExternalResource(ExternalResourceRequest request);
    List<ExternalResourceResponse> getAllExternalResource();
    ExternalResourceResponse getExternalResourceById(Long id);

    ExternalResourceResponse updateExternalResource(ExternalResourceRequest request, Long id);

    void deleteExternalResource(Long id);

}
