package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.csidigital.shared.enumeration.MotifUnavailability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "period")
    private Integer  period;

    @Column (name="comment")
    private String comment;

    @Column (name="motifUnavailability")
    private MotifUnavailability motifUnavailability;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

}
