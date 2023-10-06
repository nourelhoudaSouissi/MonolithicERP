package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.CalendarMonth;
import com.csidigital.dao.repository.CalendarMonthRepository;
import com.csidigital.management.service.CalendarMonthService;
import com.csidigital.shared.dto.request.CalendarMonthRequest;
import com.csidigital.shared.dto.response.CalendarMonthResponse;
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
public class CalendarMonthImpl implements CalendarMonthService {

    @Autowired
    private CalendarMonthRepository calendarMonthRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CalendarMonthResponse createCalendarMonth(CalendarMonthRequest request) {
        CalendarMonth calendarMonth = modelMapper.map(request, CalendarMonth.class);
        CalendarMonth savedCalendarMonth = calendarMonthRepository.save(calendarMonth);
        return modelMapper.map(savedCalendarMonth, CalendarMonthResponse.class);
    }

    @Override
    public List<CalendarMonthResponse> getAllCalendarsMonth() {
        List<CalendarMonth> calendarMonths = calendarMonthRepository.findAll();
        List<CalendarMonthResponse> calendarMonthResponses = new ArrayList<>();


        for (CalendarMonth calendarMonth: calendarMonths) {
            CalendarMonthResponse response = modelMapper.map(calendarMonth, CalendarMonthResponse.class);
            calendarMonthResponses.add(response);
        }

        return calendarMonthResponses;
    }

    @Override
    public CalendarMonthResponse getCalendarMonthById(Long id) {
        CalendarMonth calendarMonth =calendarMonthRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("CalendarMonth with id " +id+ " not found"));
        CalendarMonthResponse calendarMonthResponse = modelMapper.map(calendarMonth, CalendarMonthResponse.class);
        return calendarMonthResponse;
    }

    @Override
    public CalendarMonthResponse updateCalendarMonth(CalendarMonthRequest request, Long id) {
        CalendarMonth existingCalendarMonth = calendarMonthRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("calendarMonth with id: " + id + " not found"));
        modelMapper.map(request, existingCalendarMonth);
        CalendarMonth savedCalendarMonth = calendarMonthRepository.save(existingCalendarMonth);
        return modelMapper.map(savedCalendarMonth, CalendarMonthResponse.class);
    }

    @Override
    public void deleteCalendarMonth(Long id) {
        calendarMonthRepository.deleteById(id);

    }
}
