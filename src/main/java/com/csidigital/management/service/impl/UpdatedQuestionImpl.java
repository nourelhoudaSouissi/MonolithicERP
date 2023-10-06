package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.Interview;
import com.csidigital.dao.entity.UpdatedQuestion;
import com.csidigital.dao.repository.InterviewRepository;
import com.csidigital.dao.repository.UpdatedQuestionRepository;
import com.csidigital.management.service.UpdatedQuestionService;
import com.csidigital.shared.dto.request.UpdatedQuestionRequest;
import com.csidigital.shared.dto.response.UpdatedQuestionResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@Service
@Transactional
public class UpdatedQuestionImpl implements UpdatedQuestionService {

    @Autowired
    private UpdatedQuestionRepository updatedQuestionRepository ;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UpdatedQuestionResponse createUpdatedQuestion(UpdatedQuestionRequest request) {
        Interview interview= interviewRepository.findById(request.getInterviewNum()).orElseThrow() ;
        UpdatedQuestion updatedQuestion = modelMapper.map(request,  UpdatedQuestion.class);
        updatedQuestion.setInterview(interview);
        UpdatedQuestion  UpdatedQuestionSaved =  updatedQuestionRepository.save(updatedQuestion);
        return modelMapper.map( UpdatedQuestionSaved,  UpdatedQuestionResponse.class);
    }

    @Override
    public List< UpdatedQuestionResponse> getAllUpdatedQuestion() {
        List< UpdatedQuestion> updatedQuestion =  updatedQuestionRepository.findAll();
        List< UpdatedQuestionResponse> updatedQuestionList = new ArrayList<>();


        for ( UpdatedQuestion updatedQuestion1:  updatedQuestion) {
            UpdatedQuestionResponse response = modelMapper.map( updatedQuestion1,  UpdatedQuestionResponse.class);
            updatedQuestionList.add(response);
        }

        return updatedQuestionList;
    }

    @Override
    public  UpdatedQuestionResponse getUpdatedQuestionById(Long id) {
        UpdatedQuestion updatedQuestion = updatedQuestionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(" UpdatedQuestion with id " +id+ " not found"));
        UpdatedQuestionResponse updatedQuestionResponse = modelMapper.map( updatedQuestion,  UpdatedQuestionResponse.class);
        return  updatedQuestionResponse;
    }

    /*@Override
    public List<UpdatedQuestion> getQuestionsByUpdatedQuestions(Long id) {
        UpdatedQuestion updatedQuestion =updatedQuestionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("UpdatedQuestion with id "+id+"not found"));

        return updatedQuestion;
    }*/

    @Override
    public  UpdatedQuestionResponse updateUpdatedQuestion( UpdatedQuestionRequest request, Long id) {
        UpdatedQuestion existingUpdatedQuestion =  updatedQuestionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(" UpdatedQuestion with id: " + id + " not found"));
        modelMapper.map(request, existingUpdatedQuestion);
        UpdatedQuestion savedUpdatedQuestion =  updatedQuestionRepository.save(existingUpdatedQuestion);
        return modelMapper.map(savedUpdatedQuestion,  UpdatedQuestionResponse.class);
    }

    @Override
    public void deleteUpdatedQuestion(Long id) {
        updatedQuestionRepository.deleteById(id);
    }
}
