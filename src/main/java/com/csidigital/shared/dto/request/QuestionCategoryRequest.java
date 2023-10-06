package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Question;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;
@Data
public class QuestionCategoryRequest {

    private String name ;
    private List<Question> questions;
    private Long questionTypeNum;
    //private List<QuestionType> questionTypeList;
    @Enumerated(EnumType.STRING)
    private ExperienceLevel level ;
    @Enumerated(EnumType.STRING)
    private QuestionnaireType questionnaireType ;

}
