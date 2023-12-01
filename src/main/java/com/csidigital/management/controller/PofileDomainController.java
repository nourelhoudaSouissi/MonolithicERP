package com.csidigital.management.controller;

import com.csidigital.management.service.ProfileDomainService;
import com.csidigital.management.service.impl.WeekendImpl;
import com.csidigital.shared.dto.request.ProfileDomainRequest;
import com.csidigital.shared.dto.request.WeekendRequest;
import com.csidigital.shared.dto.response.ProfileDomainResponse;
import com.csidigital.shared.dto.response.WeekendResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/profileDomain")
public class PofileDomainController {

    @Autowired
    private ProfileDomainService profileDomainService ;

    @GetMapping("getAllProfileDomains")
    public List<ProfileDomainResponse> getAllProfileDomains() {

        return profileDomainService.getAllProfileDomains();
    }

    @GetMapping("/getProfileDomainById/{id}")
    public ProfileDomainResponse getProfileDomainById(@PathVariable Long id){

        return profileDomainService.getProfileDomainById(id);
    }


    @PostMapping(value = "/createProfileDomain", produces = "application/json")
    @ResponseBody
    public ProfileDomainResponse createProfileDomain(@Valid @RequestBody ProfileDomainRequest profileDomainRequest){
        return profileDomainService.createProfileDomain(profileDomainRequest);
    }

    @PutMapping("/updateProfileDomain/{id}")
    public ProfileDomainResponse updateProfileDomain(@Valid @RequestBody ProfileDomainRequest profileDomainRequest,
                                         @PathVariable Long id){
        return profileDomainService.updateProfileDomain(profileDomainRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfileDomain(@PathVariable Long id){

        profileDomainService.deleteProfileDomain(id);
    }
}
