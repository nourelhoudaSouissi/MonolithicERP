package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.ExtraDutyType;
import lombok.Data;

@Data
public class ExtraDutyResponse {
    private Long id ;
    private Long workingHoursNumber ;
    private Double HourWage ;
    private Double PerformanceBonus ;
    private ExtraDutyType extraDutyType ;
    private Long benefitId;
}
