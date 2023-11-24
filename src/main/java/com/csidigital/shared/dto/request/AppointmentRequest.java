package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.AppointmentNote;
import com.csidigital.shared.enumeration.AppointmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data

public class AppointmentRequest {
    private LocalDate date;
    private LocalTime time;
    private String location;
    private Long duration;
    private String subject;
    private List<AppointmentNote> notes;
    private Long contactNum;
    @Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;
    private String noteBefore;
    private String noteAfter;

}
