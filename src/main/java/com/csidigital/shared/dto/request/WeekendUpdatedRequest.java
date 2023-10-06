package com.csidigital.shared.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
public class WeekendUpdatedRequest {
    private String reference ;
    private String name;
    @Enumerated(EnumType.STRING)
    private DayOfWeek startDay;
    @Enumerated(EnumType.STRING)
    private DayOfWeek endDay;
    private LocalDate activationStartDate;
    private LocalDate activationEndDate;


}
