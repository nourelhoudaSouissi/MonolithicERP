package com.csidigital.shared.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    private Long calendarNum;
}
