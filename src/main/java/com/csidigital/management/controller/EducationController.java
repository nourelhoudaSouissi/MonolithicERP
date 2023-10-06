package com.csidigital.management.controller;

import com.csidigital.management.service.impl.EducationImpl;
import com.csidigital.shared.dto.request.EducationRequest;
import com.csidigital.shared.dto.response.EducationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rh/education")
public class EducationController {
    @Autowired
    private EducationImpl EducationService ;

    @GetMapping("/getEducations")
    public List<EducationResponse> getAllEducations() {
        return EducationService.getAllEducations();
    }

    @GetMapping("/get/{id}")
    public EducationResponse getEducationById(@PathVariable Long id){
        return EducationService.getEducationById(id);
    }

    @PostMapping("/add")
    public EducationResponse createEducation(@Valid @RequestBody EducationRequest educationRequest){
        return EducationService.createEducation(educationRequest);
    }

    @PostMapping("/addEducations")
    public List<EducationResponse> createEducations(@Valid @RequestBody List<EducationRequest> educationRequests) {
        List<EducationResponse> educationResponses = new ArrayList<>();

        for (EducationRequest educationRequest : educationRequests) {
            EducationResponse educationResponse = EducationService.createEducation(educationRequest);
            educationResponses.add(educationResponse);
        }

        return educationResponses;
    }
    @PutMapping("/update/{id}")
    public EducationResponse updateEducation(@Valid @RequestBody EducationRequest educationRequest,
                                         @PathVariable Long id){
        return EducationService.updateEducation(educationRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEducation(@PathVariable Long id){
        EducationService.deleteEducation(id);
    }

}
