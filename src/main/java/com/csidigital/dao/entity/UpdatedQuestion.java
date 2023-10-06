package com.csidigital.dao.entity;


import com.csidigital.shared.enumeration.InterviewType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdatedQuestion {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer mark;
    private String comment;
    private String questionText;


    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;

    @ManyToOne()
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "interviewId")
    private Interview interview ;
}