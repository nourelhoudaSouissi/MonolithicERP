package com.csidigital.management.service;

import com.csidigital.shared.dto.request.BillClientDtoRequest;
import com.csidigital.shared.dto.response.BillClientDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillClientService {
    //Get All BillClient

    List<BillClientDtoResponse> getAllBillClients();

    //Get BillClient by id

    BillClientDtoResponse getBillClientById(Long id);
    //Add new BillClient

    BillClientDtoResponse CreateBillClient(BillClientDtoRequest billClientDtoRequest);

    //Update BillClient by id
    BillClientDtoResponse updateBillClient(BillClientDtoRequest billClientDtoRequest, Long id);

    //delete BillClient by id
    void deleteBillClientById(Long id);
}
