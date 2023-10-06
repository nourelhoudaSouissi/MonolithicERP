package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.LanguageService;
import com.csidigital.shared.dto.request.LanguageRequest;
import com.csidigital.shared.dto.response.LanguageResponse;
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
public class LanguageImpl implements LanguageService {
    @Autowired
    private LanguageRepository languageRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TechnicalFileRepository technicalFileRepository ;

    @Override
    public LanguageResponse createLanguage(LanguageRequest request) {
        TechnicalFile technicalFile= technicalFileRepository.findById(request.getTechnicalFileNum()).orElseThrow();
        Language language = modelMapper.map(request, Language.class);
        language.setTechnicalFile(technicalFile);
        Language LanguageSaved = languageRepository.save(language);
        return modelMapper.map(LanguageSaved, LanguageResponse.class);
    }

    @Override
    public List<LanguageResponse> getAllLanguage() {
        List<Language> language = languageRepository.findAll();
        List<LanguageResponse> languageList = new ArrayList<>();


        for (Language languages: language) {
            LanguageResponse response = modelMapper.map(languages, LanguageResponse.class);
            languageList.add(response);
        }

        return languageList;
    }

    @Override
    public LanguageResponse getLanguageById(Long id) {
        Language language =languageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Language with id " +id+ " not found"));
        LanguageResponse languageResponse = modelMapper.map(language, LanguageResponse.class);
        return languageResponse;
    }

    @Override
    public LanguageResponse updateLanguage(LanguageRequest request, Long id) {
        TechnicalFile technicalFile = technicalFileRepository.findById(request.getTechnicalFileNum()).orElseThrow();
        Language existingLanguage = languageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Language with id: " + id + " not found"));
        modelMapper.map(request, existingLanguage);
        Language savedLanguage = languageRepository.save(existingLanguage);
        existingLanguage.setTechnicalFile(technicalFile);
        return modelMapper.map(savedLanguage, LanguageResponse.class);
    }

    @Override
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

}
