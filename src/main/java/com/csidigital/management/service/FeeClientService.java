package com.csidigital.management.service;

import com.csidigital.shared.dto.request.FeeClientDtoRequest;
import com.csidigital.shared.dto.response.FeeClientDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeeClientService {
    //Get All FeeClient

    List<FeeClientDtoResponse<Number>> getAllFeeClient();

    //Get FeeClient by id

    FeeClientDtoResponse<Number> getFeeClientById(Long id);
    //Add new FeeClient

    List<FeeClientDtoResponse<Number>> CreateFeeClient(List<FeeClientDtoRequest> feeClientDtoRequests);

    //Update FeeClient by id
    FeeClientDtoResponse<Number> updateFeeClient(FeeClientDtoRequest feeClientDtoRequest, Long id);

    //delete FeeClient by id
    void deleteFeeClientById(Long id);
}
