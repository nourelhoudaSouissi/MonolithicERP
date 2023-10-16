package com.csidigital.shared.dto.request;

import lombok.Data;

@Data

public class OrderRequest {
    private Long partnerNum;
    private Long requirementNum;
    /*private BillingType billingType;
    private String billingInstruction;
    private Long tva;
    private PaymentCondition paymentCondition;
    private PaymentMode paymentMode;
    private String bankDetail;
    private Double RevenueOrd;
    /////////////////////////////////////////////end old
    //otherPaymentMode
    private String otherPaymentMode;
    //end old
    private Long partnerNum;
    private String addressBuyer;
    //private Long rib;
    private Double htRevenue;
    private Long catalogNum;
    private Long reqNum;
    private Double tvaCost;
    //private Float changeRate;
    private Float discount;
    private Double discountAmount;
    private Double revenue;
    private Currency currency;*/
    private Long quotationNum;
}
