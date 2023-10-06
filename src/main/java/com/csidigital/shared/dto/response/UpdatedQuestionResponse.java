package com.csidigital.shared.dto.response;

import lombok.Data;

@Data
public class UpdatedQuestionResponse {
    private Long Id;
    private Integer mark;
    private String comment;
    private String questionText;
    private Long interviewNum;
}
