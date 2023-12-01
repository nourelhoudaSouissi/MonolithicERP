package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.ProfileDomain;
import com.csidigital.dao.entity.Weekend;
import com.csidigital.dao.repository.ProfileDomainRepository;
import com.csidigital.management.service.ProfileDomainService;
import com.csidigital.shared.dto.request.ProfileDomainRequest;
import com.csidigital.shared.dto.response.ProfileDomainResponse;
import com.csidigital.shared.dto.response.WeekendResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProfileDomainServiceImpl implements ProfileDomainService {
    @Autowired
    private ProfileDomainRepository profileDomainRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProfileDomainResponse createProfileDomain(ProfileDomainRequest request) {
        ProfileDomain profileDomain = modelMapper.map(request, ProfileDomain.class);
        ProfileDomain savedProfileDomain = profileDomainRepository.save(profileDomain);
        return modelMapper.map(savedProfileDomain, ProfileDomainResponse.class);
    }

    @Override
    public List<ProfileDomainResponse> getAllProfileDomains() {
        List<ProfileDomain> profileDomains = profileDomainRepository.findAll();
        List<ProfileDomainResponse> profileDomainResponses = new ArrayList<>();


        for (ProfileDomain profileDomain: profileDomains) {
            ProfileDomainResponse response = modelMapper.map(profileDomain, ProfileDomainResponse.class);
            profileDomainResponses.add(response);
        }

        return profileDomainResponses;
    }

    @Override
    public ProfileDomainResponse getProfileDomainById(Long id) {
        ProfileDomain profileDomain = profileDomainRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ProfileDomain with id " +id+ " not found"));
        ProfileDomainResponse profileDomainResponse = modelMapper.map(profileDomain, ProfileDomainResponse.class);
        return profileDomainResponse;
    }

    @Override
    public ProfileDomainResponse updateProfileDomain(ProfileDomainRequest request, Long id) {
        ProfileDomain existingProfileDomain = profileDomainRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ProfileDomain with id: " + id + " not found"));
        modelMapper.map(request, existingProfileDomain);
        ProfileDomain savedProfileDomain = profileDomainRepository.save(existingProfileDomain);
        return modelMapper.map(savedProfileDomain, ProfileDomainResponse.class);
    }

    @Override
    public void deleteProfileDomain(Long id) {
        profileDomainRepository.deleteById(id);

    }
}
