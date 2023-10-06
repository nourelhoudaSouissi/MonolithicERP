package com.csidigital.management.service;

import com.csidigital.shared.dto.request.ExceptionalFeeRequest;
import com.csidigital.shared.dto.response.ExceptionalFeeResponse;

import java.util.List;

public interface ExceptionalFeeService {
    ExceptionalFeeResponse createExceptionalFee(ExceptionalFeeRequest request);
    List<ExceptionalFeeResponse> getAllExceptionalFees();
    ExceptionalFeeResponse getExceptionalFeeById(Long id);

    ExceptionalFeeResponse updateExceptionalFee(ExceptionalFeeRequest request, Long id);

    void deleteExceptionalFee(Long id);

}
