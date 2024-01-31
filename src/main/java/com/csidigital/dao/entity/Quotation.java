package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quotation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    @Enumerated(EnumType.STRING)
    private QuotationStatus quotationStatus;
    private Boolean customerAgreement;
    private Long partnerNum;
    private LocalDate quotationDate;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    private String billingInstruction;
    private float tva;
    @Enumerated(EnumType.STRING)
    private PaymentCondition paymentCondition;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    //otherPaymentMode
    private String otherPaymentMode;
    private String addressBuyer;

    private String contactBuyer;
    private Long legalIdentifier;
    //private Long rib;
    private Double htRevenue;
    private Double htRevenueRemiseProfile;

    private Double tvaCost;
    private Double revenueOrd;
    private Float changeRate;
    private Float discount;
    private Double discountAmount;
    private Long catalogNum;
    private Double revenue;
    private Long limitDuration;
    private LocalDate ValidationDate;
    private LocalDate sentDate;
    private LocalDate refusedDate;
    private LocalDate unansweredDate;

    @Column(length = 10000)
    private String comment;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private Currency catalogCurrency;
    @JsonIgnore
    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    private List<ProfileUpdated> profiles;

    @JsonIgnore
    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL)
    private List<ServiceUpdated> services;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Requirement requirement;

    @JsonIgnore
    @OneToOne(mappedBy = "quotation", cascade= CascadeType.ALL)
    private Order order;

    @Override
    public String toString() {
        return "Quotation{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", quotationStatus='" + quotationStatus + '\'' +
                ", customerAgreement='" + customerAgreement + '\'' +
                ", quotationDate='" + quotationDate + '\'' +
                ", billingType='" + billingType + '\'' +
                ", billingInstruction='" + billingInstruction + '\'' +
                ", tva='" + tva + '\'' +
                ", paymentCondition='" + paymentCondition + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", otherPaymentMode='" + otherPaymentMode + '\'' +
                ", revenueOrd='" + revenueOrd + '\'' +
                ", comment='" + comment + '\'' +
                ", profiles='" + profiles + '\'' +
                ", requirement='" + requirement + '\'' +
                ", order='" + order + '\'' +
                '}';
    }

    public void calculateQuotationRevenue() {
        Double quotationRevenue = 0.0;
        Double quotationRevenueProfiles = 0.0;
        Double quotationRevenueServices = 0.0;

        Double quotationRevenueRemise = 0.0;
        Double quotationRevenueRemiseProfiles = 0.0;
        Double quotationRevenueRemiseServices = 0.0;

        Double tvaAmount = 0.0;
        Double tvaAmountProfiles = 0.0;
        Double tvaAmountServices = 0.0;

        Double netTtc = 0.0;
        Double netTtcProfiles = 0.0;
        Double netTtcServices = 0.0;
        List<ProfileUpdated> profiles = this.getProfiles();
        List<ServiceUpdated> services = this.getServices();
       /* for (ProfileUpdated profile : profiles){
            quotationRevenue += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod();
            quotationRevenueRemise += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod() * (1 - (profile.getProfileDiscount() / 100));

        }*/
        for (ProfileUpdated profile : profiles){
            quotationRevenueProfiles += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod();
            quotationRevenueRemiseProfiles += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod() * (1 - (profile.getProfileDiscount() / 100));
            tvaAmountProfiles += quotationRevenueRemiseProfiles * profile.getTvaPercentage()/100;
            netTtcProfiles += quotationRevenueRemiseProfiles + tvaAmountProfiles;
        }
        for (ServiceUpdated service : services){
            quotationRevenueServices += service.getAmount() * service.getServiceQuantity() * service.getPeriod();
            quotationRevenueRemiseServices += service.getAmount() * service.getServiceQuantity() * service.getPeriod() * (1 - (service.getServiceDiscount() / 100));
            tvaAmountServices += quotationRevenueRemiseServices * service.getTvaPercentage()/100;
            netTtcServices += quotationRevenueRemiseServices + tvaAmountServices;
        }

        quotationRevenue = quotationRevenueProfiles + quotationRevenueServices;
        this.setHtRevenue(quotationRevenue);

       /* Double tvaCost = quotationRevenueRemise * getTva()/100;
        this.setTvaCost(tvaCost);*/
        Double tvaCost = tvaAmountProfiles + tvaAmountServices;
        this.setTvaCost(tvaCost);

        /* Double orderRevenue = quotationRevenueRemise + tvaCost;
        this.setRevenueOrd(orderRevenue);*/
        Double orderRevenue = netTtcProfiles + netTtcServices;
        this.setRevenueOrd(orderRevenue);

        quotationRevenueRemise = quotationRevenueRemiseProfiles + quotationRevenueRemiseServices;
        this.setHtRevenueRemiseProfile(quotationRevenueRemise);
    }


}
