package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.MotifUnavailability;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AvailabilityResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer period;
    private String comment;
    private MotifUnavailability motifUnavailability;
    private Long employeeNum;

}
