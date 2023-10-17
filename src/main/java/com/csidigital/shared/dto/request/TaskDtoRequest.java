package com.csidigital.shared.dto.request;


import com.csidigital.shared.enumeration.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDtoRequest {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate EndDate;
    private Long estimation;
    private Long progression ;
    private Long TimeSpent ;
    private LocalDate creationDate ;
    private TaskType taskType;
    private Priority priority;
    private TaskPhase taskPhase;
    private LocalDate realEndDate ;
    private LocalDate projectionDate;
    private Long PhaseNum;
    private String reference;


}
