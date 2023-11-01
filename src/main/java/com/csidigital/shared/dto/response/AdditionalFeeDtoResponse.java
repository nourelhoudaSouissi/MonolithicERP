package com.csidigital.shared.dto.response;


import lombok.Data;


@Data
public class AdditionalFeeDtoResponse<D extends Number> {
    private long id;

    private String refFree;
    private String designation;
    private String unite;
    private Double cost;
    private Long tva;
    private Integer quantity;
    private Long discount;

    private Double priceWithoutTax;

    private Double priceWithAllTaxIncluded;

    private Long billId ;


}
