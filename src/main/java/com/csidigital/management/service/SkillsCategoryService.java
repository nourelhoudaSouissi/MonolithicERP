package com.csidigital.management.service;

import com.csidigital.shared.dto.request.SkillsCategoryRequest;
import com.csidigital.shared.dto.response.SkillsCategoryResponse;

import java.util.List;

public interface SkillsCategoryService {
    SkillsCategoryResponse createSkillsCategory(SkillsCategoryRequest request);
    List<SkillsCategoryResponse> getAllSkillsCategories();
    SkillsCategoryResponse getSkillsCategoryById(Long id);

    SkillsCategoryResponse updateSkillsCategory(SkillsCategoryRequest request, Long id);

    void deleteSkillsCategory(Long id);
}
