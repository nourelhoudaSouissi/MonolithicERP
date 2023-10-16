package com.csidigital.management.controller;

import com.csidigital.management.service.impl.ProfileServiceImpl;
import com.csidigital.shared.dto.request.ProfileRequest;
import com.csidigital.shared.dto.response.ProfileResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/crm/profiles")
//@CrossOrigin(origins = "${cross.origin.url}")
public class ProfileController {
    @Autowired
    private ProfileServiceImpl profileService ;

    @GetMapping()
    public List<ProfileResponse> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ProfileResponse getProfileById(@PathVariable Long id){
        return profileService.getProfileById(id);
    }

    @PostMapping()
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest){
        System.out.println(profileRequest);
        System.out.println(profileRequest.getCatalogNum());
        return profileService.createProfile(profileRequest);
    }

    @PutMapping("/{id}")
    public ProfileResponse updateProfile(@Valid @RequestBody ProfileRequest profileRequest,
                                                           @PathVariable Long id){
        return profileService.updateProfile(profileRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id){
        profileService.deleteProfile(id);
    }

}
