package com.csidigital.management.service;

import com.csidigital.shared.dto.request.CalculationUnitRequest;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.CalculationUnitResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;

import java.util.List;

public interface CalculationUnitService {
    CalculationUnitResponse createCalculationUnit(CalculationUnitRequest request);
    List<CalculationUnitResponse> getAllCalculationUnits();
    CalculationUnitResponse getCalculationUnitById(Long id);

    CalculationUnitResponse updateCalculationUnit(CalculationUnitRequest request, Long id);

    void deleteCalculationUnit(Long id);
}
