package com.csidigital.shared.dto.request;

import lombok.Data;

@Data
public class AssQuestionInterviewRequest {
    private Integer mark;
    private Long questionNum;
    private Long interviewNum;
}
