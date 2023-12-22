package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.CalculationUnitRepository;
import com.csidigital.dao.repository.CatalogRepository;
import com.csidigital.dao.repository.ProfileDomainRepository;
import com.csidigital.dao.repository.ProfileRepository;
import com.csidigital.management.service.ProfileService;
import com.csidigital.shared.dto.request.ProfileRequest;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository ;
    @Autowired
    private CatalogRepository catalogRepository ;

    @Autowired
    private ProfileDomainRepository profileDomainRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CalculationUnitRepository calculationUnitRepository ;
  /*  @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        ProfileDomain profileDomain = null;
        if (request.getProfileDomainNum() != null) {
            // Charger le ProfileDomain à partir de l'ID
            profileDomain = profileDomainRepository.findById(request.getProfileDomainNum())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile Domain not found"));
        }


        System.out.println(request);
        System.out.println(request.getCatalogNum());
        System.out.println(request.getProfileDomainNum());
        Catalog catalog = catalogRepository.findById(request.getCatalogNum())
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found"));
        Profile profile = modelMapper.map(request, Profile.class);
        profile.setCatalog(catalog);
        profile.setProfileDomain(profileDomain);
        Profile profileSaved = profileRepository.save(profile);
        return modelMapper.map(profileSaved, ProfileResponse.class);
    }*/

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        ProfileDomain profileDomain = null;
        if (request.getProfileDomainNum() != null) {
            profileDomain = profileDomainRepository.findById(request.getProfileDomainNum())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile Domain not found"));
        }

        CalculationUnit calculationUnit = null;
        if (request.getCalculationUnitNum() != null) {
            calculationUnit = calculationUnitRepository.findById(request.getCalculationUnitNum())
                    .orElseThrow(() -> new ResourceNotFoundException("CalculationUnit not found for the given ID"));
        }

        Catalog catalog = catalogRepository.findById(request.getCatalogNum())
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found"));

        Profile profile = modelMapper.map(request, Profile.class);

        if (calculationUnit != null) {
            profile.setCalculationUnit(calculationUnit);
        } else {
            throw new ResourceNotFoundException("CalculationUnit not found for the given ID");
        }

        profile.setCatalog(catalog);
        if (profileDomain != null) {
            profile.setProfileDomain(profileDomain);
        } else {
            throw new ResourceNotFoundException("ProfileDomain not found for the given ID");
        }
        Profile profileSaved = profileRepository.save(profile);
        return modelMapper.map(profileSaved, ProfileResponse.class);
    }



    @Override
    public List<ProfileResponse> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileResponse> profileList = new ArrayList<>();

        for (Profile profile : profiles) {
            ProfileResponse response = modelMapper.map(profile, ProfileResponse.class);
            profileList.add(response);
        }

        return profileList;
    }

    @Override
    public ProfileResponse getProfileById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Requested Profile with id " +id+ " not found"));
        ProfileResponse profileResponse = modelMapper.map(profile, ProfileResponse.class);
        return profileResponse;
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest request, Long id) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Profile with id: " + id + " not found"));
        modelMapper.map(request, existingProfile);
        Profile savedProfile = profileRepository.save(existingProfile);
        return modelMapper.map(savedProfile, ProfileResponse.class);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
