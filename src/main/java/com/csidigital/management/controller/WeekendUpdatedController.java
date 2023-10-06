package com.csidigital.management.controller;

import com.csidigital.management.service.impl.WeekendUpdatedImpl;
import com.csidigital.shared.dto.request.WeekendUpdatedRequest;
import com.csidigital.shared.dto.response.WeekendUpdatedResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/weekendUpdated")
public class WeekendUpdatedController {
    @Autowired
    private WeekendUpdatedImpl weekendUpdatedImpl;
    @GetMapping("getAllWeekendUpdated")
    public List<WeekendUpdatedResponse> getAllWeekendUpdated() {
        return weekendUpdatedImpl.getAllWeekendUpdated();
    }

    @GetMapping("/getWeekendUpdatedById/{id}")
    public WeekendUpdatedResponse getWeekendUpdatedById(@PathVariable Long id){
        return weekendUpdatedImpl.getWeekendUpdatedById(id);
    }

    @PostMapping("/createWeekendUpdated")
    public WeekendUpdatedResponse createWeekend(@Valid @RequestBody WeekendUpdatedRequest weekendUpdatedRequest){
        return weekendUpdatedImpl.createWeekendUpdated(weekendUpdatedRequest);
    }

    @PutMapping("/updateWeekendUpdated/{id}")
    public WeekendUpdatedResponse updateWeekendUpdated(@Valid @RequestBody WeekendUpdatedRequest weekendUpdatedRequest,
                                         @PathVariable Long id){
        return weekendUpdatedImpl.updateWeekendUpdated(weekendUpdatedRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWeekend(@PathVariable Long id){
        weekendUpdatedImpl.deleteWeekendUpdated(id);
    }

}
