package com.csidigital.management.service;

import com.csidigital.shared.dto.request.EvaluationRequest;
import com.csidigital.shared.dto.response.EmployeeResponse;
import com.csidigital.shared.dto.response.EvaluationResponse;
import com.csidigital.shared.dto.response.InterviewResponse;

import java.util.List;

public interface EvaluationService {
    void calculateGlobalAppreciation(Long evaluationId);
    EvaluationResponse createEvaluation(EvaluationRequest request);
    List<EvaluationResponse> getAllEvaluations();
    EvaluationResponse getEvaluationById(Long id);
    EmployeeResponse getEmployeeByEvaluationId(Long id);

    //EvaluationResponse calculateGlobalAppreciation(EvaluationRequest request);
    List<InterviewResponse> getEvaluationInterviews(Long id);


    EvaluationResponse updateEvaluation(EvaluationRequest request, Long id);

    void deleteEvaluation(Long id);
}