package com.csidigital.shared.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EndorsementClientResponse {
    private String title;
    private Long id;
    private String reference;
    private LocalDate endorsementDate;
    private String object;

    private String note;
    private Long contractId;
}
