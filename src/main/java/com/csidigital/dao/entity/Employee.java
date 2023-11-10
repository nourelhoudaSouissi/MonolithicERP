package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String lastName;
    @Column(name = "firstName")
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Civility civility;
    @Enumerated(EnumType.STRING)
    private Title title;

    @Enumerated(EnumType.STRING)
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
    private Integer recommendationMark ;
    private Integer experience ;
    private String experienceDetails ;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
    @Enumerated(EnumType.STRING)
    private WorkLocation workLocation;
    @Enumerated(EnumType.STRING)
    private MaritalSituation maritalSituation;

    private String locationName;
    @Enumerated(EnumType.STRING)
    private Departement departement;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    private String test;
    @Enumerated(EnumType.STRING)
    private Provenance provenance;

    private String employeeFirstName;
    private String employeeLastName;

    //-------------attributs ResourceInterne--------------------------
    private String socialSecurityNumber;
    private String bankAccountNumber;

    private Byte[] photo ;

    private double leaveBalanceRest;
    private double leaveBalance;
    private Long productivity;
    private String nationalIdentity;

    private Boolean isEmployee;

    //attributs congés

    // attribut qui désigne le congé de circoncision
    private Double circoncisionLeaveRest;

    // attribut qui désigne le congé de décés
    private Double deathLeaveRest;

    // attribut qui désigne le congé de marriage
    private Double marriageLeaveRest;

    // attribut qui désigne le congé de naissance
    private  Double maternityLeaveRest;

    // attribut qui désigne le rest des conjé payé
    private Double remainingPaidLeaveRest;

    // attribut qui désigne le total des conjé payé qui sera incrémenté chaque fin de mois de 1.83
    private Double remainingPaidLeave;

    // attribut qui désigne le rest des conjé  spécial payé
    private Double specialPaidLeaveRest;

    // attribut qui désigne le rest des conjé maladie (sickLeaveRest + compassioateLeaveRest "Accompagnement")
    private Double sicknessLeaveRest;

    // attribut qui désigne le rest des conjé maladie
    private Double sickLeaveRest;

    // attribut qui désigne le rest des conjé d'accompagnement
    private Double compassioateLeaveRest;

    // attribut qui désigne le rest des conjé de récupération
    private Double remainingRecoveryLeaveRest;

    // attribut qui désigne la date de recrutement de l'employee
    private LocalDate hireDate;


    private Double noteEvaluation;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "employee")
    private TechnicalFile technicalFile;


    @JsonIgnore
    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.ALL)
    private List<OfferCandidate> offerCandidateList;
    @JsonIgnore
    @OneToOne(mappedBy = "employee" ,cascade = CascadeType.ALL)
    private AdministrativeData administrativeData;
    @JsonIgnore
    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
    private List<Evaluation> evaluation;

    @JsonIgnore
    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.ALL)
    private List<Contract> contractsList;

    //relation  oneTomany avec availability
    @JsonIgnore
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Availability> availabilityList;


    @JsonIgnore
    //relation manytomany avec equipements
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "AssEquipmentEmployee",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> equipmentList;


    // relations congés
    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<TimeOff> timeOffList;
    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<RecoveryLeave> recoveryLeaves;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ExpenseReport> expenseReports;

   @JsonIgnore
    @OneToOne
    @JoinColumn(name = "hierarchical_superior_id")
    private Employee hierarchicalSuperior;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Positioning> positioningList;

    @JsonIgnore
    @ManyToMany
    private List<Project> project;

    @JsonIgnore
    @OneToMany
    private List<SubTask> subTasks;

}


