package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.*;
import com.csidigital.shared.enumeration.*;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class InterviewResponse {


    private Long Id;
    private LocalDate interviewDate;
    private LocalTime interviewTime;
    private String duration;
    private String comment;
    private Integer globalMark;
    private String interviewerName;
    private String interviewerEmail;
    private String interviewerPhoneNumber;
    private String interviewPlace;
    private InterviewLocation interviewLocation ;
    private InterviewType interviewType;
    private InterviewMode interviewMode;
    private List<AssQuestionInterview> assQuestionInterviewList;
    private List<QuestionType> questionTypeList;
    private Evaluation evaluation;
    private Double coefficient;
    private Double interviewMark;

    private List<UpdatedQuestion> updatedQuestions;
}