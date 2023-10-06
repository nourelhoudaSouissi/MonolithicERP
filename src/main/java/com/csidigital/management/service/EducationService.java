package com.csidigital.management.service;

import com.csidigital.shared.dto.request.EducationRequest;
import com.csidigital.shared.dto.response.EducationResponse;

import java.util.List;

public interface EducationService {
    EducationResponse createEducation(EducationRequest request);
    List<EducationResponse> getAllEducations();
    EducationResponse getEducationById(Long id);

    EducationResponse updateEducation(EducationRequest request, Long id);

    void deleteEducation(Long id);
}
