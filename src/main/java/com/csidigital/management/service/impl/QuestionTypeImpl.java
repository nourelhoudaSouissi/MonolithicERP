package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.QuestionTypeService;
import com.csidigital.shared.dto.request.QuestionTypeRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.enumeration.ExperienceLevel;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class QuestionTypeImpl implements QuestionTypeService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository ;
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionTypeResponse createQuestionType(QuestionTypeRequest request) {
        //QuestionCategory questionCategory= questionCategoryRepository.findById(request.getQuestionCategoryNum()).orElseThrow();
        QuestionType questionType = modelMapper.map(request, QuestionType.class);
        //questionType.setQuestionCategory(questionCategory);
        QuestionType QuestionTypeSaved = questionTypeRepository.save(questionType);
        return modelMapper.map(QuestionTypeSaved, QuestionTypeResponse.class);
    }

    @Override
    public List<QuestionTypeResponse> getAllQuestionTypes() {
        List<QuestionType> questionType = questionTypeRepository.findAll();
        List<QuestionTypeResponse> questionTypeList = new ArrayList<>();


        for (QuestionType questionTypes: questionType) {
            QuestionTypeResponse response = modelMapper.map(questionTypes, QuestionTypeResponse.class);
            questionTypeList.add(response);}

        return questionTypeList;
    }

    @Override
    public QuestionTypeResponse getQuestionTypeById(Long id) {
        QuestionType questionType =questionTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("QuestionType with id " +id+ " not found"));
        QuestionTypeResponse questionTypeResponse = modelMapper.map(questionType, QuestionTypeResponse.class);
        return questionTypeResponse;
    }

    @Override
    public List<QuestionCategoryResponse> getQuestionCategoryByType(Long id) {
        QuestionType questionType = questionTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("QuestionType with id "+id+" not found"));
        List<QuestionCategory> questionCategories=  questionType.getQuestionCategories();
        List<QuestionCategoryResponse>questionCategoryResponseList=new ArrayList<>();
        for (QuestionCategory questionCategory : questionCategories){
            QuestionCategoryResponse response=modelMapper.map(questionCategory,QuestionCategoryResponse.class);
            questionCategoryResponseList.add(response);
        }
        return questionCategoryResponseList ;
    }

    public List<QuestionResponse> getQuestionsByCategoryAndType(Long questionTypeId, Long questionCategoryId) {
        QuestionType questionType = questionTypeRepository.findById(questionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionType with id " + questionTypeId + " not found"));

        QuestionCategory questionCategory = questionType.getQuestionCategories().stream()
                .filter(category -> category.getId().equals(questionCategoryId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory with id " + questionCategoryId + " not found"));

        List<Question> questions = questionCategory.getQuestions();

        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question question : questions) {
            QuestionResponse response = modelMapper.map(question, QuestionResponse.class);
            questionResponseList.add(response);
        }

        return questionResponseList;
    }

    @Override
    public List<QuestionResponse> getQuestionByTypeCategoryAndLevel(Long id, Long Id, ExperienceLevel level) {
        QuestionType questionType = questionTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionType with id " + id + " not found"));

        QuestionCategory questionCategory = questionType.getQuestionCategories().stream()
                .filter(category -> category.getId().equals(Id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory with id " + Id + " not found"));

        List<Question> questions = questionCategory.getQuestions();

        // Filter questions by level
        List<Question> filteredQuestions = questions.stream()
                .filter(question -> question.getQuestionCategory().getLevel() == level)
                .collect(Collectors.toList());

        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question question : filteredQuestions) {
            QuestionResponse response = modelMapper.map(question, QuestionResponse.class);
            questionResponseList.add(response);
        }

        return questionResponseList;
        }


    @Override
    public QuestionTypeResponse updateQuestionType(QuestionTypeRequest request, Long id) {
        QuestionType existingQuestionType = questionTypeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("QuestionType with id: " + id + " not found"));
        modelMapper.map(request, existingQuestionType);
        QuestionType savedQuestionType = questionTypeRepository.save(existingQuestionType);
        return modelMapper.map(savedQuestionType, QuestionTypeResponse.class);
    }

    @Override
    public void deleteQuestionType(Long id) {
        questionTypeRepository.deleteById(id);
    }
}
