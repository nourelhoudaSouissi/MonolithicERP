package com.csidigital.shared.dto.request;

import com.csidigital.shared.enumeration.*;
import lombok.Data;

@Data
public class ExceptionalFeeRequest {
        private FeeType feeType;
        private String shortDescription;
        private Long amount;
        private Currency currency;
        private String name;
        private Long contractId;

}
