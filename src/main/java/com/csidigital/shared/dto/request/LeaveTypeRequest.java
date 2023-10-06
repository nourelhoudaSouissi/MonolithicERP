package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.TimeOffType;
import lombok.Data;

@Data
public class LeaveTypeRequest {
    private String name;
    private String description;
    private Integer duration;
    private TimeOffType timeOffType;
    private Integer alertNumberDays;


}
