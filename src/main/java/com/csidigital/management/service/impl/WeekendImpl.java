package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Weekend;
import com.csidigital.dao.repository.WeekendRepository;
import com.csidigital.management.service.WeekendService;
import com.csidigital.shared.dto.request.WeekendRequest;
import com.csidigital.shared.dto.response.WeekendResponse;
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
public class WeekendImpl implements WeekendService {
    @Autowired
    private WeekendRepository weekendRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public WeekendResponse createWeekend(WeekendRequest request) {
        Weekend weekend = modelMapper.map(request, Weekend.class);
        Weekend savedWeekend = weekendRepository.save(weekend);
        return modelMapper.map(savedWeekend, WeekendResponse.class);
    }

    @Override
    public List<WeekendResponse> getAllWeekend() {
        List<Weekend> weekends = weekendRepository.findAll();
        List<WeekendResponse> weekendResponses = new ArrayList<>();


        for (Weekend weekend: weekends) {
            WeekendResponse response = modelMapper.map(weekend, WeekendResponse.class);
            weekendResponses.add(response);
        }

        return weekendResponses;
    }

    @Override
    public WeekendResponse getWeekendById(Long id) {
        Weekend weekend = weekendRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Weekend with id " +id+ " not found"));
        WeekendResponse weekendResponse = modelMapper.map(weekend, WeekendResponse.class);
        return weekendResponse;
    }

    @Override
    public WeekendResponse updateWeekend(WeekendRequest request, Long id) {
        Weekend existingWeekend = weekendRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Weekend with id: " + id + " not found"));
        modelMapper.map(request, existingWeekend);
        Weekend savedWeekend = weekendRepository.save(existingWeekend);
        return modelMapper.map(savedWeekend, WeekendResponse.class);
    }

    @Override
    public void deleteWeekend(Long id) {
        weekendRepository.deleteById(id);

    }
}
