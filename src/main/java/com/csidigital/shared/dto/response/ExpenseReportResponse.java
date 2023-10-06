package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ExpenseReportResponse {

    private Long Id;
    private LocalDate billingDate;
    @Enumerated(EnumType.STRING)
    private FeeType feeType;
    private double amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Boolean customerBilling;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    private LocalDate createDate;
    private String comment;
    private EmployeeResponse employee;
    private String employeeFirstName;
    private String employeeLastName;
    private String justificationDoc;
}
