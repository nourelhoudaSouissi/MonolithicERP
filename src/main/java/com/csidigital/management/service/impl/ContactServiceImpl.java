package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.entity.ContactNote;
import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.repository.ContactRepository;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.management.service.ContactService;
import com.csidigital.shared.dto.request.ContactRequest;
import com.csidigital.shared.dto.response.ContactResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public ContactResponse createContact(ContactRequest request) {
        Partner partner = null;
        if(request.getPartnerNum()!=null) {
            partner = partnerRepository.findById(request.getPartnerNum())
                    .orElseThrow();
        }
        Contact contact = modelMapper.map(request, Contact.class);
        contact.setPartner(partner);
        Contact contactSaved = contactRepository.save(contact);
        return modelMapper.map(contactSaved, ContactResponse.class);
    }

    @Override
    public List<ContactResponse> getAllContacts() {
        List<Contact> contactPartners = contactRepository.findAll();
        List<ContactResponse> contactList = new ArrayList<>();

        for (Contact contact : contactPartners) {
            ContactResponse response = modelMapper.map(contact, ContactResponse.class);
            contactList.add(response);
        }
        return contactList;
    }

    @Override
    public ContactResponse getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("PartnerContact with id " +id+ " not found"));
        ContactResponse contactResponse = modelMapper.map(contact, ContactResponse.class);
        return contactResponse;
    }

    @Override
    public List<Appointment> getContactAppointmentsById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Contact with id " +id+ " not found"));
        List<Appointment> appointments = contact.getAppointments();
        return appointments;
    }

    @Override
    public List<ContactNote> getContactNotesById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Contact with id " +id+ " not found"));
        List<ContactNote> contactNotes = contact.getContactNotes();
        return contactNotes;
    }

    @Override
    public ContactResponse updateContact(Long id, ContactRequest request) {
        Partner partner = null;
        if(request.getPartnerNum()!=null) {
            partner = partnerRepository.findById(request.getPartnerNum())
                    .orElseThrow();
        }
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("PartnerContact with id: " + id + " not found"));
        modelMapper.map(request, existingContact);
        existingContact.setPartner(partner);
        Contact savedContactPartner = contactRepository.save(existingContact);
        return modelMapper.map(savedContactPartner, ContactResponse.class);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
