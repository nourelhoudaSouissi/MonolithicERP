package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.ProfileReference;
import com.csidigital.dao.entity.Weekend;
import com.csidigital.dao.repository.ProfileReferenceRepository;
import com.csidigital.dao.repository.ProfileRepository;
import com.csidigital.management.service.ProfileReferenceService;
import com.csidigital.shared.dto.request.ProfileReferenceRequest;
import com.csidigital.shared.dto.response.ProfileReferenceResponse;
import com.csidigital.shared.dto.response.WeekendResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfileReferenceServiceImpl implements ProfileReferenceService {
    @Autowired
    private ProfileReferenceRepository profileReferenceRepository ;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProfileReferenceResponse createProfileReference(ProfileReferenceRequest request) {
        ProfileReference profileReference = modelMapper.map(request, ProfileReference.class);
        ProfileReference savedProfileReference = profileReferenceRepository.save(profileReference);
        return modelMapper.map(savedProfileReference, ProfileReferenceResponse.class);
    }

    @Override
    public List<ProfileReferenceResponse> getAllProfileReferences() {
        List<ProfileReference> profileReferences = profileReferenceRepository.findAll();
        List<ProfileReferenceResponse> profileReferenceResponses = new ArrayList<>();


        for (ProfileReference profileReference: profileReferences) {
            ProfileReferenceResponse response = modelMapper.map(profileReference, ProfileReferenceResponse.class);
            profileReferenceResponses.add(response);
        }

        return profileReferenceResponses;

    }

    @Override
    public ProfileReferenceResponse getProfileReferenceById(Long id) {
        ProfileReference profileReference = profileReferenceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Weekend with id " +id+ " not found"));
        ProfileReferenceResponse profileReferenceResponse = modelMapper.map(profileReference, ProfileReferenceResponse.class);
        return profileReferenceResponse;    }

    @Override
    public ProfileReferenceResponse updateProfileReference(ProfileReferenceRequest request, Long id) {
        ProfileReference existingProfileReference = profileReferenceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ProfileReference with id: " + id + " not found"));
        modelMapper.map(request, existingProfileReference);
        ProfileReference savedProfileReference = profileReferenceRepository.save(existingProfileReference);
        return modelMapper.map(savedProfileReference, ProfileReferenceResponse.class);
    }

    @Override
    public void deleteProfileReference(Long id) {
        profileReferenceRepository.deleteById(id);
    }
}
