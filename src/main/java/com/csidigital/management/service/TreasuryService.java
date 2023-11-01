package com.csidigital.management.service;


import com.csidigital.shared.dto.request.TreasuryDtoRequest;
import com.csidigital.shared.dto.response.TreasuryDtoResponse;

import java.util.List;

public interface TreasuryService {
    //Get All Treasury

    List<TreasuryDtoResponse> getAllTreasury();

    //Get Treasury by id

    TreasuryDtoResponse getTreasuryById(Long id);

    //Add new Treasury

    TreasuryDtoResponse CreateTreasury(TreasuryDtoRequest treasuryDtoRequest);

    //Update Treasury by id
    TreasuryDtoResponse updateTreasury(TreasuryDtoRequest treasuryDtoRequest, Long id);

    //delete Treasury by id
    void deleteTreasuryById(Long id);



}
