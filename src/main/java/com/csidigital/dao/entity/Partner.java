package com.csidigital.dao.entity;


import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "CustomPartner")
@AllArgsConstructor
@NoArgsConstructor
public class Partner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String ref;
    private List<String> refs = new ArrayList<>(3);
    //identifiant Siret ou RNE
    private Long legalIdentifier;

    //identifiant TVA
    private Long tvaIdentifier;

    private Long nafCode;
    private String name ;
    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus ;

    private String email;
    private String webSite;

    private Long phoneNumber ;
    private Long mobilePhoneNumber ;
    @Column(length = 10000)
    private String comment ;

    @Column (nullable = true)
    @Enumerated(EnumType.STRING)
    private LegalStatus legalStatus ;
    @Lob
    private String logo ;
    private LocalDate activityStartDate;
    private LocalDate activityEndDate;
    private LocalDate creationDate;
    private LocalDate partnerShipDate;
    private LocalDate foundedSince;
    private double inProgressAuthorized;
    private ControlType controlType;
    private int toleranceRate;
    private String insurancePolicy;
    private String insuranceCompany;

    private Currency currency;
    private double capital;
    //private String majorShareHolder;
    private Privilege classification;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.STRING)
    private PaymentCondition paymentCondition;
    private String externalReference ;
    private boolean blocked;
    private String blockingReason;
    private String reason;
    //@Enumerated(EnumType.STRING)
    //private Provenance provenance ;
    //@Enumerated(EnumType.STRING)
    //private WorkField workField ;
    //private String ceoFullName ;
    //private Integer staffNumber;
    //private String parentCompany;
    @JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Address> addresses;

    /*@JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<OfferedService> offeredServices;*/

    @JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Requirement> requirements;

    @JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Contact> contacts ;

    /*@JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<SocialMedia> socialMedias ;*/

    @JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<ContractClient> contracts ;

    @JsonIgnore
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                // Include other fields excluding the 'addresses' field
                // ...
                '}';
    }

    public Partner(PaymentMode paymentMode, PaymentCondition paymentCondition) {
        this.paymentMode = paymentMode;
        this.paymentCondition = paymentCondition;
    }
}
