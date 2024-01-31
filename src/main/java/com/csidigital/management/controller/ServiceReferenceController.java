package com.csidigital.management.controller;

import com.csidigital.dao.entity.ServiceReference;
import com.csidigital.management.service.impl.ServiceReferenceServiceImpl;
import com.csidigital.management.service.impl.WeekendImpl;
import com.csidigital.shared.dto.request.ServiceReferenceRequest;
import com.csidigital.shared.dto.request.WeekendRequest;
import com.csidigital.shared.dto.response.ServiceReferenceResponse;
import com.csidigital.shared.dto.response.WeekendResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/serviceReference")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceReferenceController {
    @Autowired
    private ServiceReferenceServiceImpl serviceReferenceService ;

    @GetMapping("getAllServiceReferences")
    public List<ServiceReferenceResponse> getAllServiceReferences() {

        return serviceReferenceService.getAllServiceReferences();
    }

    @GetMapping("/getServiceReferenceById/{id}")
    public ServiceReferenceResponse getServiceReferenceById(@PathVariable Long id){

        return serviceReferenceService.getServiceReferenceById(id);
    }

    @PostMapping("/createServiceReference")
    public ServiceReferenceResponse createServiceReference(@Valid @RequestBody ServiceReferenceRequest serviceReferenceRequest){
        return serviceReferenceService.createServiceReference(serviceReferenceRequest);
    }

    @PutMapping("/updateServiceReference/{id}")
    public ServiceReferenceResponse updateServiceReference(@Valid @RequestBody ServiceReferenceRequest serviceReferenceRequest,
                                         @PathVariable Long id){
        return serviceReferenceService.updateServiceReference(serviceReferenceRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteServiceReference(@PathVariable Long id){

        serviceReferenceService.deleteServiceReference(id);
    }

}
