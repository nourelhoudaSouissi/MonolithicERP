package com.csidigital.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarMonth {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "ordre")
    private Integer ordre;
    @Column(name = "name")
    private  String name;
    @Column(name = "duration")
    private Integer duration;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "calendarId")
    private Calendar calendar;


}
