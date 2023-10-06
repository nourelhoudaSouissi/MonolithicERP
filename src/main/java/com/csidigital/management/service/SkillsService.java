package com.csidigital.management.service;

import com.csidigital.shared.dto.request.SkillsRequest;
import com.csidigital.shared.dto.response.SkillsResponse;

import java.util.List;

public interface SkillsService {
    SkillsResponse createSkills(SkillsRequest request);
    List<SkillsResponse> getAllSkills();
    SkillsResponse getSkillsById(Long id);

    SkillsResponse updateSkills(SkillsRequest request, Long id);

    void deleteSkills(Long id);
}
