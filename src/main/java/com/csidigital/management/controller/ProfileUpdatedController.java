package com.csidigital.management.controller;

import com.csidigital.management.service.impl.ProfileUpdatedServiceImpl;
import com.csidigital.shared.dto.request.ProfileUpdatedRequest;
import com.csidigital.shared.dto.response.ProfileUpdatedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/updatedProfiles")
//@RequestMapping("/crm/requestedProfiles")
//@CrossOrigin(origins = "${cross.origin.url}")
public class ProfileUpdatedController {
    @Autowired
    private ProfileUpdatedServiceImpl requestedProfileService ;

    @GetMapping()
    public List<ProfileUpdatedResponse> getAllRequestedProfiles() {
        return requestedProfileService.getAllRequestedProfiles();
    }

    @GetMapping("/{id}")
    public ProfileUpdatedResponse getRequestedProfileById(@PathVariable Long id){
        return requestedProfileService.getRequestedProfileById(id);
    }

    @PostMapping()
    public ProfileUpdatedResponse createRequestedProfile(@Valid @RequestBody ProfileUpdatedRequest profileUpdatedRequest){
        System.out.println(profileUpdatedRequest);
        System.out.println(profileUpdatedRequest.getQuotationNum());
        return requestedProfileService.createRequestedProfile(profileUpdatedRequest);
    }

    @PutMapping("/{id}")
    public ProfileUpdatedResponse updateRequestedProfile(@Valid @RequestBody ProfileUpdatedRequest profileUpdatedRequest,
                                                         @PathVariable Long id){
        return requestedProfileService.updateRequestedProfile(profileUpdatedRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestedProfile(@PathVariable Long id){
        requestedProfileService.deleteRequestedProfile(id);
    }

}
