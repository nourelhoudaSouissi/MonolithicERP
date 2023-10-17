package com.csidigital.shared.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PhaseRequest {


    private String name ;
    @Column(length = 10000)
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(length = 10000)
    private String livrable;


}
