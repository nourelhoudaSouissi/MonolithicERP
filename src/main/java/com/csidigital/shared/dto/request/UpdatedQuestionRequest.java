package com.csidigital.shared.dto.request;

import lombok.Data;

@Data
public class UpdatedQuestionRequest {
    private Integer mark;
    private String comment;
    private String questionText;
    private Long interviewNum ;
}
