package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "order_entity")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private LocalDate orderDate;
    private Long partnerNum;
    private Long requirementNum;
    /*@Enumerated(EnumType.STRING)
    private BillingType billingType;
    private String billingInstruction;
    private float tva;
    @Enumerated(EnumType.STRING)
    private PaymentCondition paymentCondition;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    //otherPaymentMode
    private String otherPaymentMode;
    private Long rib;
    private Double RevenueOrd;
    //end old


    private String refQuotation;
    @Enumerated(EnumType.STRING)
    private QuotationStatus quotationStatus;
    private Boolean customerAgreement;

    private Long catalogNum;

    private LocalDate quotationDate;
    private String addressBuyer;
    //private Long rib;
    private Double htRevenue;
    private Double tvaCost;
    //private Float changeRate;
    private Float discount;
    private Double discountAmount;
    private Double revenue;
    @Enumerated(EnumType.STRING)
    private Currency currency;*/

    /////////////////////end new
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Quotation quotation;



    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,    mappedBy = "order")
    private Project project;

}
