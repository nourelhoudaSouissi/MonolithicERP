package com.csidigital.shared.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
public class ProfileDomainResponse {
    private Long id;
    private String title;
    private String description;
}
