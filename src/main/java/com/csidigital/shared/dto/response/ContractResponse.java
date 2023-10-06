package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.*;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContractResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate startDate;
    private String contractTitle;
    private LocalDate endDate;
    private String contractPlace;
    private  String reference;
    private LocalDate validityDate;
    private String contractEmployer;
    private String contractEmployee;
    private LocalDate contractDate;
    private Byte[] entrepriseSignature;
    private List<ArticleUpdated> articles ;
    private String commentContract;
    private Status contractStatus;
    private List<Endorsement> endorsementList;
    private List<BenefitRC> benefitRCSList;
    private List<ExceptionalFee>  ExceptionalFeeList;
    private Long employeeId;
}
