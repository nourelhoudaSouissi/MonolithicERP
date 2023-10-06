package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.LeaveType;
import com.csidigital.dao.repository.LeaveTypeRepository;
import com.csidigital.management.service.LeaveTypeService;
import com.csidigital.shared.dto.request.LeaveTypeRequest;
import com.csidigital.shared.dto.response.LeaveTypeResponse;
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
public class LeaveTypeImpl implements LeaveTypeService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LeaveTypeResponse createLeaveType(LeaveTypeRequest request) {
        LeaveType leaveType = modelMapper.map(request, LeaveType.class);
        LeaveType savedLeaveType = leaveTypeRepository.save(leaveType);
        return modelMapper.map(savedLeaveType, LeaveTypeResponse.class);
    }

    @Override
    public List<LeaveTypeResponse> getAllLeaveTypes() {
        List<LeaveType> leaveTypes = leaveTypeRepository.findAll();
        List<LeaveTypeResponse> leaveTypeResponses = new ArrayList<>();
        for (LeaveType leaveType: leaveTypes) {
            LeaveTypeResponse response = modelMapper.map(leaveType, LeaveTypeResponse.class);
            leaveTypeResponses.add(response);
        }
        return leaveTypeResponses;
    }

    @Override
    public LeaveTypeResponse getLeaveTypeById(Long id) {
        LeaveType leaveType =leaveTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Leave Type with id " +id+ " not found"));
        LeaveTypeResponse leaveTypeResponse = modelMapper.map(leaveType, LeaveTypeResponse.class);
        return leaveTypeResponse;
    }

    @Override
    public LeaveTypeResponse updateLeaveType(LeaveTypeRequest request, Long id) {
        LeaveType existingLeaveType = leaveTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("LeaveType with id: " + id + " not found"));
        modelMapper.map(request, existingLeaveType);
        LeaveType savedLeaveType = leaveTypeRepository.save(existingLeaveType);
        return modelMapper.map(savedLeaveType, LeaveTypeResponse.class);
    }

    @Override
    public Double getTotalDurationSicknessLeave() {
        return leaveTypeRepository.getTotalDurationSicknessLeave();
    }

    @Override
    public Double getTotalDurationSpecialPaidLeave() {
        return leaveTypeRepository.getTotalDurationSpecialPaidLeave();
    }

    @Override
    public List<Object[]>  getSicknessLeaveList() {
        return leaveTypeRepository.getSicknessLeaveList();
    }

    @Override
    public List<Object[]>  getSpecialPaidLeaveList() {
        return leaveTypeRepository.getSpecialPaidLeaveList();
    }

    @Override
    public void deleteLeaveType(Long id) {
        leaveTypeRepository.deleteById(id);
    }
}
