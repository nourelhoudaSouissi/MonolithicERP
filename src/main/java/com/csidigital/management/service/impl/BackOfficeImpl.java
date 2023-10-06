package com.csidigital.management.service.impl;
import com.csidigital.dao.entity.BackOffice;
import com.csidigital.dao.repository.BackOfficeRepository;
import com.csidigital.management.service.BackOfficeService;
import com.csidigital.shared.dto.request.BackOfficeRequest;
import com.csidigital.shared.dto.response.BackOfficeResponse;
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
public class BackOfficeImpl implements BackOfficeService {
    @Autowired
    private BackOfficeRepository backOfficeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BackOfficeResponse createBackoffice(BackOfficeRequest request) {
        BackOffice backOffice = modelMapper.map(request, BackOffice.class);
        BackOffice backOfficeSaved = backOfficeRepository.save(backOffice);
        return modelMapper.map(backOfficeSaved, BackOfficeResponse.class);
    }

    @Override
    public List<BackOfficeResponse> getAllBackOffice() {
        List<BackOffice> backOffices = backOfficeRepository.findAll();
        List<BackOfficeResponse> backOfficeResponseList = new ArrayList<>();

        for (BackOffice backOffice : backOffices) {
            BackOfficeResponse response = modelMapper.map(backOffice ,BackOfficeResponse.class);
            backOfficeResponseList.add(response);
        }

        return backOfficeResponseList;
    }

    @Override
    public BackOfficeResponse getBackOfficeById(Long id) {
        BackOffice backOffice = backOfficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BackOffice with id " + id + " not found"));
        BackOfficeResponse backOfficeResponse = modelMapper.map(backOffice, BackOfficeResponse.class);
        return backOfficeResponse;
    }

    @Override
    public BackOfficeResponse updateBackOffice(BackOfficeRequest request, Long id) {
        BackOffice existingBackOffice = backOfficeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BackOffice with id: " + id + " not found"));
        modelMapper.map(request, existingBackOffice);
        BackOffice savedBackOffice = backOfficeRepository.save(existingBackOffice);
        return modelMapper.map(savedBackOffice, BackOfficeResponse.class);
    }

    @Override
    public void deleteBackOffice(Long id) {
        backOfficeRepository.deleteById(id);
    }


}

