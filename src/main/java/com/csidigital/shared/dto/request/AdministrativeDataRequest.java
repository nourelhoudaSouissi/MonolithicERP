package com.csidigital.shared.dto.request;


import com.csidigital.shared.enumeration.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AdministrativeDataRequest {

    private ContractType contractType;
    private double currentSalary;
    private double expectedSalary;
    private AvailabilityEnum availability;
    private LocalDate availabilityDate;
    private Experience experience;
    private Long employeeNum;


}
