package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.ContactNote;
import com.csidigital.shared.enumeration.Civility;
import lombok.Data;

import java.util.List;

@Data
public class ContactRequest {
    private Civility civility;
    private String lastName;
    private String firstName;
    private String localisation;
    private String function;
    private String societe;
    private String service;
    private String email;
    private Long phoneNumber;
    private Long mobilePhoneNumber;
    private boolean privilegedContact;
    private boolean appointmentMaking;
    private String comment;
    private String privilege;
    private List<ContactNote> contactNotes;
    private List<Appointment> appointments;
    private Long partnerNum ;
}
