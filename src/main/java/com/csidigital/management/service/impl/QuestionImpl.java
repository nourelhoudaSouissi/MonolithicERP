package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.QuestionService;
import com.csidigital.shared.dto.request.QuestionRequest;
import com.csidigital.shared.dto.response.QuestionResponse;
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

public class QuestionImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository ;
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionResponse createQuestion(QuestionRequest request) {
        QuestionCategory questionCategory= questionCategoryRepository.findById(request.getCategoryNum()).orElseThrow();
        Question question = modelMapper.map(request, Question.class);
        question.setQuestionCategory(questionCategory);
        Question QuestionSaved = questionRepository.save(question);
        return modelMapper.map(QuestionSaved, QuestionResponse.class);
    }

    @Override
    public List<QuestionResponse> getAllQuestion() {
        List<Question> question = questionRepository.findAll();
        List<QuestionResponse> questionList = new ArrayList<>();


        for (Question questions: question) {
            QuestionResponse response = modelMapper.map(questions, QuestionResponse.class);
            questionList.add(response);
        }

        return questionList;
    }

    @Override
    public QuestionResponse getQuestionById(Long id) {
        Question question =questionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Question with id " +id+ " not found"));
        QuestionResponse questionResponse = modelMapper.map(question, QuestionResponse.class);
        return questionResponse;
    }

    @Override
    public QuestionResponse updateQuestion(QuestionRequest request, Long id) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Question with id: " + id + " not found"));
        modelMapper.map(request, existingQuestion);
        Question savedQuestion = questionRepository.save(existingQuestion);
        return modelMapper.map(savedQuestion, QuestionResponse.class);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
