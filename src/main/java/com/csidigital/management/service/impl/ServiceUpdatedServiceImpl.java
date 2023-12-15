package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Quotation;
import com.csidigital.dao.entity.ServiceUpdated;
import com.csidigital.dao.repository.QuotationRepository;
import com.csidigital.dao.repository.ServiceRepository;
import com.csidigital.dao.repository.ServiceUpdatedRepository;
import com.csidigital.management.service.ServiceUpdatedService;
import com.csidigital.shared.dto.request.ServiceUpdatedRequest;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.dto.response.ServiceUpdatedResponse;
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
public class ServiceUpdatedServiceImpl implements ServiceUpdatedService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ServiceUpdatedRepository serviceUpdatedRepository;
    @Autowired
    private QuotationRepository quotationRepository ;
    @Override
    public ServiceUpdatedResponse createServiceUpdated(ServiceUpdatedRequest request) {
        Quotation quotation = quotationRepository.findById(request.getQuotationNum())
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));

        ServiceUpdated serviceUpdated = modelMapper.map(request, ServiceUpdated.class);
        serviceUpdated.setQuotation(quotation);
        ServiceUpdated savedServiceUpdated= serviceUpdatedRepository.save(serviceUpdated);
        return modelMapper.map(savedServiceUpdated, ServiceUpdatedResponse.class);
    }

    @Override
    public List<ServiceUpdatedResponse> getAllServiceUpdated() {
        List<ServiceUpdated> serviceUpdateds = serviceUpdatedRepository.findAll();
        List<ServiceUpdatedResponse> serviceUpdatedResponses = new ArrayList<>();


        for (ServiceUpdated serviceUpdated: serviceUpdateds) {
            ServiceUpdatedResponse response = modelMapper.map(serviceUpdated, ServiceUpdatedResponse.class);
            serviceUpdatedResponses.add(response);
        }

        return serviceUpdatedResponses;
    }

    @Override
    public ServiceUpdatedResponse getServiceUpdatedById(Long id) {
        ServiceUpdated serviceUpdated = serviceUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ServiceUpdated with id " +id+ " not found"));
        ServiceUpdatedResponse serviceUpdatedResponse = modelMapper.map(serviceUpdated, ServiceUpdatedResponse.class);
        return serviceUpdatedResponse;
    }

    @Override
    public ServiceUpdatedResponse updateServiceUpdated(ServiceUpdatedRequest request, Long id) {
        ServiceUpdated existingServiceUpdated = serviceUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ServiceUpdated with id: " + id + " not found"));
        modelMapper.map(request, existingServiceUpdated);
        ServiceUpdated savedServiceUpdated = serviceUpdatedRepository.save(existingServiceUpdated);
        return modelMapper.map(savedServiceUpdated, ServiceUpdatedResponse.class);
    }

    @Override
    public void deleteServiceUpdated(Long id) {
        serviceUpdatedRepository.deleteById(id);
    }
}
