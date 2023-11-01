package com.csidigital.shared.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdditionalFeeDtoRequest {
    private String refFree;
    private String designation;
    private String unite;
    private Double cost;
    private Long tva;
    private Integer quantity;
    private Long discount;

    private Long billId ;


}
