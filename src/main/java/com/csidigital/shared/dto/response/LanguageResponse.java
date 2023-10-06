package com.csidigital.shared.dto.response;
import com.csidigital.shared.enumeration.LanguageLevel;
import lombok.Data;
@Data
public class LanguageResponse {
    private Long Id;
    private com.csidigital.shared.enumeration.Language language;
    private LanguageLevel languageLevel;
    private String additionalInformation;
    private Long technicalFileId;
}