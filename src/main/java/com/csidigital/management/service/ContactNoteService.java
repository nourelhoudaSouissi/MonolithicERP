package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ContactNoteRequest;
import com.csidigital.shared.dto.response.ContactNoteResponse;

import java.util.List;

public interface ContactNoteService {
    ContactNoteResponse createContactNote(ContactNoteRequest request);
    List<ContactNoteResponse> getAllContactNotes();
    ContactNoteResponse getContactNoteById(Long id);

    ContactNoteResponse updateContactNote(ContactNoteRequest request, Long id);

    void deleteContactNote(Long id);
}
