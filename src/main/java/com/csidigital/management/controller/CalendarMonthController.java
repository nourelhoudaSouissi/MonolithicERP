package com.csidigital.management.controller;


import com.csidigital.management.service.impl.CalendarMonthImpl;
import com.csidigital.shared.dto.request.CalendarMonthRequest;
import com.csidigital.shared.dto.response.CalendarMonthResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/calendarMonth")
public class CalendarMonthController {
    @Autowired
    private CalendarMonthImpl calendarMonthImpl;
    @GetMapping("getAllCalendarsMonth")
    public List<CalendarMonthResponse> getAllCalendarsMonth() {
        return calendarMonthImpl.getAllCalendarsMonth();
    }

    @GetMapping("/getCalendarMonthById/{id}")
    public CalendarMonthResponse getCalendarMonthById(@PathVariable Long id){

        return calendarMonthImpl.getCalendarMonthById(id);
    }

    @PostMapping( "/createCalendarMonth")
    public CalendarMonthResponse createCalendarMonth(@Valid @RequestBody CalendarMonthRequest calendarMonthRequest){
        System.out.println(calendarMonthRequest);
        return calendarMonthImpl.createCalendarMonth(calendarMonthRequest);
    }

    @PutMapping("/updateCalendarMonth/{id}")
    public CalendarMonthResponse updateCalendarMonth(@Valid @RequestBody CalendarMonthRequest calendarMonthRequest,
                                           @PathVariable Long id){
        return calendarMonthImpl.updateCalendarMonth(calendarMonthRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCalendarMonth(@PathVariable Long id){

        calendarMonthImpl.deleteCalendarMonth(id);
    }
}
