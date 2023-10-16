package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.*;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PartnerResponse {
    private Long id ;
    private String ref;
    private List<String> refs = new ArrayList<>();

    //identifiant Siret ou RNE
    private Long legalIdentifier;

    //identifiant TVA
    private Long tvaIdentifier;

    private Long nafCode;
    private String name ;
    private String companyStatus ;
    //private Integer staffNumber;
    //private String parentCompany;
    private String email;
    private String webSite;
    //private String ceoFullName ;
    private Long phoneNumber ;
    private Long mobilePhoneNumber ;
    private String comment ;
    //private WorkField workField ;
    private LegalStatus legalStatus ;
    @Lob
    private String logo ;
    private LocalDate activityStartDate;
    private LocalDate activityEndDate;
    private LocalDate partnerShipDate;
    private LocalDate creationDate;
    private LocalDate foundedSince;
    private double inProgressAuthorized;
    private ControlType controlType;
    private int toleranceRate;
    private String insurancePolicy;
    private String insuranceCompany;
    private Currency currency;
    private double capital;
    private PaymentMode paymentMode;
    private PaymentCondition paymentCondition;

    private String externalReference ;
    private boolean blocked;
    private String blockingReason;
    private String reason;
    private Privilege classification;
    //private Provenance provenance ;
    private List<Requirement> requirements ;
    private List<Address> addresses;
    //private List<OfferedService> offeredServices;
    private List<Contact> contacts ;
    //private List<SocialMedia> socialMedias ;
    private List<BankAccount> bankAccounts ;
    private List<ContractClient> contracts ;
}
