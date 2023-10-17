package com.csidigital.management.service;

import com.csidigital.dao.entity.Employee;
import com.csidigital.shared.dto.request.ResourceRequest;
import com.csidigital.shared.dto.response.ResourceResponse;

import java.util.List;

public interface ResourceService {
    ResourceResponse createResource(ResourceRequest request);
    List<ResourceResponse> getAllResources();

    List<Employee> getChefs();

    ResourceResponse getResourceById(Long id);

    ResourceResponse updateResource(ResourceRequest request, Long id);

    void deleteResource(Long id);


}
