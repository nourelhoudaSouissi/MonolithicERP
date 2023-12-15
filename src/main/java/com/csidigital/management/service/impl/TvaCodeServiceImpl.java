package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.PaymentTerm;
import com.csidigital.dao.entity.TvaCode;
import com.csidigital.dao.repository.TvaCodeRepository;
import com.csidigital.management.service.TvaCodeService;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.PaymentTermResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TvaCodeServiceImpl implements TvaCodeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TvaCodeRepository tvaCodeRepository;
    @Override
    public TvaCodeResponse createTvaCode(TvaCodeRequest request) {
        TvaCode tvaCode = modelMapper.map(request, TvaCode.class);
        TvaCode savedTvaCode= tvaCodeRepository.save(tvaCode);
        return modelMapper.map(savedTvaCode, TvaCodeResponse.class);
    }

    @Override
    public List<TvaCodeResponse> getAllTvaCodes() {
        List<TvaCode> tvaCodes = tvaCodeRepository.findAll();
        List<TvaCodeResponse> tvaCodeResponses = new ArrayList<>();


        for (TvaCode tvaCode: tvaCodes) {
            TvaCodeResponse response = modelMapper.map(tvaCode, TvaCodeResponse.class);
            tvaCodeResponses.add(response);
        }

        return tvaCodeResponses;
    }

    @Override
    public TvaCodeResponse getTvaCodeById(Long id) {
        TvaCode tvaCode = tvaCodeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TvaCode with id " +id+ " not found"));
        TvaCodeResponse tvaCodeResponse = modelMapper.map(tvaCode, TvaCodeResponse.class);
        return tvaCodeResponse;
    }

    @Override
    public TvaCodeResponse updateTvaCode(TvaCodeRequest request, Long id) {
        TvaCode existingTvaCode = tvaCodeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TvaCode with id: " + id + " not found"));
        modelMapper.map(request, existingTvaCode);
        TvaCode savedTvaCode = tvaCodeRepository.save(existingTvaCode);
        return modelMapper.map(savedTvaCode, TvaCodeResponse.class);    }

    @Override
    public void deleteTvaCode(Long id) {
        tvaCodeRepository.deleteById(id);
    }
}
