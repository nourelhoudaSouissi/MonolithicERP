package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.PositioningStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PositioningResponse {
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
    private Long employeeId;
    private Long requirementId;
    private Long orderId;
}
