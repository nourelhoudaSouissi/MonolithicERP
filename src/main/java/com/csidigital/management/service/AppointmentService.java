package com.csidigital.management.service;

import com.csidigital.shared.dto.request.AppointmentRequest;
import com.csidigital.shared.dto.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    AppointmentResponse createAppointment(AppointmentRequest request);
    List<AppointmentResponse> getAllAppointments();
    AppointmentResponse getAppointmentById(Long id);

    AppointmentResponse updateAppointment(AppointmentRequest request, Long id);

    void deleteAppointment(Long id);
}

