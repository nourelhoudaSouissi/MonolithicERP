package com.csidigital.shared.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class CalendarMonthResponse {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;



    private Integer ordre;

    private  String name;

    private Integer duration;
    private Long calendarId;
}
