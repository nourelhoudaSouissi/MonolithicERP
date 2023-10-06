package com.csidigital.shared.dto.request;


import com.csidigital.shared.enumeration.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EndorsementRequest {

    private String reference;
    @Column(name = "title")
    private String title;
    @Column(name = "nationalBRNumber")
    private String nationalBRNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "endorsementDate")
    private LocalDate endorsementDate;
    @Column(name = "object")
    private String object;
    @Column(name = "note")
    private String note;
    private LocalDate validityDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private  Long contractNum;
}
