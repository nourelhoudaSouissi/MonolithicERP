package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.ExtraDuty;
import com.csidigital.dao.entity.WorkArrangement;
import com.csidigital.shared.enumeration.BenefitStatus;
import lombok.Data;

import java.util.List;

@Data
public class BenefitResponse {
    private Long id;
    private BenefitStatus benefitStatus ;
    private String titled ;
    private Double averageDailyCost ;
    private Double totalCost ;

    private Double cost ;
    private Double costEfficiency ;
    private Boolean exceptionalCosts;
    private Double monthlyFees ;


    private List<ExtraDuty> extraDuties;


    private List<WorkArrangement> workArrangements;



}
