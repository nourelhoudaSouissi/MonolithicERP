package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.ExperienceLevel;
import lombok.Data;

import java.time.LocalDate;

@Data

public class AssOfferCandidateRequest {

    private LocalDate applicationDate;
    private ExperienceLevel experienceLevel;

    private Long offerNum;
    private Long employeeNum;


}
