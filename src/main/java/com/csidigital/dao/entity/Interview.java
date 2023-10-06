package com.csidigital.dao.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Interview {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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
    @Enumerated(EnumType.STRING)
    private InterviewLocation interviewLocation ;
    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;
    @Enumerated(EnumType.STRING)
    private InterviewMode interviewMode;

    private Double coefficient;
    private Double interviewMark;

    @JsonIgnore
    @OneToMany(mappedBy = "interview")
    private List<AssQuestionInterview> assQuestionInterviewList;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "evaluationId")
    private Evaluation evaluation;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "interview_question_type",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "question_type_id"))
    private List<QuestionType> questionTypeList;
    @JsonIgnore
    @OneToMany(mappedBy = "interview")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private List<UpdatedQuestion> updatedQuestions;
}



