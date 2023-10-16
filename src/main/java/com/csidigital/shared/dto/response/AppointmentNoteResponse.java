package com.csidigital.shared.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentNoteResponse {
    private Long id;
    private LocalDate contactDate;
    private String  subject;
    private String comment;
    private String discussionNote;

    private Long appointmentId;
}
