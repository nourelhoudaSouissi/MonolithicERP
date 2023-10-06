package com.csidigital.management.service;

import com.csidigital.shared.dto.request.QuestionTypeRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.enumeration.ExperienceLevel;

import java.util.List;

public interface QuestionTypeService {
    QuestionTypeResponse createQuestionType(QuestionTypeRequest request);
    List<QuestionTypeResponse> getAllQuestionTypes();
    QuestionTypeResponse getQuestionTypeById(Long id);
    List<QuestionCategoryResponse> getQuestionCategoryByType(Long id);
    List<QuestionResponse> getQuestionsByCategoryAndType(Long id ,Long Id);
    List<QuestionResponse> getQuestionByTypeCategoryAndLevel(Long id, Long Id, ExperienceLevel level);


    QuestionTypeResponse updateQuestionType(QuestionTypeRequest request, Long id);

    void deleteQuestionType(Long id);
}
