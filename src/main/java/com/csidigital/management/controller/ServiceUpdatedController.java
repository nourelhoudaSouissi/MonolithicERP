package com.csidigital.management.controller;

import com.csidigital.management.service.ServiceService;
import com.csidigital.management.service.ServiceUpdatedService;
import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.request.ServiceUpdatedRequest;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.dto.response.ServiceUpdatedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/serviceUpdated")
public class ServiceUpdatedController {

    @Autowired
    private ServiceUpdatedService serviceUpdatedService ;

    @GetMapping("getAllServiceUpdateds")
    public List<ServiceUpdatedResponse> getAllServiceUpdateds() {
        return serviceUpdatedService.getAllServiceUpdated();
    }

    @GetMapping("/getServiceUpdatedById/{id}")
    public ServiceUpdatedResponse getServiceUpdatedById(@PathVariable Long id){
        return serviceUpdatedService.getServiceUpdatedById(id);
    }


    @PostMapping(value = "/createServiceUpdated", produces = "application/json")
    @ResponseBody
    public ServiceUpdatedResponse createServiceUpdated(@Valid @RequestBody ServiceUpdatedRequest serviceUpdatedRequest){
        return serviceUpdatedService.createServiceUpdated(serviceUpdatedRequest);
    }

    @PutMapping("/updateServiceUpdated/{id}")
    public ServiceUpdatedResponse updateServiceUpdated(@Valid @RequestBody ServiceUpdatedRequest serviceUpdatedRequest,
                                         @PathVariable Long id){
        return serviceUpdatedService.updateServiceUpdated(serviceUpdatedRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable Long id){

        serviceUpdatedService.deleteServiceUpdated(id);
    }
}
