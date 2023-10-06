package com.csidigital.dao.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    @OneToMany(mappedBy = "holiday", cascade = CascadeType.ALL)
    private List<RecoveryLeave> recoveryLeaveList;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "calendarId")
    private Calendar calendar;
}