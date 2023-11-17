package com.csidigital.management.service;

import com.csidigital.dao.entity.Requirement;
import com.csidigital.shared.dto.request.RequirementRequest;
import com.csidigital.shared.dto.response.PartnerResponse;
import com.csidigital.shared.dto.response.RequirementResponse;
import org.springframework.data.repository.query.Param;

import java.time.Period;
import java.util.List;

public interface RequirementService {
    RequirementResponse createRequirement(RequirementRequest request );
    List<RequirementResponse> getAllRequirements();
    RequirementResponse getRequirementById(Long id);
    List<RequirementResponse> getByPartnerId(Long id);
    PartnerResponse getPartnerByReqId(Long reqId);
    //List<ProfileUpdatedResponse> getRequirementRequestedProfilesById(Long id);
    RequirementResponse updateRequirement(RequirementRequest request, Long id);
    void deleteRequirement(Long id);
    void convertDates(Requirement requirement, RequirementResponse response);
    //List<ProfileUpdatedResponse> getReqProfilesById(Long id);

    String mapPeriodToReadableString(Period period);
    void updateStatusToOpen(Long id);
    void updateStatusToInProgress(Long id);
    void updateStatusToClosed(Long id);
    void updateStatusToPartiallyWon(Long id);
    void updateStatusToTotallyWon(Long id);
    void updateStatusToPartiallyLost(Long id);
    void updateStatusToTotallyLost(Long id);
    void updateStatusToAbandoned(Long id);
    Double getBugetTotal(Long id);


}
