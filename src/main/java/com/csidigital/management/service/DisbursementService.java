package com.csidigital.management.service;

import com.csidigital.shared.dto.request.DisbursementDtoRequest;
import com.csidigital.shared.dto.response.DisbursementDtoResponse;

import java.util.List;

public interface DisbursementService {
    //Get All Disbursement

    List<DisbursementDtoResponse> getAllDisbursement();

    //Get Disbursement by id

    DisbursementDtoResponse getByIdDisbursement(Long id);

    //Add new Disbursement

    DisbursementDtoResponse CreateDisbursement(DisbursementDtoRequest disbursementDtoRequest);

    //Update Disbursement by id
    DisbursementDtoResponse updateDisbursement(DisbursementDtoRequest disbursementDtoRequest, Long id);

    //delete Disbursement by id
    void deleteDisbursementById(Long id);
}
