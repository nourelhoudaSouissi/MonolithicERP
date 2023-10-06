package com.csidigital.dao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weekend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String reference ;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "startDay")
    private DayOfWeek startDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "endDay")
    private DayOfWeek endDay;



}
