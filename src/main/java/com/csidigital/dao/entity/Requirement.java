package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.*;
import com.csidigital.shared.enumeration.Availability;
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
public class Requirement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String ref;
    private String title;
    private LocalDate startDate ;
    private LocalDate responseDate ;
    private LocalDate creationDate ;
    private  Integer candidateNumber ;
    @Column(length = 10000)
    private String comment;
    private Double totalBudget;
    private Currency currency;
    private LocalDate closureDate;
    @Enumerated(EnumType.STRING)
    private BudgetingType budgetingType ;
    @Enumerated(EnumType.STRING)
    private RequirementStatus requirementStatus ;
    @Enumerated(EnumType.STRING)
    private RequirementType requirementType ;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType ;
    @Enumerated(EnumType.STRING)
    private Availability availability ;
    /*@Enumerated(EnumType.STRING)
    private WorkField workField ;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Partner partner;

    @JsonIgnore
    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL)
    private List<Quotation> quotations;

    @Override
    public String toString() {
        return "Requirement{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", responseDate='" + responseDate + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", candidateNumber='" + candidateNumber + '\'' +
                ", comment='" + comment + '\'' +
                ", totalBudget='" + totalBudget + '\'' +
                ", currency='" + currency + '\'' +
                ", closureDate='" + closureDate + '\'' +
                ", budgetingType='" + budgetingType + '\'' +
                ", requirementStatus='" + requirementStatus + '\'' +
                ", requirementType='" + requirementType + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
