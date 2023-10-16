package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.AppointmentNote;
import com.csidigital.dao.repository.AppointmentNoteRepository;
import com.csidigital.dao.repository.AppointmentRepository;
import com.csidigital.management.service.AppointmentNoteService;
import com.csidigital.shared.dto.request.AppointmentNoteRequest;
import com.csidigital.shared.dto.response.AppointmentNoteResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AppointmentNoteServiceImpl implements AppointmentNoteService {
    @Autowired
    private AppointmentRepository appointmentRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AppointmentNoteRepository appointmentNoteRepository;

    @Override
    public AppointmentNoteResponse createAppointmentNote(AppointmentNoteRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentNum())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        AppointmentNote appointmentNote = modelMapper.map(request, AppointmentNote.class);
        appointmentNote.setAppointment(appointment);
        AppointmentNote savedAppointmentNote = appointmentNoteRepository.save(appointmentNote);
        return modelMapper.map(savedAppointmentNote, AppointmentNoteResponse.class);
    }

    @Override
    public List<AppointmentNoteResponse> getAllAppointmentNotes() {
        List<AppointmentNote> appointmentNotes = appointmentNoteRepository.findAll();
        List<AppointmentNoteResponse> appointmentNoteList = new ArrayList<>();

        for (AppointmentNote appointmentNote : appointmentNotes) {
            AppointmentNoteResponse response = modelMapper.map(appointmentNote, AppointmentNoteResponse.class);
            appointmentNoteList.add(response);
        }

        return appointmentNoteList;
    }

    @Override
    public AppointmentNoteResponse getAppointmentNoteById(Long id) {
        AppointmentNote appointmentNote = appointmentNoteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment note with id " +id+ " not found"));
        AppointmentNoteResponse appointmentNoteResponse = modelMapper.map(appointmentNote, AppointmentNoteResponse.class);
        return appointmentNoteResponse;
    }

    @Override
    public AppointmentNoteResponse updateAppointmentNote(AppointmentNoteRequest request, Long id) {
        AppointmentNote existingAppointmentNote = appointmentNoteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment note with id: " + id + " not found"));
        modelMapper.map(request, existingAppointmentNote);
        AppointmentNote savedAppointmentNote = appointmentNoteRepository.save(existingAppointmentNote);
        return modelMapper.map(savedAppointmentNote, AppointmentNoteResponse.class);
    }

    @Override
    public void deleteAppointmentNote(Long id) {
        appointmentNoteRepository.deleteById(id);
    }
}

