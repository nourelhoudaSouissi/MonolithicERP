package com.csidigital.management.controller;

import com.csidigital.management.service.ExperienceService;
import com.csidigital.shared.dto.request.ExperienceRequest;
import com.csidigital.shared.dto.response.ExperienceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rh/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService ;
    @GetMapping("/getExperiences")
    public List<ExperienceResponse> getAllExperiences() {
        return experienceService.getAllExperiences();
    }

    @GetMapping("/get/{id}")
    public ExperienceResponse getExperienceById(@PathVariable Long id){
        return experienceService.getExperienceById(id);
    }

    @PostMapping("/add")
    public ExperienceResponse createExperience(@Valid @RequestBody ExperienceRequest experienceRequest){
        return experienceService.createExperience(experienceRequest);
    }

    @PostMapping("/addExperiences")
    public List<ExperienceResponse> createExperience(@Valid @RequestBody List<ExperienceRequest> experienceRequests) {
        List<ExperienceResponse> experienceResponses = new ArrayList<>();

        for (ExperienceRequest experienceRequest : experienceRequests) {
            ExperienceResponse experienceResponse = experienceService.createExperience(experienceRequest);
            experienceResponses.add(experienceResponse);
        }

        return experienceResponses;
    }

    @PutMapping("/update/{id}")
    public ExperienceResponse updateExperience(@Valid @RequestBody ExperienceRequest experienceRequest,
                                         @PathVariable Long id){
        return experienceService.updateExperience(experienceRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExperience(@PathVariable Long id){
        experienceService.deleteExperience(id);
    }

}
