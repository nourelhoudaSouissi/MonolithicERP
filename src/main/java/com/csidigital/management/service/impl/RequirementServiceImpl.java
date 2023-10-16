package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.entity.Requirement;
import com.csidigital.dao.entity.RequirementRefSequence;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.dao.repository.ProfileUpdatedRepository;
import com.csidigital.dao.repository.RequirementRepository;
import com.csidigital.dao.repository.RequirementSequenceRepository;
import com.csidigital.management.service.RequirementService;
import com.csidigital.shared.dto.request.RequirementRequest;
import com.csidigital.shared.dto.response.PartnerResponse;
import com.csidigital.shared.dto.response.RequirementResponse;
import com.csidigital.shared.enumeration.RequirementStatus;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private RequirementRepository requirementRepository ;
    @Autowired
    private ProfileUpdatedRepository profileRepository ;
    @Autowired
    private ModelMapper modelMapper;
    private String requirementReference;
    @Autowired
    private RequirementSequenceRepository sequenceRepository;
    @Autowired
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public RequirementResponse createRequirement(RequirementRequest request) {
        System.out.println(request.getPartnerNum());
        Partner partner = partnerRepository.findById(request.getPartnerNum())
                    .orElseThrow(() -> new ResourceNotFoundException("Partner not found"));

        RequirementRefSequence sequence = new RequirementRefSequence();
        sequenceRepository.save(sequence);

        Requirement requirement = modelMapper.map(request, Requirement.class);
        requirementReference = String.format("OP_%07d", sequence.getId());
        requirement.setRef(requirementReference);
        requirement.setRequirementStatus(RequirementStatus.OPEN);
        requirement.setCreationDate(LocalDate.now());
        requirement.setPartner(partner);
        /*sequence.incrementNextValue();
        sequenceRepository.save(sequence);*/
        Requirement savedRequirement = requirementRepository.save(requirement);
        System.out.println(savedRequirement);
        return modelMapper.map(savedRequirement, RequirementResponse.class);

    }

    @Override
    public List<RequirementResponse> getAllRequirements() {
        List<Requirement> requirements = requirementRepository.findAll();
        List<RequirementResponse> requirementList = new ArrayList<>();

        for (Requirement requirement : requirements) {
            RequirementResponse response = modelMapper.map(requirement, RequirementResponse.class);

            //convertDates(requirement, response);

            requirementList.add(response);
        }
        return requirementList;
    }

    @Override
    public RequirementResponse getRequirementById(Long id) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Requirement with id " +id+ " not found"));
        RequirementResponse response = modelMapper.map(requirement, RequirementResponse.class);

        //convertDates(requirement, response);

        return response;
    }

    @Override
    public List<RequirementResponse> getByPartnerId(Long id) {
        List<Requirement> partnerRequirementList = requirementRepository.findByPartnerId(id);
        List<RequirementResponse> requirementList = new ArrayList<>();
        for (Requirement requirement : partnerRequirementList) {
            RequirementResponse response = modelMapper.map(requirement, RequirementResponse.class);
            requirementList.add(response);
        }
        return requirementList;
    }

    @Override
    public PartnerResponse getPartnerByReqId(Long reqId) {
        Requirement requirement = requirementRepository.findById(reqId)
                .orElseThrow(()-> new ResourceNotFoundException("Requirement with id " +reqId+ " not found"));
        return modelMapper.map(requirement.getPartner(), PartnerResponse.class);
    }

    /*@Override
    public List<ProfileUpdatedResponse> getRequirementRequestedProfilesById(Long id) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Requirement with id " +id+ " not found"));
        List<ProfileUpdated> profiles = requirement.getProfiles();
        List<ProfileUpdatedResponse> profilesList = new ArrayList<>();
        for (ProfileUpdated profile : profiles) {
            ProfileUpdatedResponse response = modelMapper.map(profile, ProfileUpdatedResponse.class);
            profilesList.add(response);
        }
        return profilesList;
    }*/

    @Override
    public RequirementResponse updateRequirement(RequirementRequest request, Long id){
        Requirement existingRequirement = requirementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Requirement with id: " + id + " not found"));

        modelMapper.map(request, existingRequirement);
        Requirement requirement = requirementRepository.save(existingRequirement);

        /*List<ProfileUpdated> profiles = request.getProfiles();

        for (ProfileUpdated profile :
             profiles) {
            profile.setRequirement(requirement);
            profileRepository.save(profile);
        }*/
        return modelMapper.map(requirement, RequirementResponse.class);
    }

    @Override
    public void deleteRequirement(Long id) {
        requirementRepository.deleteById(id);
    }

    @Override
    public void convertDates(Requirement requirement, RequirementResponse response) {
        // Convert the startDate to the desired format
        if(requirement.getStartDate()!=null) {
            String formattedStartDate = requirement.getStartDate().format(dateFormatter);
            response.setStartDate(formattedStartDate);
        }
        // Convert the responseDate to the desired format
        if(requirement.getResponseDate()!=null) {
            String formattedResponseDate = requirement.getResponseDate().format(dateFormatter);
            response.setResponseDate(formattedResponseDate);
        }
        // Convert the creationDate to the desired format
        if(requirement.getCreationDate()!=null) {
            String formattedCreationDate = requirement.getCreationDate().format(dateFormatter);
            response.setCreationDate(formattedCreationDate);
        }
        // Convert the closureDate to the desired format
        if(requirement.getClosureDate()!=null) {
            String formattedClosureDate = requirement.getClosureDate().format(dateFormatter);
            response.setClosureDate(formattedClosureDate);
        }
    }

    /*@Override
    public List<ProfileUpdatedResponse> getReqProfilesById(Long id) {
        Requirement requirement = requirementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Requirement with id " +id+ " not found"));
        List<ProfileUpdated> profiles = requirement.getProfiles();
        List<ProfileUpdatedResponse> profileList = new ArrayList<>();
        for (ProfileUpdated profile : profiles) {
            ProfileUpdatedResponse response = modelMapper.map(profile, ProfileUpdatedResponse.class);

            profileList.add(response);
        }
        return profileList;
    }*/

    @Override
    public String mapPeriodToReadableString(Period period) {
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        StringBuilder result = new StringBuilder();

        if (years > 0) {
            result.append(years).append(" année").append(years > 1 ? "s" : "").append(" ");
        }

        if (months > 0) {
            result.append(months).append(" mois").append(" ");
        }

        if (days > 0) {
            result.append(days).append(" jour").append(days > 1 ? "s" : "").append(" ");
        }

        // Remove the trailing space and return the final string
        return result.toString().trim();
    }

    @Override
    public void updateStatusToOpen(Long id) {
        requirementRepository.updateStatusToOpen(id);
    }

    @Override
    public void updateStatusToInProgress(Long id) {
        requirementRepository.updateStatusToInProgress(id);
    }

    @Override
    public void updateStatusToClosed(Long id) {
        requirementRepository.updateStatusToClosed(id);
    }

    @Override
    public void updateStatusToPartiallyWon(Long id) {
        requirementRepository.updateStatusToPartiallyWon(id);
    }

    @Override
    public void updateStatusToTotallyWon(Long id) {
        requirementRepository.updateStatusToTotallyWon(id);
    }

    @Override
    public void updateStatusToPartiallyLost(Long id) {
        requirementRepository.updateStatusToPartiallyLost(id);
    }

    @Override
    public void updateStatusToTotallyLost(Long id) {
        requirementRepository.updateStatusToTotallyLost(id);
    }

    @Override
    public void updateStatusToAbandoned(Long id) {
        requirementRepository.updateStatusToAbandoned(id);
    }
}


