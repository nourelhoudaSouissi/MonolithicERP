package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.InterviewService;
import com.csidigital.shared.dto.request.InterviewRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@AllArgsConstructor

public class InterviewImpl implements InterviewService {
    @Autowired
    private InterviewRepository interviewRepository ;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;


    @Autowired
    private UpdatedQuestionRepository updatedQuestionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InterviewResponse createInterview(InterviewRequest request) {
        List<QuestionType> questionTypes = null;
        Evaluation evaluation = evaluationRepository.findById(request.getEvaluationNum()).orElseThrow();

        if (request.getQuestionTypeIds() != null) {
            questionTypes = questionTypeRepository.findAllById(request.getQuestionTypeIds());
        }

        Interview interview = modelMapper.map(request, Interview.class);
        interview.setEvaluation(evaluation);
        interview.setQuestionTypeList(questionTypes);
        Interview interviewSaved = interviewRepository.save(interview);

        // Add questions to the interview based on the question types and categories
        if (request.getQuestionTypeIds() != null){
            for (QuestionType questionType : questionTypes) {
                List<QuestionCategory> questionCategory = questionType.getQuestionCategories();
                List<Question> questions = new ArrayList<>();

                for(QuestionCategory q : questionCategory)
                    questions=q.getQuestions();
                for (Question question : questions) {
                    UpdatedQuestion updatedQuestion = new UpdatedQuestion();

                    updatedQuestion.setInterview(interviewSaved);
                    updatedQuestion.setQuestionText(question.getQuestion());



                    interviewSaved.getUpdatedQuestions().add(updatedQuestion);
                }

            }}

        interviewRepository.save(interviewSaved);

        return modelMapper.map(interviewSaved, InterviewResponse.class);
    }

    @Override
    public List<InterviewResponse> getAllInterview() {
        List<Interview> interview = interviewRepository.findAll();
        List<InterviewResponse> interviewList = new ArrayList<>();


        for (Interview interviews: interview) {
            InterviewResponse response = modelMapper.map(interviews, InterviewResponse.class);
            interviewList.add(response);
        }

        return interviewList;
    }

    @Override
    public InterviewResponse getInterviewById(Long id) {
        Interview interview =interviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Interview with id " +id+ " not found"));
        InterviewResponse interviewResponse = modelMapper.map(interview, InterviewResponse.class);
        return interviewResponse;
    }

    @Override
    public List<QuestionTypeResponse> getQuestionTypesbyInterview(Long id) {
        Interview interview =interviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("interview with id"+id+"not found"));
        List<QuestionType> questionTypes=  interview.getQuestionTypeList();
        List<QuestionTypeResponse>questionTypeList=new ArrayList<>();
        for (QuestionType questionType : questionTypes){
            QuestionTypeResponse response=modelMapper.map(questionType,QuestionTypeResponse.class);
            questionTypeList.add(response);
        }
        return questionTypeList ;
    }

    @Override
    public List<UpdatedQuestionResponse> getUpdatedQuestionsInterview(Long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("interview with id "+id+" not found"));
        List<UpdatedQuestion> updatedQuestions=interview.getUpdatedQuestions();
        List<UpdatedQuestionResponse> updatedQuestionResponseList = new ArrayList<>();
        for (UpdatedQuestion updatedQuestion : updatedQuestions){
            UpdatedQuestionResponse response =modelMapper.map(updatedQuestion,UpdatedQuestionResponse.class);
            updatedQuestionResponseList.add(response);
        }
        return updatedQuestionResponseList ;
    }

    @Override
    public InterviewResponse updateInterview(InterviewRequest request, Long id) {
        Interview existingInterview = interviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Interview with id: " + id + " not found"));
        modelMapper.map(request, existingInterview);
        Interview savedInterview = interviewRepository.save(existingInterview);
        return modelMapper.map(savedInterview, InterviewResponse.class);
    }



    @Override
    public void addQuestionTypeToInterview(Long id, List<Long> questionTypeIds) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Interview not found"));
        List<QuestionType> questionTypes = questionTypeRepository.findAllById(questionTypeIds);
        for(QuestionType res : questionTypes){
            res.getInterviewList().add(interview);
            interview.getQuestionTypeList().add(res);
        }
        // Add the resource to the project's resource list


        // Save the updated project
        interviewRepository.save(interview);
    }






    @Override
    public void deleteInterview(Long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public void updateStatusToPlannedById(Long id) {

    }

    @Override
    public void updateStatusToEndedById(Long id) {

    }

    @Override
    public void updateStatusToCancelledById(Long id) {

    }

    @Override
    public Double CalculateInterviewMark(Long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interview not found"));

        double totalMarks = 0;
        int questionCount = 0;

            List<UpdatedQuestion> updatedQuestions = interview.getUpdatedQuestions();
            for (UpdatedQuestion question : updatedQuestions) {
                if ( question.getMark() != null){
                    totalMarks += question.getMark();
                    questionCount++;
                }
            }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US); // Use US locale for consistent decimal format
        symbols.setDecimalSeparator('.'); // Set decimal separator to a period

        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols); // Create an instance of DecimalFormat

        double finalInterviewMark = ((totalMarks / questionCount) * interview.getCoefficient());
        String formattedFinalMark = decimalFormat.format(finalInterviewMark); // Format the value
        interview.setInterviewMark(Double.parseDouble(formattedFinalMark));
        return interview.getInterviewMark();
    }


}