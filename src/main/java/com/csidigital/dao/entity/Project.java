package com.csidigital.dao.entity;


import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String projectReference;
    private String name;
    private String description;
    private Double budget;
    private Integer workingHourNumber ;
    private LocalDate realStartDate ;
    private LocalDate realEndDate ;
    private String projectCategory ;
    private String lieu ;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)

    private ProjectType projectType;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    /*@JsonIgnore
    @ManyToMany(mappedBy = "project")
    List<Resource> resources ;*/

    @JsonIgnore
    @ManyToMany(mappedBy = "project")
    List<Employee> employees ;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Phase> phases;
    @JsonIgnore
    @OneToMany
    private List<ResponsableExtern> responsables;

}

