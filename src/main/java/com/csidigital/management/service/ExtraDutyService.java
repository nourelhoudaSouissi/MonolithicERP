package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ExtraDutyRequest;
import com.csidigital.shared.dto.response.ExtraDutyResponse;

import java.util.List;

public interface ExtraDutyService {
    ExtraDutyResponse createExtraDuty(ExtraDutyRequest request);
    List<ExtraDutyResponse> getAllExtraDuties();
    ExtraDutyResponse getExtraDutyById(Long id);

    ExtraDutyResponse updateExtraDuty(ExtraDutyRequest request, Long id);

    void deleteExtraDuty(Long id);
}
