package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.WorkModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkArrangement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private WorkModel workModel ;
    private Double dailyWage;
    private Long workingDaysNumber ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Benefit benefit;
}
