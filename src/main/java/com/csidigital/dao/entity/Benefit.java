package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.BenefitStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Benefit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private BenefitStatus benefitStatus ;
    private String titled ;
    private Double averageDailyCost ;
    private Double totalCost ;

    private Double cost ;
    private Double costEfficiency ;
    private Boolean exceptionalCosts;
    private Double monthlyFees ;

    @JsonIgnore
    @OneToMany(mappedBy = "benefit", cascade = CascadeType.ALL)
    private List<ExtraDuty> extraDuties;

    @JsonIgnore
    @OneToMany(mappedBy = "benefit", cascade = CascadeType.ALL)
    private List<WorkArrangement> workArrangements;



}
