package com.csidigital.shared.dto.request;


import com.csidigital.shared.enumeration.CatalogType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileRequest {
    private String function;
    private String experience;
    private Double candidateDailyCost;
    private String comment;
    private Double yearsOfExperience;
    private String technologie;
    private Boolean isActif;
    private LocalDate activationDate;
    private LocalDate deativationDate;
    private Long catalogNum;
    private Long profileDomainNum;
    private CatalogType catalogType = CatalogType.RESOURCE;
    private Long calculationUnitNum ;

}
