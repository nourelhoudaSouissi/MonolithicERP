package com.csidigital.management.service;

import com.csidigital.shared.dto.request.SocialMediaRequest;
import com.csidigital.shared.dto.response.SocialMediaResponse;

import java.util.List;

public interface SocialMediaService {
    SocialMediaResponse createSocialMedia(SocialMediaRequest request);
    List<SocialMediaResponse> getAllSocialMedias();
    SocialMediaResponse getSocialMediaById(Long id);

    SocialMediaResponse updateSocialMedia(SocialMediaRequest SocialMediaRequest , Long id );

    void deleteSocialMedia(Long id);
}