package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class OfferResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String title;
    private String reference;
    private String description ;
    private String requiredSkills ;
    private Long requiredExperienceAmount ;
    private LocalDate startDate;
    private LocalDate endDate ;
    private String jobSite;
    private OfferStatus offerStatus;
    private Set<Long> employee;

}