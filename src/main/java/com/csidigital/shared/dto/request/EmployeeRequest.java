package com.csidigital.shared.dto.request;


import com.csidigital.dao.entity.*;
import com.csidigital.dao.entity.Availability;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeRequest {
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
    private String locationName;
    private MaritalSituation maritalSituation;
    private Integer recommendationMark ;
    private Integer experience ;
    private String experienceDetails ;
    @Enumerated(EnumType.STRING)
    private WorkLocation workLocation;

    @Enumerated(EnumType.STRING)
    private Provenance provenance;
    private String employeeFirstName;
    private String employeeLastName;
    private String EmployeeSerialNumber;
    private List<OfferCandidate> AssOfferCandidateList;

    private Long AssOfferCandidateId;

    private EmployeeStatus employeeStatus;
    private Departement departement;
    private TechnicalFile technicalFile ;
    private AdministrativeData administrativeData;
    private Evaluation evaluation;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    //--------------attributs ResourceInterne------------------
    private String socialSecurityNumber;
    private String bankAccountNumber;


    private double leaveBalanceRest;
    private double leaveBalance;
    private Long productivity;
    private String nationalIdentity;

    private Boolean isEmployee;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contractsList;

    //----------attributs ResourceExterne--------------
    private String serialNumber;

    private List<Equipment> equipmentList;
     private List<Availability> availabilityList;

     //------------------------congés attributs --------------------->
     private Double circoncisionLeaveRest;
    private Double deathLeaveRest;
    private Double marriageLeaveRest;
    private  Double maternityLeaveRest;
    private Double remainingPaidLeaveRest;
    private Double remainingPaidLeave;
    private Double specialPaidLeaveRest;
    private Double sicknessLeaveRest;
    private Double sickLeaveRest;
    private Double compassioateLeaveRest;
    private Double remainingRecoveryLeaveRest;
    private LocalDate hireDate;

    private Long hierarchicalSuperiorNum;
    private Double noteEvaluation;
    private EmployeeRequest hierarchicalSuperior;
    private Byte[] photo ;

    private List<Positioning> positioningList;


    private List<Long> projectNum ;

    private  Long PrjNum;
}

