package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.ExtraDutyType;
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
public class ExtraDuty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long workingHoursNumber ;
    private Double hourWage ;
    private Double performanceBonus ;
    @Enumerated(EnumType.STRING)
    private ExtraDutyType extraDutyType ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Benefit benefit;
}
