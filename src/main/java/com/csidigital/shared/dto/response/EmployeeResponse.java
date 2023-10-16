package com.csidigital.shared.dto.response;


import com.csidigital.dao.entity.*;
import com.csidigital.dao.entity.Availability;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
public class EmployeeResponse {

    private Long Id;

    private String lastName;
    private String firstName;
    private Civility civility;
    private Title title;
    private EmployeeType employeeType;

    private LocalDate birthDate;
    private String emailOne;
    private String emailTwo;
    private Integer phoneNumberOne;
    private Integer phoneNumberTwo;
    private String address;
    private Integer postCode;
    private String city;

    private String country;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    private MaritalSituation maritalSituation;
    private Integer recommendationMark ;
    private Integer experience ;
    private String experienceDetails ;


    private EmployeeStatus employeeStatus;

    private WorkLocation workLocation;

    private Departement departement;

    private TechnicalFile technicalFile;

   private List<OfferCandidate> offerCandidateList;
    private Evaluation evaluation;
    ///---------------attributs ResourceInterne------------------------
    private String socialSecurityNumber;
    private String locationName;
    private String bankAccountNumber;

    private double leaveBalanceRest;
    private double leaveBalance;
    private Long productivity;
    private String nationalIdentity;

    private Boolean isEmployee;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contractsList;
    //----------attributs Backoffice-------------------
    private String test;
    //----------attributs ResourceExterne--------------
    private String serialNumber;

    private List<Equipment> equipmentList;
    private List<Availability> availabilityList;


    @Enumerated(EnumType.STRING)
    private Provenance provenance;
    private String employeeFirstName;
    private String employeeLastName;
    private String EmployeeSerialNumber;
   // private Set<Long> offer;

    private AdministrativeData administrativeData;

//congés attributs
    private Double circoncisionLeaveRest;
    private Double deathLeaveRest;
    private Double marriageLeaveRest;
    private Double maternityLeaveRest;
    private Double remainingPaidLeaveRest;
    private Double remainingPaidLeave;
    private Double specialPaidLeaveRest;
    private Double sicknessLeaveRest;
    private Double sickLeaveRest;
    private Double compassioateLeaveRest;
    private Double remainingRecoveryLeaveRest;
    private LocalDate hireDate;

    private Employee hierarchicalSuperior;

  //  private Map<LeaveTypeResponse, Double> leaveBalances;

    private Byte[] photo ;
    private Double noteEvaluation;

private Long hierarchicalSuperiorNum;
    private List<OfferCandidate> AssOfferCandidateList;
private  List<Offer> offers;

    private List<Positioning> positioningList;










}






