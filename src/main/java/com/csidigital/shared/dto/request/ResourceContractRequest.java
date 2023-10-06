package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.*;

import lombok.Data;

@Data
public class ResourceContractRequest {

    private ContractType contractType;
    private String trialPeriod;
    private EndContractReason endContractReason;
    private SalaryType salaryType;
    private Long changeCoefficient;
    private double contractFees;
    private String workingTime;
    private Currency currency;
    private double averageDailyCost;
    private double rateDailyCost;

}
