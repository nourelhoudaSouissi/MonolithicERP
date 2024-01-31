package com.csidigital.shared.dto.request;

import lombok.Data;

@Data
public class ProfileReferenceRequest {
    private String function;
    private String experience;
    private String description;
    private Double yearsOfExperience;
    private String technologie;
}
