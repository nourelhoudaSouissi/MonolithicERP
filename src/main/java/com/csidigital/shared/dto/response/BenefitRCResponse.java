package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.ContractBenifitType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BenefitRCResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortDescription;
    private String description;
    private ContractBenifitType contractBenifitType;
    private Long contractId;
}

