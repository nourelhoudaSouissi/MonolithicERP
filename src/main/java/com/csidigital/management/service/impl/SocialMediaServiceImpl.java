package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.entity.SocialMedia;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.dao.repository.SocialMediaRepository;
import com.csidigital.management.service.SocialMediaService;
import com.csidigital.shared.dto.request.SocialMediaRequest;
import com.csidigital.shared.dto.response.SocialMediaResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {
    @Autowired
    private SocialMediaRepository socialMediaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public SocialMediaResponse createSocialMedia(SocialMediaRequest request) {
        Partner partner = partnerRepository.findById(request.getPartnerNum())
                .orElseThrow();
        SocialMedia socialMedia = modelMapper.map(request, SocialMedia.class);
        socialMedia.setPartner(partner);
        SocialMedia socialMediaSaved = socialMediaRepository.save(socialMedia);
        return modelMapper.map(socialMediaSaved, SocialMediaResponse.class);
    }

    @Override
    public List<SocialMediaResponse> getAllSocialMedias() {
        List<SocialMedia> socialMedias = socialMediaRepository.findAll();
        List<SocialMediaResponse> socialMediaList = new ArrayList<>();

        for (SocialMedia socialMedia : socialMedias) {
            SocialMediaResponse response = modelMapper.map(socialMedia, SocialMediaResponse.class);
            socialMediaList.add(response);
        }
        return socialMediaList;
    }


    @Override
    public SocialMediaResponse getSocialMediaById(Long id) {
        SocialMedia socialMedia = socialMediaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("SocialMedia with id " +id+ " not found"));
        SocialMediaResponse socialMediaResponse = modelMapper.map(socialMedia, SocialMediaResponse.class);
        return socialMediaResponse;
    }

    @Override
    public SocialMediaResponse updateSocialMedia(SocialMediaRequest request, Long id) {
        SocialMedia existingSocialMedia = socialMediaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("SocialMedia with id: " + id + " not found"));
        modelMapper.map(request, existingSocialMedia);
        SocialMedia savedSocialMedia = socialMediaRepository.save(existingSocialMedia);
        return modelMapper.map(savedSocialMedia, SocialMediaResponse.class);
    }

    @Override
    public void deleteSocialMedia(Long id) {
        socialMediaRepository.deleteById(id);
    }
}