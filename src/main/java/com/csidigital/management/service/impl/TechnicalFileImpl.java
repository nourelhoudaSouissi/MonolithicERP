package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.TechnicalFileService;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.dto.request.*;
import com.csidigital.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
@Transactional
public class TechnicalFileImpl implements TechnicalFileService {
    @Autowired
    private TechnicalFileRepository technicalFileRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

  /*  @Override
    public TechnicalFileResponse createTechnicalFile(TechnicalFileRequest request) {
        Employee employee =  employeeRepository.findById(request.getEmployeeNum()).orElseThrow();
        TechnicalFile technicalFile= modelMapper.map(request, TechnicalFile.class);
        technicalFile.setEmployee(employee);
        TechnicalFile technicalFileSaved = technicalFileRepository.save(technicalFile);
        return modelMapper.map(technicalFileSaved, TechnicalFileResponse .class);
    }*/
  @Override
  public TechnicalFileResponse createTechnicalFile(TechnicalFileRequest request) {
      Employee employee = employeeRepository.findById(request.getEmployeeNum()).orElseThrow();

      // Extraction des lettres
      String firstName = employee.getFirstName();
      String lastName = employee.getLastName();
      char firstInitial = firstName.charAt(0);
      char lastInitial = lastName.charAt(0);
      char lastLetter = lastName.charAt(lastName.length() - 1);

      // Génération de la référence en majuscules
      String reference = String.format("%c%c%c", firstInitial, lastInitial, lastLetter).toUpperCase();

      TechnicalFile technicalFile = modelMapper.map(request, TechnicalFile.class);
      technicalFile.setEmployee(employee);
      technicalFile.setReference(reference); // Attribution de la référence générée
      TechnicalFile technicalFileSaved = technicalFileRepository.save(technicalFile);

      return modelMapper.map(technicalFileSaved, TechnicalFileResponse.class);
  }



    @Override
    public List<TechnicalFileResponse> getAllTechnicalFiles() {
        List<TechnicalFile> technicalFiles = technicalFileRepository.findAll();
        List<TechnicalFileResponse> technicalFileResponseList = new ArrayList<>();

        for (TechnicalFile technicalFile : technicalFiles) {
            TechnicalFileResponse response = modelMapper.map(technicalFile, TechnicalFileResponse.class);
            technicalFileResponseList.add(response);
        }

        return technicalFileResponseList;
    }

    @Override
    public TechnicalFileResponse  getTechnicalFileById(Long id) {
        TechnicalFile technicalFile = technicalFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TechnicalFile with id " + id + " not found"));
        TechnicalFileResponse  technicalFileResponse = modelMapper.map(technicalFile, TechnicalFileResponse.class);
        return technicalFileResponse;
    }

    @Override
    public TechnicalFileResponse getTechnicalFileByEmployeeId(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(("Employee with id "+id+" not found")));
        TechnicalFile technicalFile = employee.getTechnicalFile();
        return modelMapper.map(technicalFile, TechnicalFileResponse.class);
    }




        @Override
        public List<EducationResponse> getTechnicalFileEducation(Long id) {
            TechnicalFile technicalFile = technicalFileRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException(("Education with id "+id+" not found")));
             List<Education> educations = technicalFile.getEducations();
            List<EducationResponse> educationResponseList = new ArrayList<>();

            for (Education education : educations) {
                EducationResponse response = modelMapper.map(education, EducationResponse.class);
                educationResponseList.add(response);
            }

            return educationResponseList;
        }

    @Override
    public TechnicalFileResponse  updateTechnicalFile(TechnicalFileRequest request, Long id) {
        TechnicalFile existingTechnicalFile = technicalFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TechnicalFile with id: " + id + " not found"));
        modelMapper.map(request, existingTechnicalFile);
        TechnicalFile savedTechnicalFile = technicalFileRepository.save(existingTechnicalFile);
        return modelMapper.map(savedTechnicalFile, TechnicalFileResponse .class);
    }

    @Override
    public void deleteTechnicalFile(Long id) {technicalFileRepository.deleteById(id);
    }
}
