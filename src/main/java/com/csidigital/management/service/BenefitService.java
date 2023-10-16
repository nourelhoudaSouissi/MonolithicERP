package com.csidigital.management.service;

import com.csidigital.shared.dto.request.BenefitRequest;
import com.csidigital.shared.dto.response.BenefitResponse;
import com.csidigital.shared.dto.response.ExtraDutyResponse;
import com.csidigital.shared.dto.response.WorkArrangementResponse;

import java.util.List;

public interface BenefitService {
    BenefitResponse createBenefit(BenefitRequest request);
    List<BenefitResponse> getAllBenefits();
    BenefitResponse getBenefitById(Long id);
    List<WorkArrangementResponse> getBenefitWorkArrangementsById(Long id) ;
    List<ExtraDutyResponse> getBenefitExtraDutiesById(Long id) ;
    BenefitResponse updateBenefit(BenefitRequest request, Long id);

    void deleteBenefit(Long id);
}
