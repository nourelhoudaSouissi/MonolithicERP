package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.CatalogType;
import com.csidigital.shared.enumeration.Experience;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileResponse {
    private Long id;
    private String function;
    private Experience experience;
    private Double candidateDailyCost;
    private String comment;
    private Double yearsOfExperience;
    private String technologie;
    private Boolean isActif;
    private LocalDate activationDate;
    private LocalDate deativationDate;
    private Long catalogId;
    private Long profileDomainId;
    // private String profileDomaineTitle;
    //private ProfileDomainResponse profileDomain;
    private CatalogType catalogType = CatalogType.RESOURCE;


}
