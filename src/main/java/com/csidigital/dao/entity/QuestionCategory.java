package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;

    @JsonIgnore
    @OneToMany(mappedBy = "questionCategory" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

     @ManyToOne()
     @JsonIgnore
     @JoinColumn(name = "categoryId")
     private QuestionType questionType;


    @Enumerated(EnumType.STRING)
    private ExperienceLevel level ;

    @Enumerated(EnumType.STRING)
    private QuestionnaireType questionnaireType ;
}
