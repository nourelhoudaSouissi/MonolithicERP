package com.csidigital.shared.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.DayOfWeek;
@Data
public class WeekendRequest {
    private String reference ;
    private String name;
    @Enumerated(EnumType.STRING)
    private DayOfWeek startDay;
    @Enumerated(EnumType.STRING)
    private DayOfWeek endDay;
}
