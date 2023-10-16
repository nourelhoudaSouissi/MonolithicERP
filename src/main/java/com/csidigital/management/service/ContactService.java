package com.csidigital.management.service;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.ContactNote;
import com.csidigital.shared.dto.request.ContactRequest;
import com.csidigital.shared.dto.response.ContactResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContactService {
    public ContactResponse createContact(@RequestBody ContactRequest contactRequest);
    public List<ContactResponse>getAllContacts();
    public ContactResponse getContactById(@RequestParam Long id);
    List<Appointment> getContactAppointmentsById(Long id);
    List<ContactNote> getContactNotesById(Long id);
    public ContactResponse updateContact(@RequestParam Long id,
                                                       @RequestBody ContactRequest request);
    public void deleteContact(@RequestParam Long id);
}
