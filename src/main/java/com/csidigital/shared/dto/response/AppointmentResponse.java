package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.AppointmentNote;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AppointmentResponse {
    private Long Id ;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private Long duration;
    private String subject;
    private Long contactId;
    private List<AppointmentNote> notes;
    private String contactFullName;

}
