package com.csidigital.management.controller;
import com.csidigital.management.service.impl.QuestionTypeImpl;
import com.csidigital.shared.dto.request.QuestionTypeRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.enumeration.ExperienceLevel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/QuestionType")
public class QuestionTypeController {
    @Autowired
    private QuestionTypeImpl questionTypeImpl;

    @GetMapping("/getAll")
    public List<QuestionTypeResponse> getAllQuestionTypes() {
        return questionTypeImpl.getAllQuestionTypes();
    }

    @GetMapping("/getBy/{id}")
    public QuestionTypeResponse getQuestionTypeById(@PathVariable Long id){
        return questionTypeImpl.getQuestionTypeById(id);
    }

    @GetMapping("get/{id}/questionCategories")
         public List<QuestionCategoryResponse> getQuestionCategoryByType(@PathVariable Long id){
        return questionTypeImpl.getQuestionCategoryByType(id);
    }
    @GetMapping("get/{id}/{Id}/questions")
    public List<QuestionResponse> getQuestionsByCategoryAndType(@PathVariable Long id ,@PathVariable Long Id){
        return questionTypeImpl.getQuestionsByCategoryAndType(id,Id);
    }
@GetMapping("get/{id}/{Id}/{level}/questions")
public List<QuestionResponse> getQuestionByTypeCategoryAndLevel(@PathVariable Long id , @PathVariable Long Id, @PathVariable ExperienceLevel level){
        return questionTypeImpl.getQuestionByTypeCategoryAndLevel(id, Id, level);
}
    @PostMapping("/add")
    public QuestionTypeResponse createQuestionType(@Valid @RequestBody QuestionTypeRequest questionTypeRequest){
        return questionTypeImpl.createQuestionType(questionTypeRequest);
    }

    @PutMapping("/update/{id}")
    public QuestionTypeResponse updateQuestionType(@Valid @RequestBody QuestionTypeRequest questionTypeRequest,
                                            @PathVariable Long id){
        return questionTypeImpl.updateQuestionType(questionTypeRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestionType(@PathVariable Long id){
        questionTypeImpl.deleteQuestionType(id);
    }

}
