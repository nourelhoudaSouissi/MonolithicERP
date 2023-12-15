package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class QuotationResponse {
    private Long id ;
    private String ref;
    private QuotationStatus quotationStatus;
    private LocalDate quotationDate;
    private Boolean customerAgreement;
    private BillingType billingType;
    private String billingInstruction;
    private float tva;
    private PaymentCondition paymentCondition;
    private PaymentMode paymentMode;
    private Double htRevenue;
    private Double tvaCost;
    private Double revenueOrd;
    private Float changeRate;
    private Double discountAmount;
    private Double htRevenueRemiseProfile;
    private Long limitDuration;
    private LocalDate ValidationDate;
    private LocalDate sentDate;
    private LocalDate refusedDate;
    private LocalDate unansweredDate;

    private Double revenue;
    private Float discount;
    private String comment;
    private Long partnerNum;
    private String addressBuyer;
    private String contactBuyer;
    private Long legalIdentifier;
    private Currency currency;
    private Currency catalogCurrency;
    private Long catalogNum;
    private Long requirementId;
    private Long orderId;
    private List<ProfileUpdatedResponse> profiles;
    private List<ServiceUpdatedResponse> services;

}
