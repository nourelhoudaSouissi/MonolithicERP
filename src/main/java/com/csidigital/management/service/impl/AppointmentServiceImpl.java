package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Appointment;
import com.csidigital.dao.entity.Contact;
import com.csidigital.dao.repository.AppointmentRepository;
import com.csidigital.dao.repository.ContactRepository;
import com.csidigital.management.service.AppointmentService;
import com.csidigital.shared.dto.request.AppointmentRequest;
import com.csidigital.shared.dto.response.AppointmentResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest request) {
        Contact contact = contactRepository.findById(request.getContactNum())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        Appointment appointment = modelMapper.map(request, Appointment.class);

        appointment.setContact(contact);
        Appointment appointmentSaved = appointmentRepository.save(appointment);
        return modelMapper.map(appointmentSaved, AppointmentResponse.class);
    }



    @Override
    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentResponse> appointmentList = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
            response.setContactFullName(appointment.getContact().getFullName());
            appointmentList.add(response);
        }
        return appointmentList;
    }
    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment with id " +id+ " not found"));
        AppointmentResponse appointmentResponse = modelMapper.map(appointment, AppointmentResponse.class);
        return appointmentResponse;
    }

    @Override
    public AppointmentResponse updateAppointment(AppointmentRequest request, Long id) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment with id: " + id + " not found"));
        modelMapper.map(request, existingAppointment);
        Appointment savedAppointment = appointmentRepository.save(existingAppointment);
        return modelMapper.map(savedAppointment, AppointmentResponse.class);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}

