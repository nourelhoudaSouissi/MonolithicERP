package com.csidigital.dao.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Skills {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    @Column(name = "skillsTitle")
    private String skillsTitle;


    @ManyToOne(  )
    @JsonIgnore
    @JoinColumn(name = "technicalFileId")
    private TechnicalFile technicalFile;


  /*  @ManyToOne
    @JoinColumn(name = "skillsCategoryId")
    private SkillsCategory skillsCategory;*/
}