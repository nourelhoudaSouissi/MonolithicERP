package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.BankReconciliation;
import com.csidigital.dao.repository.*;

import com.csidigital.management.service.BankReconciliationService;
import com.csidigital.shared.dto.request.BankReconciliationDtoRequest;
import com.csidigital.shared.dto.response.BankReconciliationDtoResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class BankReconciliationServiceImpl implements BankReconciliationService {

    private final BankReconciliationRepository bankReconciliationRepository;
    private  TreasuryRepository treasuryRepository;
    private CollectionRepository collectionRepository;
    private DisbursementRepository disbursementRepository;

    private final ModelMapper modelMapper;

    public BankReconciliationServiceImpl(BankReconciliationRepository bankReconciliationRepository,
                                         TreasuryRepository treasuryRepository,
                                         CollectionRepository collectionRepository,
                                         DisbursementRepository disbursementRepository,
                                         ModelMapper modelMapper) {
        this.bankReconciliationRepository = bankReconciliationRepository;
        this.treasuryRepository = treasuryRepository;
        this.collectionRepository = collectionRepository;
        this.disbursementRepository = disbursementRepository;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public BankReconciliationServiceImpl(BankReconciliationRepository bankReconciliationRepository, ModelMapper modelMapper) {
        this.bankReconciliationRepository = bankReconciliationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BankReconciliationDtoResponse> getAllBankReconciliation() {
        List<BankReconciliation> bankReconciliations = bankReconciliationRepository.findAll();
        return bankReconciliations.stream()
                .map(bankReconciliation -> modelMapper.map(bankReconciliation, BankReconciliationDtoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public BankReconciliationDtoResponse getBankReconciliationById(Long id) {
        BankReconciliation bankReconciliation = bankReconciliationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankReconciliation with id " + id + " not found"));
        return modelMapper.map(bankReconciliation, BankReconciliationDtoResponse.class);
    }

    @Override
    public BankReconciliationDtoResponse createBankReconciliation(BankReconciliationDtoRequest bankReconciliationDtoRequest) {
        BankReconciliation bankReconciliation = modelMapper.map(bankReconciliationDtoRequest, BankReconciliation.class);

        BankReconciliation BankReconciliationSaved = bankReconciliationRepository.save(bankReconciliation);
        return modelMapper.map(BankReconciliationSaved, BankReconciliationDtoResponse.class);
    }



    @Override
    public BankReconciliationDtoResponse updateBankReconciliation(BankReconciliationDtoRequest bankReconciliationDtoRequest, Long id) {
        BankReconciliation bankReconciliation = bankReconciliationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BankReconciliation with id: " + id + " not found"));
        modelMapper.map(bankReconciliationDtoRequest, bankReconciliation);
        BankReconciliation updatedBankReconciliation = bankReconciliationRepository.save(bankReconciliation);
        return modelMapper.map(updatedBankReconciliation, BankReconciliationDtoResponse.class);
    }

    @Override
    public void deleteBankReconciliationById(Long id) {
        bankReconciliationRepository.deleteById(id);
    }
}
