package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class TechnicalFileRequest {
    private String reference;
    private String description;
    private String objective;
    private String driverLicense;
    private List<Skills> skills;
    private List<Experience> experiences;
    private List<Language> languages;
    private List<Certification> certifications;
    private List<Education> educations;
    private Long employeeNum;
    private Long experienceAmount;
}
