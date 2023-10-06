package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.EducationService;
import com.csidigital.shared.dto.request.EducationRequest;
import com.csidigital.shared.dto.response.EducationResponse;
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

public class EducationImpl implements EducationService {
   @Autowired
   private ModelMapper modelMapper ;
   @Autowired
   private EducationRepository educationRepository ;

   @Autowired
   private TechnicalFileRepository technicalFileRepository;
    @Override
    public EducationResponse createEducation(EducationRequest request) {
        TechnicalFile technicalFile = technicalFileRepository.findById(request.getTechnicalFileNum()).orElseThrow();
        Education education = modelMapper.map(request, Education.class);
        education.setTechnicalFile(technicalFile);
        Education educationSaved = educationRepository.save(education);
        return modelMapper.map(educationSaved, EducationResponse.class);
    }

    @Override
    public List<EducationResponse> getAllEducations() {
        List<Education> educations = educationRepository.findAll();
        List<EducationResponse> educationList = new ArrayList<>();

        for (Education education : educations) {
            EducationResponse response = modelMapper.map(education, EducationResponse.class);
            educationList.add(response);
        }

        return educationList;
    }

    @Override
    public EducationResponse getEducationById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Education with id " +id+ " not found"));
        EducationResponse educationResponse = modelMapper.map(education, EducationResponse.class);
        return educationResponse;
    }

    @Override
    public EducationResponse updateEducation(EducationRequest request, Long id) {
        TechnicalFile technicalFile = technicalFileRepository.findById(request.getTechnicalFileNum()).orElseThrow();
        Education existingEducation = educationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Education with id: " + id + " not found"));
        modelMapper.map(request, existingEducation);
        Education savedEducation = educationRepository.save(existingEducation);
        existingEducation.setTechnicalFile(technicalFile);
        return modelMapper.map(savedEducation, EducationResponse.class);
    }

    @Override
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}
