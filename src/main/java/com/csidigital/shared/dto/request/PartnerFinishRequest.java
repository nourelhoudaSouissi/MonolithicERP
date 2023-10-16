package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.ControlType;
import com.csidigital.shared.enumeration.Privilege;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PartnerFinishRequest {
    private Long id;
    private LocalDate activityStartDate;
    private LocalDate activityEndDate;
    private LocalDate partnerShipDate;
    private LocalDate foundedSince;
    private double inProgressAuthorized;
    private ControlType controlType;
    private int toleranceRate;
    private String insurancePolicy;
    private String insuranceCompany;

    private double capital;
    private String majorShareHolder;
    private Privilege classification;
    private String comment ;
}
