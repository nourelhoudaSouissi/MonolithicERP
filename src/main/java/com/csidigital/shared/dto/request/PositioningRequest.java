package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.PositioningStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PositioningRequest {
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
    private Long employeeNum;
    private Long requirementNum;
    private Long orderNum;
}
