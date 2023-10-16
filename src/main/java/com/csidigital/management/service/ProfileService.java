package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ProfileRequest;
import com.csidigital.shared.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request);
    List<ProfileResponse> getAllProfiles();
    ProfileResponse getProfileById(Long id);
    ProfileResponse updateProfile(ProfileRequest request, Long id );

    void deleteProfile(Long id);
}
