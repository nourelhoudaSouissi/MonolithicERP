package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.ContactNote;
import com.csidigital.shared.enumeration.Civility;
import com.csidigital.shared.enumeration.Privilege;
import com.csidigital.shared.enumeration.Service;
import lombok.Data;

import java.util.List;
@Data
public class PartnerContactResponse {
    private Long id;

    private Civility civility;
    private String lastName;
    private String firstName;
    private String fullName;
    private String function;
    private Service service;
    private String emailOne;
    private String emailTwo;
    private Long phoneNumberOne;
    private Long phoneNumberTwo;
    private String comment;
    private Privilege privilege;
    private List<ContactNote> contactNotes;
    private List<Appointment> appointments;
    private Long partnerId ;
}
