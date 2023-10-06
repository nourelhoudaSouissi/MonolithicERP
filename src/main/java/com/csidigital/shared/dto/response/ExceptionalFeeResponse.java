package com.csidigital.shared.dto.response;


import com.csidigital.shared.enumeration.*;
import lombok.Data;

@Data
public class ExceptionalFeeResponse {
    private Long Id ;
    private FeeType feeType;
    private String shortDescription;
    private Long amount;
    private Currency currency;
    private String name;
    private Long contractId;

}
