package com.csidigital.shared.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.time.DayOfWeek;

@Data
public class WeekendResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;
    @Enumerated(EnumType.STRING)
    private DayOfWeek startDay;
    @Enumerated(EnumType.STRING)
    private DayOfWeek endDay;

}
