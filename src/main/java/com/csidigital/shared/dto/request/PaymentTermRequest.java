package com.csidigital.shared.dto.request;

import lombok.Data;

@Data
public class PaymentTermRequest {
    private String title;
    private String code;
    private String description;
}
