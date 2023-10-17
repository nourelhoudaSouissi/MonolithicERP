package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.Contract;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResourceRequest extends EmployeeRequest{


    private String socialSecurityNumber;
    private String bankAccountNumber;

    //private Double leaveBalanceRest;
   // private Double leaveBalance;
    private Long productivity;
    private String nationalIdentity;
    private LocalDate recruitmentDate;

    private Boolean isEmployee;

    private List<Contract> contractsList;
    private List<Long> projectNum ;

    private  Long PrjNum;
}
