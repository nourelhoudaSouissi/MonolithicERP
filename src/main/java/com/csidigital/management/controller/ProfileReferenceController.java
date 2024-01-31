package com.csidigital.management.controller;

import com.csidigital.management.service.impl.ProfileReferenceServiceImpl;
import com.csidigital.shared.dto.request.ProfileReferenceRequest;
import com.csidigital.shared.dto.response.ProfileReferenceResponse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/profileReference")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileReferenceController {
    @Autowired
    private ProfileReferenceServiceImpl profileReferenceService ;

    @GetMapping("getAllProfileReferences")
    public List<ProfileReferenceResponse> getAllProfileReferences() {

        return profileReferenceService.getAllProfileReferences();
    }

    @GetMapping("/getProfileReferenceById/{id}")
    public ProfileReferenceResponse getProfileReferenceById(@PathVariable Long id){

        return profileReferenceService.getProfileReferenceById(id);
    }

    @PostMapping("/createProfileReference")
    public ProfileReferenceResponse createProfileReference(@Valid @RequestBody ProfileReferenceRequest profileReferenceRequest){
        return profileReferenceService.createProfileReference(profileReferenceRequest);
    }

    @PutMapping("/updateProfileReference/{id}")
    public ProfileReferenceResponse updateProfileReference(@Valid @RequestBody ProfileReferenceRequest profileReferenceRequest,
                                         @PathVariable Long id){
        return profileReferenceService.updateProfileReference(profileReferenceRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfileReference(@PathVariable Long id){

        profileReferenceService.deleteProfileReference(id);
    }
}
