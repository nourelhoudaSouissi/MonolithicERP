package com.csidigital.shared.dto.response;

import com.csidigital.dao.entity.*;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;
@Data
public class QuestionCategoryResponse {
    private Long id ;
    private String name ;
    private List<Question> questions;
    //private List<QuestionType> questionTypeList;
    private Long questionTypeNum;
    @Enumerated(EnumType.STRING)
    private ExperienceLevel level ;
    @Enumerated(EnumType.STRING)
    private QuestionnaireType questionnaireType ;

}
