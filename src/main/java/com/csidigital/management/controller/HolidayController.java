package com.csidigital.management.controller;

import com.csidigital.management.service.impl.HolidayImpl;
import com.csidigital.shared.dto.request.HolidayRequest;
import com.csidigital.shared.dto.response.HolidayResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/holiday")
public class HolidayController {
    @Autowired
    private HolidayImpl holidayImpl ;

    @GetMapping("getAllHolidays")
    public List<HolidayResponse> getAllHolidays() {
        return holidayImpl.getAllHolidays();
    }

    @GetMapping("/getHolidayById/{id}")
    public HolidayResponse getHolidayById(@PathVariable Long id){
    return holidayImpl.getHolidayById(id);
    }

    @PostMapping("/addHoliday")
    public HolidayResponse createHoliday(@Valid @RequestBody HolidayRequest holidayRequest){
        return holidayImpl.createHoliday(holidayRequest);
    }

    @PutMapping("/updateHoliday/{id}")
    public HolidayResponse updateHoliday(@Valid @RequestBody HolidayRequest holidayRequest,
                                     @PathVariable Long id){
        return holidayImpl.updateHoliday(holidayRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHoliday(@PathVariable Long id){

        holidayImpl.deleteHoliday(id);
    }
}
