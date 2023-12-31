package com.csidigital.management.service;

import com.csidigital.shared.dto.request.QuestionRequest;
import com.csidigital.shared.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionService {
    QuestionResponse createQuestion(QuestionRequest request);
    List<QuestionResponse> getAllQuestion();
    QuestionResponse getQuestionById(Long id);

    QuestionResponse updateQuestion(QuestionRequest request, Long id);

    void deleteQuestion(Long id);

}
