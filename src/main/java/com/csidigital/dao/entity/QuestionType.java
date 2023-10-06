package com.csidigital.dao.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String questionTypeName ;

    @JsonIgnore
    @OneToMany(mappedBy = "questionType" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionCategory> questionCategories;

    @JsonIgnore
    @ManyToMany(mappedBy = "questionTypeList")
    private List<Interview> interviewList;


}
