package com.csidigital.shared.dto.response;

import lombok.Data;

@Data
public class ContactNoteResponse {
    private Long id ;
    private String  subject;
    private String note;

    private Long contactId;
}
