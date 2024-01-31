package com.csidigital.shared.dto.response;

import lombok.Data;

@Data
public class ServiceReferenceResponse {
    private Long id;
    private String code;
    private String title;
    private String description;
}
