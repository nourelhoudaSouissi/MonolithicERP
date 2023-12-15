package com.csidigital.management.service;

import com.csidigital.shared.dto.request.PaymentTermRequest;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.PaymentTermResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;

import java.util.List;

public interface TvaCodeService {
    TvaCodeResponse createTvaCode(TvaCodeRequest request);
    List<TvaCodeResponse> getAllTvaCodes();
    TvaCodeResponse getTvaCodeById(Long id);

    TvaCodeResponse updateTvaCode(TvaCodeRequest request, Long id);

    void deleteTvaCode(Long id);
}
