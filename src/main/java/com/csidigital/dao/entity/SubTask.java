package com.csidigital.dao.entity;


import com.csidigital.shared.enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SubTask implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate EndDate;
    private Long estimation;
    private Long progression ;
    private TaskType taskType;
    private Priority priority;
    private Integer remaining ;
    private LocalDate realStartDate;
    private LocalDate realEndDate;
    private Date creationDate ;
    private LocalDate projectionDate;
    @Enumerated(EnumType.STRING)
    private TaskPhase taskPhase;
    @Column(unique = true)
    private String subTaskReference;

    /* @JsonIgnore
    @ManyToOne
    private Resource resource;*/

    @JsonIgnore
    @ManyToOne
    private Employee employee;


    @JsonIgnore
    @ManyToOne
    private Task task ;
}
