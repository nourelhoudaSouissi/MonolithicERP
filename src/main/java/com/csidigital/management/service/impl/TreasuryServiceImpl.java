package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Treasury;
import com.csidigital.dao.repository.*;
import com.csidigital.exception.ResourceNotFoundException;
import com.csidigital.management.service.TreasuryService;
import com.csidigital.shared.dto.request.TreasuryDtoRequest;
import com.csidigital.shared.dto.response.TreasuryDtoResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TreasuryServiceImpl implements TreasuryService {


    @Autowired
    private TreasuryRepository treasuryRepository;
    @Autowired
    private BankReconciliationRepository bankReconciliationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TreasuryDtoResponse> getAllTreasury() {
        List<Treasury> treasuries = treasuryRepository.findAll();
        List<TreasuryDtoResponse> treasuryList = new ArrayList<>();

        for (Treasury treasury : treasuries) {
            TreasuryDtoResponse treasuryDtoResponse = modelMapper.map(treasury, TreasuryDtoResponse.class);
            treasuryList.add(treasuryDtoResponse);
        }

        return treasuryList;    }

    @Override
    public TreasuryDtoResponse getTreasuryById(Long id) {
        Treasury treasury = treasuryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Treasury with id " +id+ " not found"));
        TreasuryDtoResponse treasuryDtoResponse = modelMapper.map(treasury, TreasuryDtoResponse.class);
        return treasuryDtoResponse;    }

    @Override
    public TreasuryDtoResponse CreateTreasury(TreasuryDtoRequest treasuryDtoRequest) {
        Treasury treasury = modelMapper.map(treasuryDtoRequest, Treasury.class);



        Treasury treasurySaved = treasuryRepository.save(treasury);
        return modelMapper.map(treasurySaved, TreasuryDtoResponse.class);
    }

    @Override
    public TreasuryDtoResponse updateTreasury(TreasuryDtoRequest treasuryDtoRequest, Long id) {
        Treasury treasury = treasuryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Treasury with id: " + id + " not found"));
        modelMapper.map(treasuryDtoRequest, treasury);
        Treasury updatedTreasury = treasuryRepository.save(treasury);
        return modelMapper.map(updatedTreasury, TreasuryDtoResponse.class);    }

    @Override
    public void deleteTreasuryById(Long id) {
        treasuryRepository.deleteById(id);

    }

}
