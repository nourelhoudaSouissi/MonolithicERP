package com.csidigital.management.service;

import com.csidigital.shared.dto.request.WeekendRequest;
import com.csidigital.shared.dto.response.WeekendResponse;

import java.util.List;

public interface WeekendService {
    WeekendResponse createWeekend(WeekendRequest request);
    List<WeekendResponse> getAllWeekend();
    WeekendResponse getWeekendById(Long id);

    WeekendResponse updateWeekend(WeekendRequest request, Long id);

    void deleteWeekend(Long id);
}
