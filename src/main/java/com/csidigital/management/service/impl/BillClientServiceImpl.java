package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.BillClientRepository;
import com.csidigital.management.service.BillClientService;
import com.csidigital.shared.dto.request.BillClientDtoRequest;
import com.csidigital.shared.dto.response.BillClientDtoResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class BillClientServiceImpl implements BillClientService {
    @Autowired
    private BillClientRepository billClientRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BillClientDtoResponse> getAllBillClients() {
        List<BillClient> billsClient = billClientRepository.findAll();
        List<BillClientDtoResponse> billClientList = new ArrayList<>();

        for (BillClient billClient : billsClient) {
            BillClientDtoResponse billClientResponse = modelMapper.map(billClient, BillClientDtoResponse.class);
            billClientList.add(billClientResponse );
        }

        return billClientList;
    }

    @Override
    public BillClientDtoResponse getBillClientById(Long id) {
        BillClient billClient = billClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("BillClient with id " +id+ " not found"));
        BillClientDtoResponse billClientDtoResponse = modelMapper.map(billClient, BillClientDtoResponse.class);
        return billClientDtoResponse;
    }

    @Override
    public BillClientDtoResponse CreateBillClient(BillClientDtoRequest billClientDtoRequest) {
        BillClient billClient = modelMapper.map(billClientDtoRequest, BillClient.class);

        // set the Bill object to each AdditionalFee object
        for(FeeClient feeClient : billClient.getFeeClients()) {
            feeClient.setBillClient(billClient);
        }

        BillClient BillClientSaved = billClientRepository.save(billClient);
        return modelMapper.map(BillClientSaved, BillClientDtoResponse.class);
    }


    @Override
    public BillClientDtoResponse updateBillClient(BillClientDtoRequest billClientDtoRequest, Long id) {
        BillClient billClient = billClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("BillClient with id: " + id + " not found"));

        // Vérifier si l'objet BillClient est lié à un objet Collection
        if (billClient.getCollection() != null) {
            throw new IllegalStateException("Cannot update a BillClient that is linked to a Collection");
        }

        // Supprimer toutes les additionalFees existantes liées au Bill
        List<FeeClient> feeClientsToDelete = billClient.getFeeClients();
        for (FeeClient feeClient : feeClientsToDelete) {
            feeClient.setBillClient(null);
        }
        modelMapper.map(billClientDtoRequest, billClient);

        // Ajouter les nouvelles additionalFees avec le Bill lié (en OneToMany)
        List<FeeClient> newFeeClients = new ArrayList<>();
        for (FeeClient feeClientDtoRequest : billClientDtoRequest.getFeeClients()) {
            FeeClient feeClient = modelMapper.map(feeClientDtoRequest, FeeClient.class);
            feeClient.setBillClient(billClient);
            newFeeClients.add(feeClient);
        }
        billClient.setFeeClients(newFeeClients);

        BillClient updatedBillClient = billClientRepository.save(billClient);
        return modelMapper.map(updatedBillClient, BillClientDtoResponse.class);
    }


    @Override
    public void deleteBillClientById(Long id) {
        BillClient billClient = billClientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BillClient with id: " + id + " not found"));

        if (billClient.getCollection() != null) {
            throw new IllegalStateException("Cannot delete BillClient with id: " + id + " because it is linked to a Collection.");
        }

        billClientRepository.deleteById(id);
    }

}
