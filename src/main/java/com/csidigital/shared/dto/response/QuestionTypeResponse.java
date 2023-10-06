package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.List;


@Data
public class QuestionTypeResponse {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private List<QuestionCategory> questionCategories;
    private String questionTypeName;
    private List<Interview> interviewList;
}
