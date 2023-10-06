package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.OfferStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferRequest {
    private String title;
    private String reference;
    private String description ;
    private String requiredSkills ;
    private Long requiredExperienceAmount ;
    private OfferStatus offerStatus;
    private LocalDate startDate;
    private LocalDate endDate ;
    private String jobSite;

    private Long AssOfferCandidateId;
}
