package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.WorkModel;
import lombok.Data;

@Data
public class WorkArrangementResponse {
    private Long id ;
    private WorkModel workModel ;
    private Double dailyWage;
    private Long workingDaysNumber ;


    private Long benefitId;
}
