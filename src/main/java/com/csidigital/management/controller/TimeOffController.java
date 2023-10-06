package com.csidigital.management.controller;

import com.csidigital.management.service.impl.TimeOffImpl;
import com.csidigital.shared.dto.request.TimeOffRequest;
import com.csidigital.shared.dto.response.TimeOffResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/TimeOff")
public class TimeOffController {
    @Autowired
    private TimeOffImpl timeOffImpl ;

    @GetMapping("getAll")
    public List<TimeOffResponse> getAllTimeOff() {
        return timeOffImpl.getAllTimeOff();
    }

    @GetMapping("/getBy/{id}")
    public TimeOffResponse getTimeOffById(@PathVariable Long id){
        return timeOffImpl.getTimeOffById(id);
    }

    @PostMapping("/add")
    public TimeOffResponse createTimeOff(@Valid @RequestBody TimeOffRequest timeOffRequest){
        return timeOffImpl.createTimeOff(timeOffRequest);
    }

    @PutMapping("/update/{id}")
    public TimeOffResponse updateTimeOff(@Valid @RequestBody TimeOffRequest timeOffRequest,
                                                   @PathVariable Long id){
        return timeOffImpl.updateTimeOff(timeOffRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTimeOff(@PathVariable Long id){
        timeOffImpl.deleteTimeOff(id);
    }
    @PutMapping("/updateStatusToValidatedById/{id}")
    void updateStatusToValidatedById(@PathVariable Long id){
        timeOffImpl.updateStatusToValidatedById(id);
    }
    @PutMapping("/updateStatusToRejectedById/{id}")
    void updateStatusToRejectedById(@PathVariable Long id){
        timeOffImpl.updateStatusToRejectedById(id);
    }


    @GetMapping("getLeaveTypeDurationsByEmployee/{employeeId}")
    public List<Object[]> getLeaveTypeDurationsByEmployee(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationByLeaveTypeAndEmployeeId(employeeId);
    }

    @GetMapping("getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId/{employeeId}")
    public List<Object[]> getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(employeeId);
    }
    @GetMapping("getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId/{employeeId}")
    public List<Object[]> getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(employeeId);
    }

    @GetMapping("getTotalDurationSpecialPaidLeaveEmployeeId/{employeeId}")
    public Double getTotalDurationSpecialPaidLeaveEmployeeId(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationSpecialPaidLeaveEmployeeId(employeeId);
    }
    @GetMapping("getTotalDurationSicknessLeaveEmployeeId/{employeeId}")
    public Double getTotalDurationSicknessLeaveEmployeeId(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationSicknessLeaveEmployeeId(employeeId);
    }

    @GetMapping("getTotalDurationPaidLeaveEmployeeId/{employeeId}")
    public Double getTotalDurationPaidLeaveEmployeeId(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationPaidLeaveEmployeeId(employeeId);
    }
    @GetMapping("getTotalDurationUnpaidLeaveEmployeeId/{employeeId}")
    public Double getTotalDurationUnpaidLeaveEmployeeId(@PathVariable Long employeeId) {
        return timeOffImpl.getTotalDurationUnpaidLeaveEmployeeId(employeeId);
    }

    @GetMapping("/countAllPending")
    public int countPending(){

        return timeOffImpl.countPending();
    }

    @GetMapping("/countAllValidated")
    public int countValidated(){

        return timeOffImpl.countValidated();
    }

    @GetMapping("/countAllRejected")
    public int countRejected(){

        return timeOffImpl.countRejected();
    }
}

