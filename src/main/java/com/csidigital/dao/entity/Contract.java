package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.csidigital.shared.enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "contractTitle")
    private String contractTitle;

    @Column(name = "contractPlace")
    private String contractPlace;

    @Column(name = "contractDate")
    private LocalDate contractDate;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "validityDate")
    private LocalDate validityDate;

    private String commentContract;
    @Column(name = "entrepriseSignature")
    private Byte[] entrepriseSignature;

    @Enumerated(EnumType.STRING)
    private Status contractStatus;

    @Column(name = "contractEmployer", length = 10000)
    private String contractEmployer;
    @Column(name = "contractEmployee", length = 10000)
    private String contractEmployee;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "id_employee")
    private Employee employee;
    private  String reference;

    @OneToMany(mappedBy = "contract" )
    private List<ArticleUpdated> articles = new ArrayList<ArticleUpdated>();

    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<Endorsement> endorsementList;

    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private List<BenefitRC> benefitRCSList;

    @JsonIgnore
    @OneToMany(mappedBy = "contract" , cascade = CascadeType.ALL)
    private List< ExceptionalFee>  ExceptionalFeeList;





}
