package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.BillRepository;
import com.csidigital.management.service.BillService;
import com.csidigital.shared.dto.request.BillDtoRequest;
import com.csidigital.shared.dto.response.BillDtoResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BillDtoResponse> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        List<BillDtoResponse> billList = new ArrayList<>();

        for (Bill bill : bills) {
            BillDtoResponse billResponse = modelMapper.map(bill, BillDtoResponse.class);
            billList.add(billResponse);
        }

        return billList;
    }

    @Override
    public BillDtoResponse getBillById(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id " +id+ " not found"));
        BillDtoResponse billDtoResponse = modelMapper.map(bill, BillDtoResponse.class);
        return billDtoResponse;
    }

    @Override
    public BillDtoResponse CreateBill(BillDtoRequest billDtoRequest) {
        Bill bill = modelMapper.map(billDtoRequest, Bill.class);

        // set the Bill object to each AdditionalFee object
        for(AdditionalFee additionalFee : bill.getAdditionalFees()) {
            additionalFee.setBill(bill);
        }

        Bill BillSaved = billRepository.save(bill);
        return modelMapper.map(BillSaved, BillDtoResponse.class);
    }


    @Override
    public BillDtoResponse updateBill(BillDtoRequest billDtoRequest, Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bill with id: " + id + " not found"));

        // Vérifier si l'objet Bill est lié à un objet Disbursement
        if (bill.getDisbursement() != null) {
            throw new IllegalStateException("Cannot update a Bill that is linked to a Collection");
        }

        // Supprimer toutes les additionalFees existantes liées au Bill
        List<AdditionalFee> additionalFeesToDelete = bill.getAdditionalFees();
        for (AdditionalFee additionalFee : additionalFeesToDelete) {
            additionalFee.setBill(null);
        }

        // Mettre à jour les valeurs du Bill avec les nouvelles valeurs
        modelMapper.map(billDtoRequest, bill);

        // Ajouter les nouvelles additionalFees avec le Bill lié (en OneToMany)
        List<AdditionalFee> newAdditionalFees = new ArrayList<>();
        for (AdditionalFee additionalFeeDtoRequest : billDtoRequest.getAdditionalFees()) {
            AdditionalFee additionalFee = modelMapper.map(additionalFeeDtoRequest, AdditionalFee.class);
            additionalFee.setBill(bill);
            newAdditionalFees.add(additionalFee);
        }
        bill.setAdditionalFees(newAdditionalFees);

        // Enregistrer le Bill mis à jour avec les nouvelles additionalFees
        Bill updatedBill = billRepository.save(bill);

        return modelMapper.map(updatedBill, BillDtoResponse.class);
    }


    @Override
    public void deleteBillById(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill with id: " + id + " not found"));

        if (bill.getDisbursement() != null) {
            throw new IllegalStateException("Cannot delete Bill with id: " + id + " because it is linked to a Collection.");
        }

        billRepository.deleteById(id);
    }


}
