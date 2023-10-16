package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Quotation;
import com.csidigital.shared.enumeration.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor

public class RequirementResponse {
    private Long id ;
    private String title;
    private String ref;
    private BudgetingType budgetingType ;
    private RequirementType requirementType ;
    private RequirementStatus requirementStatus;
    private PaymentType paymentType ;
    private Availability availability ;
    private String startDate ;
    private String responseDate ;
    private String creationDate ;
    private  Integer candidateNumber ;
    private Double totalBudget;
    private Currency currency;
    private String closureDate;
    private String comment;
    private List<Quotation> quotations;
    private Long partnerId ;
}
