package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Holiday;
import com.csidigital.dao.repository.HolidayRepository;
import com.csidigital.management.service.HolidayService;
import com.csidigital.shared.dto.request.HolidayRequest;
import com.csidigital.shared.dto.response.HolidayResponse;
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
public class HolidayImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HolidayResponse createHoliday(HolidayRequest request) {
        Holiday holiday = modelMapper.map(request, Holiday.class);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return modelMapper.map(savedHoliday, HolidayResponse.class);
    }

    @Override
    public List<HolidayResponse> getAllHolidays() {
        List<Holiday> holidays = holidayRepository.findAll();
        List<HolidayResponse> holidaysResponses = new ArrayList<>();


        for (Holiday holiday: holidays) {
            HolidayResponse response = modelMapper.map(holiday, HolidayResponse.class);
            holidaysResponses.add(response);
        }

        return holidaysResponses;
    }

    @Override
    public HolidayResponse getHolidayById(Long id) {
        Holiday holiday =holidayRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Holiday with id " +id+ " not found"));
        HolidayResponse holidayResponse = modelMapper.map(holiday, HolidayResponse.class);
        return holidayResponse;
    }

    @Override
    public HolidayResponse updateHoliday(HolidayRequest request, Long id) {
        Holiday existingHoliday = holidayRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Holiday with id: " + id + " not found"));
        modelMapper.map(request, existingHoliday);
        Holiday savedHoliday = holidayRepository.save(existingHoliday);
        return modelMapper.map(savedHoliday, HolidayResponse.class);
    }

    @Override
    public void deleteHoliday(Long id) {

        holidayRepository.deleteById(id);
    }
}
