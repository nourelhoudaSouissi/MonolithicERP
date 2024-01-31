package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.CatalogType;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ServiceUpdatedResponse {
    private Long Id;
    private Double amount;
    private String code;
    private String title;
    private CatalogType catalogType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long period;
    private Integer serviceQuantity;
    private Double total;
    private Double tvaPercentage;
    private Double totalTva;

    private Double totalDiscount;
    private Double serviceDiscount;
    private Long quotationId;
    private Long serviceId;

}
