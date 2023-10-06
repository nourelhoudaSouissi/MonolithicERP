package com.csidigital.dao.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer accountingPeriod;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Holiday> holidays;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WeekendUpdated> weekendUpdateds;
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CalendarMonth> calendarMonths;

}
