package com.csidigital.shared.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponse {
    private Long id;
    private String ref;
    private LocalDate orderDate;
    private Long partnerNum;
    private Long requirementNum;
    /*private BillingType billingType;
    private String billingInstruction;
    private float tva;
    private PaymentCondition paymentCondition;
    private PaymentMode paymentMode;
    private String otherPaymentMode;
    private Long rib;
    private Double RevenueOrd;


    private String refQuotation;
    private Boolean customerAgreement;
    private Long partnerNum;
    private Long catalogNum;
    private Long reqNum;
    private LocalDate quotationDate;
    private String addressBuyer;
    private Double htRevenue;
    private Double tvaCost;
    private Float discount;
    private Double discountAmount;
    private Double revenue;
    private Currency currency;*/
    private Long quotationId;
    private Long projectNum;

}
