package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.ExperienceService;
import com.csidigital.shared.dto.request.ExperienceRequest;
import com.csidigital.shared.dto.response.ExperienceResponse;
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
public class ExperienceImpl implements ExperienceService {
    @Autowired 
    private ExperienceRepository experienceRepository ;
    @Autowired 
    private ModelMapper modelMapper ;

    @Autowired
    TechnicalFileRepository technicalFileRepository ;

    @Override
    public ExperienceResponse createExperience(ExperienceRequest request) {
        TechnicalFile technicalFile= technicalFileRepository.findById(request.getTechnicalFileNum()).orElseThrow();
        Experience experience = modelMapper.map(request, Experience.class);
        experience.setTechnicalFile(technicalFile);
        Experience experienceSaved = experienceRepository.save(experience);
        return modelMapper.map(experienceSaved, ExperienceResponse.class);
    }

    @Override
    public List<ExperienceResponse> getAllExperiences() {
        List<Experience> experiences = experienceRepository.findAll();
        List<ExperienceResponse> ExperienceList = new ArrayList<>();

        for (Experience experience : experiences) {
            ExperienceResponse response = modelMapper.map(experience, ExperienceResponse.class);
            ExperienceList.add(response);
        }

        return ExperienceList;
    }

    @Override
    public ExperienceResponse getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Experience with id " +id+ " not found"));
        ExperienceResponse experienceResponse = modelMapper.map(experience, ExperienceResponse.class);
        return experienceResponse;
    }

    @Override
    public ExperienceResponse updateExperience(ExperienceRequest request, Long id) {
        TechnicalFile technicalFile = technicalFileRepository.findById(request.getTechnicalFileNum()).orElseThrow();
        Experience existingExperience = experienceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Experience with id: " + id + " not found"));
        modelMapper.map(request, existingExperience);
        Experience savedExperience = experienceRepository.save(existingExperience);
        existingExperience.setTechnicalFile(technicalFile);
        return modelMapper.map(savedExperience, ExperienceResponse.class);
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);

    }
}
