package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Availability;
import com.csidigital.dao.entity.Employee;
import com.csidigital.dao.repository.AvailabilityRepository;
import com.csidigital.dao.repository.EmployeeRepository;
import com.csidigital.management.service.AvailabilityService;
import com.csidigital.shared.dto.request.AvailabilityRequest;
import com.csidigital.shared.dto.response.AvailabilityResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor


public class AvailabilityImpl implements AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository ;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AvailabilityResponse createAvailability(AvailabilityRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeNum()).orElseThrow();
        Availability availability = modelMapper.map(request, Availability.class);
        availability.setEmployee(employee);
        Availability AvailabilitySaved = availabilityRepository.save(availability);
        return modelMapper.map(AvailabilitySaved, AvailabilityResponse.class);
    }

    @Override
    public List<AvailabilityResponse> getAllAvailability() {
        List<Availability> availability = availabilityRepository.findAll();
        List<AvailabilityResponse> availabilityList = new ArrayList<>();


        for (Availability availabilities: availability) {
            AvailabilityResponse response = modelMapper.map(availabilities, AvailabilityResponse.class);
            availabilityList.add(response);
        }

        return availabilityList;
    }

    @Override
    public AvailabilityResponse getAvailabilityById(Long id) {
        Availability availability =availabilityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Availability with id " +id+ " not found"));
        AvailabilityResponse availabilityResponse = modelMapper.map(availability, AvailabilityResponse.class);
        return availabilityResponse;
    }

    @Override
    public AvailabilityResponse updateAvailability(AvailabilityRequest request, Long id) {
        Availability existingAvailability = availabilityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Availability with id: " + id + " not found"));
        modelMapper.map(request, existingAvailability);
        Availability savedAvailability = availabilityRepository.save(existingAvailability);
        return modelMapper.map(savedAvailability, AvailabilityResponse.class);
    }

    @Override
    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

}
