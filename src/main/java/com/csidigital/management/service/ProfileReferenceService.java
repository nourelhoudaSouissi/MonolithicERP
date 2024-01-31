package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ProfileReferenceRequest;
import com.csidigital.shared.dto.response.ProfileReferenceResponse;

import java.util.List;

public interface ProfileReferenceService {
    ProfileReferenceResponse createProfileReference(ProfileReferenceRequest request);
    List<ProfileReferenceResponse> getAllProfileReferences();
    ProfileReferenceResponse getProfileReferenceById(Long id);
    ProfileReferenceResponse updateProfileReference(ProfileReferenceRequest request, Long id );

    void deleteProfileReference(Long id);
}
