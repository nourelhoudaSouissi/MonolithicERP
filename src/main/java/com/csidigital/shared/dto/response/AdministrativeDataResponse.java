package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Evaluation;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdministrativeDataResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ContractType contractType;
    private double currentSalary;

    private double expectedSalary;
    @Enumerated(EnumType.STRING)
    private AvailabilityEnum availability;
    private LocalDate availabilityDate;
    @Enumerated(EnumType.STRING)
    private Experience experience;
    private Evaluation evaluation;

    }
