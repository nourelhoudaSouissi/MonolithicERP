package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.ProfileUpdated;
import com.csidigital.dao.entity.Quotation;
import com.csidigital.dao.repository.ProfileUpdatedRepository;
import com.csidigital.dao.repository.QuotationRepository;
import com.csidigital.dao.repository.RequirementRepository;
import com.csidigital.management.service.ProfileUpdatedService;
import com.csidigital.shared.dto.request.ProfileUpdatedRequest;
import com.csidigital.shared.dto.response.ProfileUpdatedResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfileUpdatedServiceImpl implements ProfileUpdatedService {
    @Autowired
    private ProfileUpdatedRepository profileUpdatedRepository;
    @Autowired
    private RequirementRepository requirementRepository ;
    @Autowired
    private QuotationRepository quotationRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProfileUpdatedResponse createRequestedProfile(ProfileUpdatedRequest request) {
        System.out.println(request);
        //System.out.println(request.getRequirementNum());
        System.out.println(request.getQuotationNum());
        /*Requirement requirement = requirementRepository.findById(request.getRequirementNum())
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));*/
        Quotation quotation = quotationRepository.findById(request.getQuotationNum())
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));
        ProfileUpdated profileUpdated = modelMapper.map(request, ProfileUpdated.class);
        /*Period period = Period.between(profileUpdated.getStartDate(), profileUpdated.getEndDate());
        requestedProfile.setPeriod(period);*/
        profileUpdated.setQuotation(quotation);
        ProfileUpdated profileUpdatedSaved = profileUpdatedRepository.save(profileUpdated);
        return modelMapper.map(profileUpdatedSaved, ProfileUpdatedResponse.class);
    }

    @Override
    public List<ProfileUpdatedResponse> getAllRequestedProfiles() {
        List<ProfileUpdated> profileUpdateds = profileUpdatedRepository.findAll();
        List<ProfileUpdatedResponse> requestedProfileList = new ArrayList<>();

        for (ProfileUpdated profileUpdated : profileUpdateds) {
            ProfileUpdatedResponse response = modelMapper.map(profileUpdated, ProfileUpdatedResponse.class);
            requestedProfileList.add(response);
        }

        return requestedProfileList;
    }

    @Override
    public ProfileUpdatedResponse getRequestedProfileById(Long id) {
        ProfileUpdated profileUpdated = profileUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Requested Profile with id " +id+ " not found"));
        ProfileUpdatedResponse profileUpdatedResponse = modelMapper.map(profileUpdated, ProfileUpdatedResponse.class);
        return profileUpdatedResponse;
    }

    @Override
    public ProfileUpdatedResponse updateRequestedProfile(ProfileUpdatedRequest request, Long id) {
        ProfileUpdated existingProfileUpdated = profileUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("RequestedProfile with id: " + id + " not found"));
        modelMapper.map(request, existingProfileUpdated);
        ProfileUpdated savedProfileUpdated = profileUpdatedRepository.save(existingProfileUpdated);
        return modelMapper.map(savedProfileUpdated, ProfileUpdatedResponse.class);
    }

    @Override
    public void deleteRequestedProfile(Long id) {
        profileUpdatedRepository.deleteById(id);
    }
}
