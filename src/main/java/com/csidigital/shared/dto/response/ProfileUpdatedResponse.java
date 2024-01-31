package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.Experience;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdatedResponse {
    private Long id ;
    private Integer candidateNumber;
    private String function;
    private Experience experience;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long period;
    private Double total;
    private Double candidateDailyCost;
    private String comment;
    private Long profileId;
    private Long quotationId;
    private Double totalDiscount;
    private Double profileDiscount;
    private Double tvaPercentage;
    private Double totalTva;


}
