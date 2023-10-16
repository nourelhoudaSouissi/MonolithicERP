package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.Experience;
import lombok.Data;

@Data
public class ProfileResponse {
    private Long id;
    private String function;
    private Experience experience;
    private Double candidateDailyCost;
    private String comment;
    private Long catalogId;
}
