package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.LanguageLevel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class LanguageRequest {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private com.csidigital.shared.enumeration.Language language;
    private LanguageLevel languageLevel;
    private String additionalInformation;
    private Long technicalFileNum ;
}
