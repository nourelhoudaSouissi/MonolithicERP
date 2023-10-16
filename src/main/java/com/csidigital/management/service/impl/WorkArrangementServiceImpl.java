package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Benefit;
import com.csidigital.dao.entity.WorkArrangement;
import com.csidigital.dao.repository.BenefitRepository;
import com.csidigital.dao.repository.WorkArrangementRepository;
import com.csidigital.management.service.WorkArrangementService;
import com.csidigital.shared.dto.request.WorkArrangementRequest;
import com.csidigital.shared.dto.response.WorkArrangementResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkArrangementServiceImpl implements WorkArrangementService {
    @Autowired
    private BenefitRepository benefitRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WorkArrangementRepository workArrangementRepository ;
    @Override
    public WorkArrangementResponse createWorkArrangement(WorkArrangementRequest request) {
        Benefit benefit = benefitRepository.findById(request.getBenefitNum())
                .orElseThrow(() -> new ResourceNotFoundException("Benefit not found"));
       WorkArrangement workArrangement= modelMapper.map(request , WorkArrangement.class);
       workArrangement.setBenefit(benefit);
       WorkArrangement savedWorkArrangement = workArrangementRepository.save(workArrangement);
       return modelMapper.map(savedWorkArrangement, WorkArrangementResponse.class);

    }

    @Override
    public List<WorkArrangementResponse> getAllWorkArrangements() {
        List<WorkArrangement> workArrangements = workArrangementRepository.findAll();
        List<WorkArrangementResponse> workArrangementList = new ArrayList<>();
        for (WorkArrangement workArrangement: workArrangements) {
            WorkArrangementResponse response = modelMapper.map(workArrangement, WorkArrangementResponse.class);
            workArrangementList.add(response);
        }
            return  workArrangementList ;


    }

    @Override
    public WorkArrangementResponse getWorkArrangementById(Long id) {
        WorkArrangement workArrangement = workArrangementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Work arrangement with id " +id+ " not found"));
        WorkArrangementResponse workArrangementResponse = modelMapper.map(workArrangement, WorkArrangementResponse.class);
        return workArrangementResponse;
    }

    @Override
    public WorkArrangementResponse updateWorkArrangement( WorkArrangementRequest request , Long id) {
       WorkArrangement existingWorkArrangement = workArrangementRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Work arrangement with id " +id+ " not found"));
       modelMapper.map(request , existingWorkArrangement) ;
       WorkArrangement savedWorkArrangement = workArrangementRepository.save(existingWorkArrangement);
       return modelMapper.map(savedWorkArrangement , WorkArrangementResponse.class);


    }

    @Override
    public void deleteWorkArrangement(Long id) {
        workArrangementRepository.deleteById(id);

    }
}
