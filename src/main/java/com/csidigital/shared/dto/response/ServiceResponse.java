package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.CatalogType;
import lombok.Data;

@Data
public class ServiceResponse {
    private Long id;
    private Double amount;
    private String code;
    private String title;
    private CatalogType catalogType = CatalogType.SERVICE;
    private Long catalogId;
    private String comment;
    private Long tvaCodeId ;
    private Double tvaPercentage;


}
