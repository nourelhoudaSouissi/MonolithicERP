package com.csidigital.shared.dto.response;


import com.csidigital.dao.entity.Project;

import java.time.LocalDate;

public class PhaseResponse {
    private Long Id;
    private String name ;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String livrable;

    private Project project;
}
