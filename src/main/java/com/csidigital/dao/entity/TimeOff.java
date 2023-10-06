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

public class TimeOff {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    @Column(name = "description")
    private String description;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "timeOffPeriod")
    private Double timeOffPeriod;
    @Column(name = "comment")
    private String comment;
    @Column(name = "requestInputDate")
    private LocalDate requestInputDate;
    @Column(name = "requestStatus")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
    @Column(name = "validationDate")
    private LocalDate validationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "externalTimeOffType")
    private ExternalTimeOffType externalTimeOffType;


    @Enumerated(EnumType.STRING)
    @Column(name = "timeOffPeriodType")
    private TimeOffPeriodType timeOffPeriodType;
    @Enumerated(EnumType.STRING)
    @Column(name = "timeOfTimeType")
    private TimeOfTimeType timeOfTimeType;
    @Lob
    private String justificationDoc;

   /* @Transient
    private String justificationDocBase64;*/

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leaveTypeId")
    private LeaveType leaveType;


}
