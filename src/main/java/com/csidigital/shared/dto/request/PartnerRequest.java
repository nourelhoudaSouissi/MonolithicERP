package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Address;
import com.csidigital.dao.entity.BankAccount;
import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.entity.Requirement;
import com.csidigital.shared.enumeration.*;
import com.csidigital.shared.enumeration.PaymentCondition;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class PartnerRequest {
    private String ref;

    //identifiant Siret ou RNE
    private Long legalIdentifier;

    //identifiant TVA
    private Long tvaIdentifier;

    private Long nafCode;
    private String name;
    private CompanyStatus companyStatus ;
    //private Integer staffNumber;
    //private String parentCompany;
    private String email;
    private String majorShareHolder;
    private Privilege classification;
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
    private LocalDate foundedSince;
    private double inProgressAuthorized;
    private String controlType;
    private String insurancePolicy;
    private String insuranceCompany;

    private Currency currency;
    private double capital;
    private PaymentMode paymentMode;
    private PaymentCondition paymentCondition;
    private boolean blocked;
    private String blockingReason;
    private String reason;
    //private Provenance provenance ;
    private String externalReference ;
    private int toleranceRate;

    private List<Requirement> requirements ;

    private List<Contact> contacts;

    private List<Address> addresses;

    //private List<OfferedService> offeredServices;
    //private List<SocialMedia> socialMedias ;
    private List<BankAccount> bankAccounts ;
    private Boolean isTaxable;

    private Long paymentTermNum;
    private Long tvaCodeNum ;
    private Double tvaPercentage;

}
