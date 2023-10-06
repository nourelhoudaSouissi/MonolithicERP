package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.QuestionCategoryService;
import com.csidigital.shared.dto.request.QuestionCategoryRequest;
import com.csidigital.shared.dto.response.*;
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


public class QuestionCategoryImpl implements QuestionCategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;


    public QuestionCategoryResponse createQuestionCategory(QuestionCategoryRequest request) {
        QuestionType questionType= questionTypeRepository.findById(request.getQuestionTypeNum()).orElseThrow();
        QuestionCategory questionCategory = modelMapper.map(request, QuestionCategory.class);
        questionCategory.setQuestionType(questionType);
        QuestionCategory questionCategorySaved = questionCategoryRepository.save(questionCategory);
        return modelMapper.map(questionCategorySaved, QuestionCategoryResponse.class);
    }

    @Override
    public List<QuestionCategoryResponse> getAllQuestionCategories() {
        List<QuestionCategory> questionCategories = questionCategoryRepository.findAll();
        List<QuestionCategoryResponse> questionCategoryResponseList = new ArrayList<>();

        for (QuestionCategory questionCategory : questionCategories) {
            QuestionCategoryResponse response = modelMapper.map(questionCategory, QuestionCategoryResponse.class);
            questionCategoryResponseList.add(response);
        }

        return questionCategoryResponseList;
    }


    @Override
    public QuestionCategoryResponse getQuestionCategoryById(Long id) {
        QuestionCategory questionCategory = questionCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("questionCategory with id " + id + " not found"));
        QuestionCategoryResponse  questionCategoryResponse = modelMapper.map(questionCategory, QuestionCategoryResponse.class);
        return questionCategoryResponse;
    }

    @Override
    public List<QuestionResponse> getCategoryQuestions(Long id) {
        QuestionCategory questionCategory = questionCategoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category with id " +id+ " not found"));
        //TechnicalFile technicalFile = employee.getTechnicalFile();
        List<Question> questions = questionCategory.getQuestions();
        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question question : questions) {
            QuestionResponse response = modelMapper.map(question, QuestionResponse.class);
            questionResponseList.add(response);
        }
        return questionResponseList ;
    }


   /* @Override
    public QuestionCategoryResponse getQuestionCategoryByQuestionTypeId(Long id) {
        QuestionType questionType = questionTypeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(("QT with id "+id+" not found")));
        QuestionCategory questionCategory = questionType.getQuestionCategory();
       return modelMapper.map(questionCategory, QuestionCategoryResponse.class);
    }*/
    @Override
    public QuestionCategoryResponse updateQuestionCategory(QuestionCategoryRequest request, Long id) {
        QuestionCategory existingQuestionCategory = questionCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory with id: " + id + " not found"));
        modelMapper.map(request, existingQuestionCategory);
        QuestionCategory savedQuestionCategory = questionCategoryRepository.save(existingQuestionCategory);
        return modelMapper.map(savedQuestionCategory, QuestionCategoryResponse .class);    }

    @Override
    public void deleteQuestionCategory(Long id) {questionCategoryRepository.deleteById(id);

    }
}
