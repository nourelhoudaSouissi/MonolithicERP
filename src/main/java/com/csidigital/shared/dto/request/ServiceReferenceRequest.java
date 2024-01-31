package com.csidigital.shared.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ServiceReferenceRequest {
    private String code;
    private String title;
    private String description;
}
