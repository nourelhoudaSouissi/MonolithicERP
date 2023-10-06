package com.csidigital.management.controller;


import com.csidigital.management.service.impl.UpdatedQuestionImpl;
import com.csidigital.shared.dto.request.UpdatedQuestionRequest;
import com.csidigital.shared.dto.response.UpdatedQuestionResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/rh/updatedQuestion")
public class UpdatedQuestionController {

    @Autowired
    private UpdatedQuestionImpl updatedQuestion ;
    @GetMapping("/getAll")
    public List<UpdatedQuestionResponse> getAllUpdatedQuestion(){
        return updatedQuestion.getAllUpdatedQuestion();
    }
    @GetMapping("/getBy/{id}")
    public UpdatedQuestionResponse getUpdatedQuestionById(@PathVariable Long id){
        return updatedQuestion.getUpdatedQuestionById(id);
    }

    @PostMapping("/add")
    public UpdatedQuestionResponse createUpdatedQuestion(@Valid @RequestBody UpdatedQuestionRequest  updatedQuestionRequest){
        return updatedQuestion.createUpdatedQuestion(updatedQuestionRequest);
    }
    @PutMapping("/update/{id}")
    public UpdatedQuestionResponse updateUpdatedQuestion(@Valid @RequestBody UpdatedQuestionRequest updatedQuestionRequest,
                                                         @PathVariable Long id){
        return updatedQuestion.updateUpdatedQuestion(updatedQuestionRequest,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUpdatedQuestion(@PathVariable Long id){
        updatedQuestion.deleteUpdatedQuestion(id);
    }

}
