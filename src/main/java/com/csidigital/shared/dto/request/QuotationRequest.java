package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class QuotationRequest {
    private QuotationStatus quotationStatus;
    private Boolean customerAgreement;
    private LocalDate quotationDate;
    private BillingType billingType;
    private String billingInstruction;
    private String ref;
    private float tva;
    private PaymentCondition paymentCondition;
    private PaymentMode paymentMode;
    private String otherPaymentMode;
    private String comment;
    //private Long rib;
    private String currency;
    private Currency catalogCurrency;
    private String addressBuyer;
    private String contactBuyer;
    private Long legalIdentifier;
    private Long partnerNum;
    private Double htRevenue;
    private Double tvaCost;
    private Double RevenueOrd;
    private Float changeRate;
    private Float discount;
    private Double discountAmount;
    private Double revenue;
    private Long RequirementNum;
    private Long catalogNum;

    private Double htRevenueRemiseProfile;
    private List<ProfileUpdatedRequest> profiles;
    private Long limitDuration;
    private LocalDate ValidationDate;
    private LocalDate sentDate;
    private LocalDate refusedDate;
    private LocalDate unansweredDate;
}
