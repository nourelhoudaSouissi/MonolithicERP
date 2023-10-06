package com.csidigital.management.service;

import com.csidigital.shared.dto.request.AssQuestionInterviewRequest;
import com.csidigital.shared.dto.response.AssQuestionInterviewResponse;

import java.util.List;

public interface AssQuestionInterviewService {
    AssQuestionInterviewResponse createAssQuestionInterview(AssQuestionInterviewRequest request);
    List<AssQuestionInterviewResponse> getAllAssQuestionInterview();
    AssQuestionInterviewResponse getAssQuestionInterviewDataById(Long id);

    AssQuestionInterviewResponse updateAssQuestionInterview(AssQuestionInterviewRequest request, Long id);

    void deleteAssQuestionInterviewData(Long id);

}
