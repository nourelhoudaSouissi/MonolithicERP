package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.ContactNote;
import com.csidigital.shared.enumeration.Civility;
import com.csidigital.shared.enumeration.Privilege;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class ContactResponse {
    private Long contactId;
    @Enumerated(EnumType.STRING)
    private Civility civility;
    private String lastName;
    private String firstName;
    private String fullName;
    private String localisation;
    private String function;
    private String societe ;
    private String service;
    private String email;
    private Long phoneNumber;
    private Long mobilePhoneNumber;
    private boolean privilegedContact;
    private boolean appointmentMaking;
    private String comment;
    @Nullable
    @Enumerated(EnumType.STRING)
    private Privilege privilege;
    private Long partnerId ;
    private List<ContactNote> contactNotes;
    private List<Appointment> appointments;
}
