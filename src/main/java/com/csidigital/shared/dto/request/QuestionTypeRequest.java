package com.csidigital.shared.dto.request;
import com.csidigital.dao.entity.Interview;
import com.csidigital.dao.entity.QuestionCategory;
import lombok.Data;

import java.util.List;

@Data
public class QuestionTypeRequest {

    private String questionTypeName;
    private List<QuestionCategory> questionCategories;
    private List<Interview> interviewList;
}
