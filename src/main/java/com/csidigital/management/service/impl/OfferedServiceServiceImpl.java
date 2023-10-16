package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.OfferedService;
import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.repository.OfferedServiceRepository;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.management.service.OfferedServiceService;
import com.csidigital.shared.dto.request.OfferedServiceRequest;
import com.csidigital.shared.dto.response.OfferedServiceResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OfferedServiceServiceImpl implements OfferedServiceService {
    @Autowired
    private OfferedServiceRepository offeredServiceRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartnerRepository partnerRepository;
    @Override
    public OfferedServiceResponse createOfferedService(OfferedServiceRequest request) {
        Partner partner = partnerRepository.findById(request.getPartnerNum())
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found"));
        OfferedService offeredService = modelMapper.map(request, OfferedService.class);
        offeredService.setPartner(partner);
        OfferedService offeredServiceSaved = offeredServiceRepository.save(offeredService);
        return modelMapper.map(offeredServiceSaved, OfferedServiceResponse.class);
    }

    @Override
    public List<OfferedServiceResponse> getAllOfferedServices() {
        List<OfferedService> offeredServices = offeredServiceRepository.findAll();
        List<OfferedServiceResponse> offeredServiceList = new ArrayList<>();

        for (OfferedService offeredService : offeredServices) {
            OfferedServiceResponse response = modelMapper.map(offeredService, OfferedServiceResponse.class);
            offeredServiceList.add(response);
        }

        return offeredServiceList;
    }

    @Override
    public OfferedServiceResponse getOfferedServiceById(Long id) {
        OfferedService offeredService = offeredServiceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("OfferedService with id " +id+ " not found"));
        OfferedServiceResponse offeredServiceResponse = modelMapper.map(offeredService, OfferedServiceResponse.class);
        return offeredServiceResponse;
    }

    @Override
    public OfferedServiceResponse updateOfferedService(OfferedServiceRequest request, Long id) {
        OfferedService existingOfferedService = offeredServiceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("OfferedService with id: " + id + " not found"));
        modelMapper.map(request, existingOfferedService);
        OfferedService savedOfferedService = offeredServiceRepository.save(existingOfferedService);
        return modelMapper.map(savedOfferedService, OfferedServiceResponse.class);
    }

    @Override
    public void deleteOfferedService(Long id) {
        offeredServiceRepository.deleteById(id);
    }
}


