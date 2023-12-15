package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Catalog;
import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.entity.TvaCode;
import com.csidigital.dao.repository.CatalogRepository;
import com.csidigital.dao.repository.ServiceRepository;
import com.csidigital.dao.repository.TvaCodeRepository;
import com.csidigital.management.service.ServiceService;
import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;
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
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CatalogRepository catalogRepository ;
    @Autowired
    private TvaCodeRepository tvaCodeRepository ;

    /*@Override
    public ServiceResponse createService(ServiceRequest request) {
        TvaCode tvaCode = null;
        if(request.getTvaCodeNum()!=null) {
            tvaCode = tvaCodeRepository.findById(request.getTvaCodeNum())
                    .orElseThrow();
        }
        Catalog catalog = catalogRepository.findById(request.getCatalogNum())
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found"));

        com.csidigital.dao.entity.Service service = modelMapper.map(request, com.csidigital.dao.entity.Service.class);
        service.setCatalog(catalog);
        service.setTvaCode(tvaCode);
        com.csidigital.dao.entity.Service savedService= serviceRepository.save(service);
        return modelMapper.map(savedService, ServiceResponse.class);
    }
*/
    @Override
    public ServiceResponse createService(ServiceRequest request) {
        TvaCode tvaCode = null;
        if (request.getTvaCodeNum() != null) {
            tvaCode = tvaCodeRepository.findById(request.getTvaCodeNum())
                    .orElseThrow(() -> new ResourceNotFoundException("TvaCode not found for the given ID"));
        }

        Catalog catalog = catalogRepository.findById(request.getCatalogNum())
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found"));

        com.csidigital.dao.entity.Service service = modelMapper.map(request, com.csidigital.dao.entity.Service.class);
        service.setCatalog(catalog);

        if (tvaCode != null) {
            service.setTvaCode(tvaCode);
        } else {
            // In case tvaCode is null after retrieval, throw an exception as it's mandatory
            throw new ResourceNotFoundException("TvaCode not found for the given ID");
        }

        com.csidigital.dao.entity.Service savedService = serviceRepository.save(service);
        return modelMapper.map(savedService, ServiceResponse.class);
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        List<com.csidigital.dao.entity.Service> services = serviceRepository.findAll();
        List<ServiceResponse> serviceResponses = new ArrayList<>();


        for (com.csidigital.dao.entity.Service service: services) {
            ServiceResponse response = modelMapper.map(service, ServiceResponse.class);
            serviceResponses.add(response);
        }

        return serviceResponses;
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        com.csidigital.dao.entity.Service service = serviceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Service with id " +id+ " not found"));
        ServiceResponse serviceResponse = modelMapper.map(service, ServiceResponse.class);
        return serviceResponse;
    }

    @Override
    public ServiceResponse updateService(ServiceRequest request, Long id) {
        com.csidigital.dao.entity.Service existingService = serviceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Service with id: " + id + " not found"));
        modelMapper.map(request, existingService);
        com.csidigital.dao.entity.Service savedService = serviceRepository.save(existingService);
        return modelMapper.map(savedService, ServiceResponse.class);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
