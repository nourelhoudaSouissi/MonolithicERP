package com.csidigital.shared.dto.response;

import lombok.Data;

@Data
public class FeeClientDtoResponse<D extends Number> {
    private Long id;
    private String refFree;
    private String designation;
    private String unite;
    private Double cost;
    private Long tva;
    private Long discount;
    private Integer quantity;
    private Double priceWithoutTax;
    private Double priceWithAllTaxIncluded;
    private Long billClientId;

}
