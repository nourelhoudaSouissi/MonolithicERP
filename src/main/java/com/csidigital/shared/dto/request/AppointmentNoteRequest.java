package com.csidigital.shared.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentNoteRequest {
    private LocalDate contactDate;
    private String  subject;
    private String comment;
    private String discussionNote;

    private Long appointmentNum;
}
