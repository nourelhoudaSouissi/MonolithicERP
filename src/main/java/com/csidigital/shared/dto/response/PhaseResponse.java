package com.csidigital.shared.dto.response;


import com.csidigital.dao.entity.Project;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PhaseResponse {
    private Long Id;
    private String name ;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String livrable;

    private Project project;
}
