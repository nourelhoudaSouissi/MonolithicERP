package com.csidigital.management.controller;

import com.csidigital.management.service.impl.AppointmentServiceImpl;
import com.csidigital.shared.dto.request.AppointmentRequest;
import com.csidigital.shared.dto.response.AppointmentResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/appointments")
//@CrossOrigin(origins = "${cross.origin.url}")
public class AppointmentController {
    @Autowired
    private AppointmentServiceImpl appointmentService ;

    @GetMapping()
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentResponse getAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping()
    public AppointmentResponse createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.createAppointment(appointmentRequest);
    }

    @PutMapping("/{id}")
    public AppointmentResponse updateAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest,
                                                 @PathVariable Long id){
        return appointmentService.updateAppointment(appointmentRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
    }

}
