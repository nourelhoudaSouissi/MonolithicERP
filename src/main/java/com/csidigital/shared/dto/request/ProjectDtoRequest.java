package com.csidigital.shared.dto.request;



import com.csidigital.dao.entity.ResponsableExtern;
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
public class ProjectDtoRequest {
    private String projectReference;
    private String name;
    private String description;
    private Double budget;
    private LocalDate startDate;
    private Integer workingHourNumber ;
    private LocalDate realStartDate ;
    private LocalDate realEndDate ;
    private String projectCategory ;
    private ProjectType projectType;
    private LocalDate endDate;
    private String lieu;
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
    private List<Long> resourceIds;
    private List<ResponsableExtern> responsables;

    private List<Long> employeeIds;
    private Long orderNum;
}
