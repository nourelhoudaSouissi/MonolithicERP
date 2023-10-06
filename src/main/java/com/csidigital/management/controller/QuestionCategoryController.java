package com.csidigital.management.controller;

import com.csidigital.management.service.impl.QuestionCategoryImpl;
import com.csidigital.shared.dto.request.QuestionCategoryRequest;
import com.csidigital.shared.dto.response.QuestionCategoryResponse;
import com.csidigital.shared.dto.response.QuestionResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/rh/questionCategory")
public class QuestionCategoryController {
    @Autowired
    private QuestionCategoryImpl questionCategory ;
    @GetMapping("/getAll")
    public List<QuestionCategoryResponse> getAllQuestionCategories(){
        return questionCategory.getAllQuestionCategories();
    }
    @GetMapping("/getBy/{id}")
    public QuestionCategoryResponse getQuestionCategoryById(@PathVariable Long id){
        return questionCategory.getQuestionCategoryById(id);
    }
    @GetMapping("get/{id}/questions")
    public List<QuestionResponse> getCategoryQuestions(@PathVariable Long id){
        return questionCategory.getCategoryQuestions(id);
    }

    /*@GetMapping("/{id}/questionType")
    public QuestionCategoryResponse getByQuestionTypeId(@PathVariable Long id){
        return questionCategory.getQuestionCategoryByQuestionTypeId(id);
    }*/


    @PostMapping("/add")
    public QuestionCategoryResponse createQuestionCategory(@Valid @RequestBody QuestionCategoryRequest questionCategoryRequest){
        return questionCategory.createQuestionCategory(questionCategoryRequest);
    }
    @PutMapping("/update/{id}")
    public QuestionCategoryResponse updateQuestionCategory(@Valid @RequestBody QuestionCategoryRequest questionCategoryRequest,
                                            @PathVariable Long id){
        return questionCategory.updateQuestionCategory(questionCategoryRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestionCategory(@PathVariable Long id){
        questionCategory.deleteQuestionCategory(id);
    }



}
