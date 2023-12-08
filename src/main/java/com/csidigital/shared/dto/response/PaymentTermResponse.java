package com.csidigital.shared.dto.response;

import lombok.Data;

@Data
public class PaymentTermResponse {
    private Long id;
    private String title;
    private String code;
    private String description;
}
