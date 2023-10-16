package com.csidigital.management.service;

import com.csidigital.shared.dto.request.WorkArrangementRequest;
import com.csidigital.shared.dto.response.WorkArrangementResponse;

import java.util.List;

public interface WorkArrangementService {
    WorkArrangementResponse createWorkArrangement(WorkArrangementRequest workArrangementRequest);
    List<WorkArrangementResponse> getAllWorkArrangements();
    WorkArrangementResponse getWorkArrangementById(Long id);

    WorkArrangementResponse updateWorkArrangement(WorkArrangementRequest workArrangementRequest, Long id );

    void deleteWorkArrangement(Long id);
}
