package com.csidigital.dao.entity;

import com.csidigital.shared.enumeration.TimeOffType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String description;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(name = "timeOffType")
    private TimeOffType timeOffType;
    private Integer alertNumberDays;
    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL)
    private List<TimeOff> timeOffList;

}
