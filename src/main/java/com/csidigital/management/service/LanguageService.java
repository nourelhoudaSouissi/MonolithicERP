package com.csidigital.management.service;


import com.csidigital.shared.dto.request.LanguageRequest;
import com.csidigital.shared.dto.response.LanguageResponse;

import java.util.List;

public interface LanguageService {
    LanguageResponse createLanguage(LanguageRequest request);
    List<LanguageResponse> getAllLanguage();
    LanguageResponse getLanguageById(Long id);

    LanguageResponse updateLanguage(LanguageRequest request, Long id);

    void deleteLanguage(Long id);

}
