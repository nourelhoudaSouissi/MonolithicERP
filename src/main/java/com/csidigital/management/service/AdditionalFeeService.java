package com.csidigital.management.service;

import com.csidigital.shared.dto.request.AdditionalFeeDtoRequest;
import com.csidigital.shared.dto.response.AdditionalFeeDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdditionalFeeService {
    //Get All Additional

    List<AdditionalFeeDtoResponse<Number>> getAllAdditionalFee();

    //Get AdditionalFee by id

    AdditionalFeeDtoResponse<Number> getAdditionalFeeById(Long id);
    //Add new AdditionalFee
    List<AdditionalFeeDtoResponse<Number>> CreateAdditionalFee(List<AdditionalFeeDtoRequest> additionalFeeDtoRequests);

    //Update AdditionalFee by id
    AdditionalFeeDtoResponse<Number> updateAdditionalFee(AdditionalFeeDtoRequest additionalFeeDtoRequest, Long id);

   //delete AdditionalFee by id
   void deleteAdditionalFeeById(Long id);


}
