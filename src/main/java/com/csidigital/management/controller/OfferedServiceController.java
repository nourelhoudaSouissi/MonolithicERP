package com.csidigital.management.controller;

import com.csidigital.management.service.impl.OfferedServiceServiceImpl;
import com.csidigital.shared.dto.request.OfferedServiceRequest;
import com.csidigital.shared.dto.response.OfferedServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/offeredServices")
//@CrossOrigin(origins = "${cross.origin.url}")
public class OfferedServiceController {
    @Autowired

    private OfferedServiceServiceImpl offeredServiceService ;

    @GetMapping()
    public List<OfferedServiceResponse> getAllOfferedServices() {
        return offeredServiceService.getAllOfferedServices();
    }

    @GetMapping("/{id}")
    public OfferedServiceResponse getOfferedServiceById(@PathVariable Long id){
        return offeredServiceService.getOfferedServiceById(id);
    }

    @PostMapping()
    public OfferedServiceResponse createOfferedService(@Valid @RequestBody OfferedServiceRequest offeredServiceRequest){
        return offeredServiceService.createOfferedService(offeredServiceRequest);
    }

    @PutMapping("/{id}")
    public OfferedServiceResponse updateOfferedService(@Valid @RequestBody OfferedServiceRequest offeredServiceRequest,
                                     @PathVariable Long id){
        return offeredServiceService.updateOfferedService(offeredServiceRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOfferedService(@PathVariable Long id){
        offeredServiceService.deleteOfferedService(id);
    }
}
