package com.csidigital.shared.dto.request;


import lombok.Data;


@Data

public class ContactNoteRequest {
    private String  subject;
    private String note;
    private Long contactNum;
}
