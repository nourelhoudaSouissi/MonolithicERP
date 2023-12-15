package com.csidigital.management.controller;

import com.csidigital.management.service.ServiceService;
import com.csidigital.management.service.TvaCodeService;
import com.csidigital.shared.dto.request.ServiceRequest;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.ServiceResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService ;

    @GetMapping("getAllServices")
    public List<ServiceResponse> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/getServiceById/{id}")
    public ServiceResponse getServiceById(@PathVariable Long id){
        return serviceService.getServiceById(id);
    }


    @PostMapping(value = "/createService", produces = "application/json")
    @ResponseBody
    public ServiceResponse createService(@Valid @RequestBody ServiceRequest serviceRequest){
        return serviceService.createService(serviceRequest);
    }

    @PutMapping("/updateService/{id}")
    public ServiceResponse updateService(@Valid @RequestBody ServiceRequest serviceRequest,
                                         @PathVariable Long id){
        return serviceService.updateService(serviceRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable Long id){

        serviceService.deleteService(id);
    }
}
