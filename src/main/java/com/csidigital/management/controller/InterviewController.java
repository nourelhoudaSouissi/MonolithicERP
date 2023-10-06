package com.csidigital.management.controller;

import com.csidigital.management.service.impl.InterviewImpl;
import com.csidigital.shared.dto.request.InterviewRequest;
import com.csidigital.shared.dto.response.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("rh/Interview")

public class InterviewController {
    @Autowired
    private InterviewImpl interviewImpl ;

    @GetMapping("getAll")
    public List<InterviewResponse> getAllInterview() {
        return interviewImpl.getAllInterview();
    }

    @GetMapping("/getBy/{id}")
    public InterviewResponse getInterviewById(@PathVariable Long id){
        return interviewImpl.getInterviewById(id);
    }
    @GetMapping("/get/{id}/questionType")
    public List<QuestionTypeResponse> getQuestionTypesbyInterview(@PathVariable Long id){
        return  interviewImpl.getQuestionTypesbyInterview(id);
    }
    @GetMapping("/get/{id}/updatedQuestion")
    public List<UpdatedQuestionResponse> getUpdatedQuestionsInterview(@PathVariable Long id){
        return interviewImpl.getUpdatedQuestionsInterview(id);
    }
    @PutMapping("addQuestionType/{id}")
    public void addQuestionTypeToInterview(@PathVariable Long id, @RequestBody List<Long> questionTypeIds) {
        interviewImpl.addQuestionTypeToInterview(id,questionTypeIds);
    }

    @PostMapping("/add")
    public InterviewResponse createInterview(@Valid @RequestBody InterviewRequest interviewRequest){
        return interviewImpl.createInterview(interviewRequest);
    }

    @PutMapping("/update/{id}")
    public InterviewResponse updateInterview(@Valid @RequestBody InterviewRequest interviewRequest,
                                             @PathVariable Long id){
        return interviewImpl.updateInterview(interviewRequest, id); }

    @DeleteMapping("/delete/{id}")
    public void deleteInterview(@PathVariable Long id){
        interviewImpl.deleteInterview(id);
    }

    @PutMapping("/updateStatusToPlannedById/{id}")
    void updateStatusToPlannedById(@PathVariable Long id) {
        interviewImpl.updateStatusToPlannedById(id);
    }

    @PutMapping("/updateStatusToEndedById/{id}")
    void updateStatusToEndedById(@PathVariable Long id) {
        interviewImpl.updateStatusToEndedById(id);
    }

    @PutMapping("/updateStatusToCancelledById/{id}")
    void updateStatusToCancelledById(@PathVariable Long id) {
        interviewImpl.updateStatusToCancelledById(id);
    }
    @GetMapping("/getInterviewMark/{id}")
    public Double getInterviewMark(@PathVariable Long id){
       return   interviewImpl.CalculateInterviewMark(id);
    }
}