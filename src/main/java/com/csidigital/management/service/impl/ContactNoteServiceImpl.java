package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.entity.ContactNote;
import com.csidigital.dao.repository.ContactNoteRepository;
import com.csidigital.dao.repository.ContactRepository;
import com.csidigital.management.service.ContactNoteService;
import com.csidigital.shared.dto.request.ContactNoteRequest;
import com.csidigital.shared.dto.response.ContactNoteResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContactNoteServiceImpl implements ContactNoteService {
    @Autowired
    private ContactNoteRepository contactNoteRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactNoteResponse createContactNote(ContactNoteRequest request) {
        Contact contact = contactRepository.findById(request.getContactNum())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        ContactNote contactNote = modelMapper.map(request, ContactNote.class);
        contactNote.setContact(contact);
        ContactNote contactNoteSaved = contactNoteRepository.save(contactNote);
        return modelMapper.map(contactNoteSaved, ContactNoteResponse.class);
    }

    @Override
    public List<ContactNoteResponse> getAllContactNotes() {
        List<ContactNote> contactNotes = contactNoteRepository.findAll();
        List<ContactNoteResponse> contactNoteList = new ArrayList<>();

        for (ContactNote contactNote : contactNotes) {
            ContactNoteResponse response = modelMapper.map(contactNote, ContactNoteResponse.class);
            contactNoteList.add(response);
        }

        return contactNoteList;
    }

    @Override
    public ContactNoteResponse getContactNoteById(Long id) {
        ContactNote contactNote = contactNoteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ContactNote with id " +id+ " not found"));
        ContactNoteResponse contactNoteResponse = modelMapper.map(contactNote, ContactNoteResponse.class);
        return contactNoteResponse;
    }

    @Override
    public ContactNoteResponse updateContactNote(ContactNoteRequest request, Long id) {
        ContactNote existingContactNote = contactNoteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ContactNote with id: " + id + " not found"));
        modelMapper.map(request, existingContactNote);
        ContactNote savedContactNote = contactNoteRepository.save(existingContactNote);
        return modelMapper.map(savedContactNote, ContactNoteResponse.class);
    }

    @Override
    public void deleteContactNote(Long id) {
        contactNoteRepository.deleteById(id);
    }
}
