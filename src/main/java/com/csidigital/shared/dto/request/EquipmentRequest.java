package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EquipmentRequest {

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
    private Currency currency;

    private String supplierOrderNumber;
    private Boolean affectable;
    @Enumerated(EnumType.STRING)
    private Affectation affectation;
    @Enumerated(EnumType.STRING)
    private PurchaseMethod purchaseMethod;
    @Enumerated(EnumType.STRING)
    private AmortizationType amortizationType;

    @Enumerated(EnumType.STRING)
    private StatusDisponibility status;

    private String motifUnavailability;
    private LocalDate disponibilityDate;


    private String returnComment;

    private String returnStatus;
    private LocalDate returnDate;
}
