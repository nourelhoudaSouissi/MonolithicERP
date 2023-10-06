package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.CalendarService;
import com.csidigital.shared.dto.request.CalendarRequest;
import com.csidigital.shared.dto.response.CalendarResponse;
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
public class CalendarImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository ;
    @Autowired
    private WeekendUpdatedRepository weekendUpdatedRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private CalendarMonthRepository calendarMonthRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public CalendarResponse createCalendar(CalendarRequest request) {

        Calendar calendar = modelMapper.map(request, Calendar.class);
        Calendar savedCalendar = calendarRepository.save(calendar);
        List<Holiday> holidays = request.getHolidays();
        List<CalendarMonth> calendarMonths = request.getCalendarMonths();

        String referencePrefix = "Calendar";
        Long calendarCount = calendarRepository.countCalendars();
        String calendarSuffix = String.format("%04d", calendarCount);

        String finalReference = referencePrefix + "_" + calendarSuffix;
        savedCalendar.setReference(finalReference);

       if (holidays != null) {
            for (Holiday holiday : holidays) {
                holiday.setCalendar(savedCalendar);
                //holiday.setId(null);
                holidayRepository.save(holiday);
            }
        }
            for (int i = 0; i < request.getWeekendUpdateds().size(); i++) {
                //savedCalendar.getWeekendUpdateds().add(request.getWeekendUpdateds().get(i));
                request.getWeekendUpdateds().get(i).setCalendar(savedCalendar);
             //   request.getWeekendUpdateds().get(i).setId(null);
                weekendUpdatedRepository.save(request.getWeekendUpdateds().get(i));
        }

        if (calendarMonths != null) {
            for (CalendarMonth calendarMonth : calendarMonths) {
                calendarMonth.setCalendar(savedCalendar);
                //holiday.setId(null);
                calendarMonthRepository.save(calendarMonth);
            }
        }
        return modelMapper.map(savedCalendar, CalendarResponse.class);
    }


    @Override
    public List<CalendarResponse> getAllCalendars() {
        List<Calendar> calendars = calendarRepository.findAll();
        List<CalendarResponse> cdrRespons = new ArrayList<>();

        for (Calendar calendar : calendars) {
            CalendarResponse response = modelMapper.map(calendar, CalendarResponse.class);
            cdrRespons.add(response);
        }

        return cdrRespons;
    }

    @Override
    public CalendarResponse getCalendarById(Long id) {
        Calendar calendar =calendarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Calendar with id " +id+ " not found"));
        CalendarResponse calendarResponse = modelMapper.map(calendar, CalendarResponse.class);
        return calendarResponse;
    }

    @Override
    public CalendarResponse updateCalendar(CalendarRequest request, Long id) {
        Calendar existingCalendar = calendarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Calendar with id: " + id + " not found"));
        modelMapper.map(request, existingCalendar);
        Calendar savedCalendar = calendarRepository.save(existingCalendar);
        List<Holiday> holidays = request.getHolidays();
        List<WeekendUpdated> weekendUpdateds = request.getWeekendUpdateds();
        List<CalendarMonth> calendarMonths = request.getCalendarMonths();

        for (Holiday holiday:
                holidays) {
            holiday.setCalendar(savedCalendar);
            holidayRepository.save(holiday);
        }

        for (CalendarMonth calendarMonth:
                calendarMonths) {
            calendarMonth.setCalendar(savedCalendar);
            calendarMonthRepository.save(calendarMonth);
        }

        for (WeekendUpdated weekendUpdated :
                weekendUpdateds) {
            weekendUpdated.setCalendar(savedCalendar);
            weekendUpdatedRepository.save(weekendUpdated);
        }
        return modelMapper.map(savedCalendar, CalendarResponse.class);
    }

    @Override
    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);

    }

    @Override
    public Long countCalendars() {
       return calendarRepository.countCalendars();
    }

    @Override
    public List<WeekendUpdated> getCalendarWeekends(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar with id " + id + " not found"));
        List<WeekendUpdated> weekendUpdateds = calendar.getWeekendUpdateds();

        return weekendUpdateds;
    }

    @Override
    public List<Holiday> getCalendarHolidays(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar with id " + id + " not found"));
        List<Holiday> holidays = calendar.getHolidays();

        return holidays;
    }

    @Override
    public List<CalendarMonth> getCalendarMonths(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar with id " + id + " not found"));
        List<CalendarMonth> calendarMonths = calendar.getCalendarMonths();

        return calendarMonths;    }
}
