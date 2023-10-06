package com.csidigital.management.service;

import com.csidigital.shared.dto.request.WeekendUpdatedRequest;
import com.csidigital.shared.dto.response.WeekendUpdatedResponse;

import java.util.List;

public interface WeekendUpdatedService {
    WeekendUpdatedResponse createWeekendUpdated(WeekendUpdatedRequest request);
    List<WeekendUpdatedResponse> getAllWeekendUpdated();
    WeekendUpdatedResponse getWeekendUpdatedById(Long id);

    WeekendUpdatedResponse updateWeekendUpdated(WeekendUpdatedRequest request, Long id);

    void deleteWeekendUpdated(Long id);

}
