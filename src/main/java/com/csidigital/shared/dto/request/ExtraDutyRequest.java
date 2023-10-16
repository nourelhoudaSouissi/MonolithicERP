package com.csidigital.shared.dto.request;


import com.csidigital.shared.enumeration.ExtraDutyType;
import lombok.Data;


@Data

public class ExtraDutyRequest {
    private Long workingHoursNumber ;
    private Double hourWage ;
    private Double performanceBonus ;

    private ExtraDutyType extraDutyType ;


    private Long benefitNum;
}
