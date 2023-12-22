package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.CalculationUnit;
import com.csidigital.dao.entity.TvaCode;
import com.csidigital.dao.repository.CalculationUnitRepository;
import com.csidigital.dao.repository.TvaCodeRepository;
import com.csidigital.management.service.CalculationUnitService;
import com.csidigital.shared.dto.request.CalculationUnitRequest;
import com.csidigital.shared.dto.response.CalculationUnitResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;
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
public class CalculationUnitServiceImpl implements CalculationUnitService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CalculationUnitRepository calculationUnitRepository;

    @Override
    public CalculationUnitResponse createCalculationUnit(CalculationUnitRequest request) {
        CalculationUnit calculationUnit = modelMapper.map(request, CalculationUnit.class);
        CalculationUnit savedCalculationUnit= calculationUnitRepository.save(calculationUnit);
        return modelMapper.map(savedCalculationUnit, CalculationUnitResponse.class);
    }

    @Override
    public List<CalculationUnitResponse> getAllCalculationUnits() {
        List<CalculationUnit> calculationUnits = calculationUnitRepository.findAll();
        List<CalculationUnitResponse> calculationUnitResponses = new ArrayList<>();


        for (CalculationUnit calculationUnit: calculationUnits) {
            CalculationUnitResponse response = modelMapper.map(calculationUnit, CalculationUnitResponse.class);
            calculationUnitResponses.add(response);
        }

        return calculationUnitResponses;
    }

    @Override
    public CalculationUnitResponse getCalculationUnitById(Long id) {
        CalculationUnit calculationUnit = calculationUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CalculationUnit with id " + id + " not found"));
        CalculationUnitResponse calculationUnitResponse = modelMapper.map(calculationUnit, CalculationUnitResponse.class);
        return calculationUnitResponse;
    }

    @Override
    public CalculationUnitResponse updateCalculationUnit(CalculationUnitRequest request, Long id) {
        CalculationUnit existingCalculationUnit = calculationUnitRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("CalculationUnit with id: " + id + " not found"));
        modelMapper.map(request, existingCalculationUnit);
        CalculationUnit savedCalculationUnit = calculationUnitRepository.save(existingCalculationUnit);
        return modelMapper.map(savedCalculationUnit, CalculationUnitResponse.class);
    }

    @Override
    public void deleteCalculationUnit(Long id) {
        calculationUnitRepository.deleteById(id);    }
}
