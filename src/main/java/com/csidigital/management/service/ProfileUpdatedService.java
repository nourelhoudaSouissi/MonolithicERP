package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ProfileUpdatedRequest;
import com.csidigital.shared.dto.response.ProfileUpdatedResponse;

import java.util.List;

public interface ProfileUpdatedService {
   ProfileUpdatedResponse createRequestedProfile(ProfileUpdatedRequest profileUpdatedRequest);
    List<ProfileUpdatedResponse> getAllRequestedProfiles();
    ProfileUpdatedResponse getRequestedProfileById(Long id);

    ProfileUpdatedResponse updateRequestedProfile(ProfileUpdatedRequest profileUpdatedRequest, Long id );

    void deleteRequestedProfile(Long id);
}
