package com.csidigital.management.controller;

import com.csidigital.management.service.impl.WeekendImpl;
import com.csidigital.shared.dto.request.WeekendRequest;
import com.csidigital.shared.dto.response.WeekendResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/weekend")
@CrossOrigin(origins = "http://localhost:4200")
public class WeekendController {

    @Autowired
    private WeekendImpl weekendImpl ;

    @GetMapping("getAllWeekends")
    public List<WeekendResponse> getAllWeekend() {

        return weekendImpl.getAllWeekend();
    }

    @GetMapping("/getWeekendById/{id}")
    public WeekendResponse getWeekendById(@PathVariable Long id){

        return weekendImpl.getWeekendById(id);
    }

    @PostMapping("/createWeekend")
    public WeekendResponse createWeekend(@Valid @RequestBody WeekendRequest weekendRequest){
        return weekendImpl.createWeekend(weekendRequest);
    }

    @PutMapping("/updateWeekend/{id}")
    public WeekendResponse updateWeekend(@Valid @RequestBody WeekendRequest weekendRequest,
                                         @PathVariable Long id){
        return weekendImpl.updateWeekend(weekendRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWeekend(@PathVariable Long id){

        weekendImpl.deleteWeekend(id);
    }
}
