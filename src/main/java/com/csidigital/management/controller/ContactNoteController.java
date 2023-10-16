package com.csidigital.management.controller;

import com.csidigital.management.service.impl.ContactNoteServiceImpl;
import com.csidigital.shared.dto.request.ContactNoteRequest;
import com.csidigital.shared.dto.response.ContactNoteResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/contactNotes")
//@CrossOrigin(origins = "${cross.origin.url}")
public class ContactNoteController {
    @Autowired
    private ContactNoteServiceImpl contactNoteService ;

    @GetMapping()
    public List<ContactNoteResponse> getAllContactNotes() {
        return contactNoteService.getAllContactNotes();
    }

    @GetMapping("/{id}")
    public ContactNoteResponse getContactNoteById(@PathVariable Long id){
        return contactNoteService.getContactNoteById(id);
    }

    @PostMapping()
    public ContactNoteResponse createContactNote(@Valid @RequestBody ContactNoteRequest contactNoteRequest){
        return contactNoteService.createContactNote(contactNoteRequest);
    }

    @PutMapping("/{id}")
    public ContactNoteResponse updateContactNote(@Valid @RequestBody ContactNoteRequest contactNoteRequest,
                                                 @PathVariable Long id){
        return contactNoteService.updateContactNote(contactNoteRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteContactNote(@PathVariable Long id){
        contactNoteService.deleteContactNote(id);
    }
}
