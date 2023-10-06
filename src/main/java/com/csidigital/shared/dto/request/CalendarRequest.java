package com.csidigital.shared.dto.request;


import com.csidigital.dao.entity.CalendarMonth;
import com.csidigital.dao.entity.Holiday;
import com.csidigital.dao.entity.WeekendUpdated;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CalendarRequest {
    private String reference ;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer accountingPeriod;
    private List<Holiday> holidays;
    private List<WeekendUpdated> weekendUpdateds;
    private List<CalendarMonth> calendarMonths;

}
