package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    private String reference;
    private String type;
    private String designation;
    private LocalDate acquisitionDate;
    private LocalDate endDate;
    private Double purchasePrise;
    private String supplier;
    private Boolean amortizable;
    private Boolean affectable;
    private String motifUnavailability;
    private LocalDate disponibilityDate;
    private String supplierOrderNumber;
    @Column(name = "returnComment", length = 10000)
    private String returnComment;
    @Column(name = "returnStatus", length = 10000)
    private String returnStatus;
    private LocalDate returnDate;

    @Column(name = "comment", length = 10000)
    private String comment;
    @Enumerated(EnumType.STRING)
    private Affectation affectation;
    @Enumerated(EnumType.STRING)
    private PurchaseMethod purchaseMethod;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private AmortizationType amortizationType;
    @Enumerated(EnumType.STRING)
    private StatusDisponibility status;




    //relation  manytomany avec employee
    @JsonIgnore
    @ManyToMany(mappedBy = "equipmentList")
    private List<Employee> employeeList;



}
