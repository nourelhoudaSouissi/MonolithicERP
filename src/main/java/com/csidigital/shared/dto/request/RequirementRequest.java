package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.*;
import lombok.Data;

import java.time.LocalDate;

@Data

public class RequirementRequest {
    private String title;
    private BudgetingType budgetingType ;
    private RequirementType requirementType ;
    private PaymentType paymentType ;
    private Availability availability ;
    private LocalDate startDate ;
    private LocalDate responseDate ;
    private  Integer candidateNumber ;
    private Double totalBudget;
    private Currency currency;
    private LocalDate closureDate;
    private String comment;
    private Long partnerNum ;



}
