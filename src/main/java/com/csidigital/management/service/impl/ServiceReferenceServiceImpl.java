package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.ServiceReference;
import com.csidigital.dao.entity.Weekend;
import com.csidigital.dao.repository.ServiceReferenceRepository;
import com.csidigital.management.service.ServiceReferenceService;
import com.csidigital.shared.dto.request.ServiceReferenceRequest;
import com.csidigital.shared.dto.response.ServiceReferenceResponse;
import com.csidigital.shared.dto.response.WeekendResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServiceReferenceServiceImpl implements ServiceReferenceService {
    @Autowired
    private ServiceReferenceRepository serviceReferenceRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ServiceReferenceResponse createServiceReference(ServiceReferenceRequest request) {
        ServiceReference serviceReference = modelMapper.map(request, ServiceReference.class);
        ServiceReference savedServiceReference = serviceReferenceRepository.save(serviceReference);
        return modelMapper.map(savedServiceReference, ServiceReferenceResponse.class);
    }

    @Override
    public List<ServiceReferenceResponse> getAllServiceReferences() {
        List<ServiceReference> serviceReferences = serviceReferenceRepository.findAll();
        List<ServiceReferenceResponse> serviceReferenceResponses = new ArrayList<>();


        for (ServiceReference serviceReference: serviceReferences) {
            ServiceReferenceResponse response = modelMapper.map(serviceReference, ServiceReferenceResponse.class);
            serviceReferenceResponses.add(response);
        }

        return serviceReferenceResponses;
    }

    @Override
    public ServiceReferenceResponse getServiceReferenceById(Long id) {
        ServiceReference serviceReference = serviceReferenceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ServiceReference with id " +id+ " not found"));
        ServiceReferenceResponse serviceReferenceResponse = modelMapper.map(serviceReference, ServiceReferenceResponse.class);
        return serviceReferenceResponse;
    }

    @Override
    public ServiceReferenceResponse updateServiceReference(ServiceReferenceRequest request, Long id) {
        ServiceReference existingServiceReference = serviceReferenceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ServiceReference with id: " + id + " not found"));
        modelMapper.map(request, existingServiceReference);
        ServiceReference savedServiceReference = serviceReferenceRepository.save(existingServiceReference);
        return modelMapper.map(savedServiceReference, ServiceReferenceResponse.class);
    }

    @Override
    public void deleteServiceReference(Long id) {
        serviceReferenceRepository.deleteById(id);

    }
}
