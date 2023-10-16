package com.csidigital.management.controller;

import com.csidigital.management.service.impl.RequirementServiceImpl;
import com.csidigital.shared.dto.request.RequirementRequest;
import com.csidigital.shared.dto.response.PartnerResponse;
import com.csidigital.shared.dto.response.RequirementResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/crm/requirements")
//@CrossOrigin(origins = "${cross.origin.url}")
public class RequirementController {
    @Autowired
    private RequirementServiceImpl requirementService ;

    @GetMapping()
    public List<RequirementResponse> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    @GetMapping("/{id}")
    public RequirementResponse getRequirementById(@PathVariable Long id){
        return requirementService.getRequirementById(id);
    }

    /*@GetMapping("/{id}/requestedProfiles")
    public List<ProfileUpdatedResponse> getRequirementRequestedProfilesById(@PathVariable Long id){
        return requirementService.getRequirementRequestedProfilesById(id);
    }*/

    @GetMapping("/partner/{reqId}")
    public PartnerResponse getPartnerByReqId(@PathVariable Long reqId){
        return requirementService.getPartnerByReqId(reqId);
    }

    @GetMapping("/{id}/partner")
    public List<RequirementResponse> getRequirementsByPartnerId(@PathVariable Long id){
        return requirementService.getByPartnerId(id);
    }

    /*@GetMapping("/{id}/profiles")
    public List<ProfileUpdatedResponse> getReqProfilesById(@PathVariable Long id){
        return requirementService.getReqProfilesById(id);
    }*/

    @PostMapping()
    public RequirementResponse createRequirement(@RequestBody RequirementRequest requirementRequest ){
        return requirementService.createRequirement(requirementRequest );
    }

    @PutMapping("/{id}")
    public RequirementResponse updateRequirement(@Valid @RequestBody RequirementRequest requirementRequest,
                                                 @PathVariable Long id){
        return requirementService.updateRequirement(requirementRequest, id);
    }

    @PutMapping("/updateToInProgress/{id}")
    void updateStatusToInProgress(@PathVariable Long id) {
        requirementService.updateStatusToInProgress(id);
    }
    @PutMapping("/updateToOpen/{id}")
    void updateStatusToOpen(@PathVariable Long id) {
        requirementService.updateStatusToOpen(id);
    }
    @PutMapping("/updateToClosed/{id}")
    void updateStatusToClosed(@PathVariable Long id) {
        requirementService.updateStatusToClosed(id);
    }
    @PutMapping("/updateToPartiallyWon/{id}")
    void updateStatusToPartiallyWon(@PathVariable Long id) {
        requirementService.updateStatusToPartiallyWon(id);
    }
    @PutMapping("/updateToTotallyWon/{id}")
    void updateStatusToTotallyWon(@PathVariable Long id) {
        requirementService.updateStatusToTotallyWon(id);
    }
    @PutMapping("/updateToPartiallyLost/{id}")
    void updateStatusToPartiallyLost(@PathVariable Long id) {
        requirementService.updateStatusToPartiallyLost(id);
    }
    @PutMapping("/updateToTotallyLost/{id}")
    void updateStatusToTotallyLost(@PathVariable Long id) {
        requirementService.updateStatusToTotallyLost(id);
    }
    @PutMapping("/updateToAbandoned/{id}")
    void updateStatusToAbandoned(@PathVariable Long id) {
        requirementService.updateStatusToAbandoned(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRequirement(@PathVariable Long id){
        requirementService.deleteRequirement(id);
    }

}