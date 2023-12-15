package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Catalog;
import com.csidigital.shared.enumeration.CatalogType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ServiceRequest {
    private Double amount;
    private String code;
    private String title;
    private CatalogType catalogType = CatalogType.SERVICE;
    private Long catalogNum;
    private Catalog catalog;
    private String comment;
    private Long tvaCodeNum ;
    private Double tvaPercentage;



}
