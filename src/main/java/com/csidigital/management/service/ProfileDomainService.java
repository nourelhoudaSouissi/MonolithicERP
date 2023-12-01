package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ProfileDomainRequest;
import com.csidigital.shared.dto.request.WeekendRequest;
import com.csidigital.shared.dto.response.ProfileDomainResponse;
import com.csidigital.shared.dto.response.WeekendResponse;

import java.util.List;

public interface ProfileDomainService {
    ProfileDomainResponse createProfileDomain(ProfileDomainRequest request);
    List<ProfileDomainResponse> getAllProfileDomains();
    ProfileDomainResponse getProfileDomainById(Long id);

    ProfileDomainResponse updateProfileDomain(ProfileDomainRequest request, Long id);

    void deleteProfileDomain(Long id);
}
