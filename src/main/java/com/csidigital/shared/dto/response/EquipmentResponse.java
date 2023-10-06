package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Employee;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EquipmentResponse {
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
    private String comment;
    private String supplier;
    private Boolean amortizable;

    private String supplierOrderNumber;
    private Boolean affectable;

    @Enumerated(EnumType.STRING)
    private Affectation affectation;
    @Enumerated(EnumType.STRING)
    private PurchaseMethod purchaseMethod;
    @Enumerated(EnumType.STRING)
    private AmortizationType amortizationType;

    private Currency currency;
    @Enumerated(EnumType.STRING)
    private StatusDisponibility status;

    private String motifUnavailability;
    private LocalDate disponibilityDate;


    private String returnComment;

    private String returnStatus;
    private LocalDate returnDate;

    private List<Employee> employeeList;
    private Employee emp;
}
