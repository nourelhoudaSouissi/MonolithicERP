package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.PositioningStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Positioning implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PositioningStatus positioningStatus;
    private Long soldDaysNumber;
    private Double dailySalePrice;
    private Integer occupancyRate;
    private Double averageDailyCost;
    private Double cost;
    private Double revenue;
    private Double margin;
    private Double profitability;
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;
}
