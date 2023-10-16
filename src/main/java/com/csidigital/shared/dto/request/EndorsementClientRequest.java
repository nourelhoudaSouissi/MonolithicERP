package com.csidigital.shared.dto.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EndorsementClientRequest {
    private String title;
    private String reference;
    private LocalDate endorsementDate;
    private String object;
    private String note;
    private Long contractNum;
}
