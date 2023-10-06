package com.csidigital.dao.entity;


import com.csidigital.shared.enumeration.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveryLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String name;

    private LocalDate date;

    private String comment;

    private Double duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "recoveryType")
    private RecoveryType recoveryType;
    private Integer  numberHours;
    @Enumerated(EnumType.STRING)
    @Column(name = "recoveryDay")
    private RecoveryDay recoveryDay;

    private  LocalDate inputDate;
    private LocalDate toTakeStartDate;
    private LocalDate toTakeEndDate;
    private Float periodToTake;
    private LocalDate weekendDate;

    @ManyToOne
    @JoinColumn(name = "holidayId")
    private Holiday holiday;

    @Column(name = "requestStatus")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    @Enumerated(EnumType.STRING)
    private RecoveryHours recoveryHours;


    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;


}
