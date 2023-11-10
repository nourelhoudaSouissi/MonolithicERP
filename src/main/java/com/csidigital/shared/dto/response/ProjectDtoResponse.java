package com.csidigital.shared.dto.response;


import com.csidigital.dao.entity.*;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoResponse {
    private Long id;
    private String projectReference;
    private String name;
    private String description;
    private Double budget;
    private ProjectType projectType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer workingHourNumber;
    private LocalDate realStartDate;
    private LocalDate realEndDate;
    private String projectCategory;
    private String lieu;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
   private  List<Resource> resources ;
    private List<Phase> phases;
   private List<ResponsableExtern> responsables;
    private  List<Employee> employees ;



}
