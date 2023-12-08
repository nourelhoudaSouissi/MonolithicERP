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
        Double quotationRevenueRemise = 0.0;
        List<ProfileUpdated> profiles = this.getProfiles();
        for (ProfileUpdated profile : profiles){
            quotationRevenue += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod();
            quotationRevenueRemise += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod() * (1 - (profile.getProfileDiscount() / 100));

        }
        this.setHtRevenue(quotationRevenue);

       /* Double discountAmount = getDiscount()/100 * getHtRevenue();
        this.setDiscountAmount(discountAmount);*/

       /* Double revenue = getHtRevenue() - getDiscountAmount();
        this.setRevenue(revenue);
*/
        Double tvaCost = quotationRevenueRemise * getTva()/100;
        this.setTvaCost(tvaCost);

        Double orderRevenue = quotationRevenueRemise + tvaCost;
        this.setRevenueOrd(orderRevenue);

        this.setHtRevenueRemiseProfile(quotationRevenueRemise);
    }


}
