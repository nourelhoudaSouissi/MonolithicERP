package com.csidigital.shared.dto.response;


import com.csidigital.shared.enumeration.TimeOffType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class LeaveTypeResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String description;
    private Integer duration;
    private TimeOffType timeOffType;
    private Integer alertNumberDays;

}
