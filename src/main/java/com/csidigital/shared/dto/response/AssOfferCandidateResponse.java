package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.ExperienceLevel;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AssOfferCandidateResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate applicationDate;
    private ExperienceLevel experienceLevel;
    private Long employeeId;
    private Long offerId;
    private Long evaluationNum;

}