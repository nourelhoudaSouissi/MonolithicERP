package com.csidigital.shared.dto.response;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
public class WeekendUpdatedResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "startDay")
    private DayOfWeek startDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "endDay")
    private DayOfWeek endDay;

    private LocalDate activationStartDate;
    private LocalDate activationEndDate;

}
