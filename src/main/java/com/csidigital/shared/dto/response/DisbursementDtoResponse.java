package com.csidigital.shared.dto.response;

import com.csidigital.shared.enumeration.*;
import lombok.Data;

import java.util.Date;
@Data
public class DisbursementDtoResponse {
    private Long id;
    private String category;
    private Date date ;    private String description;
    private String treasuryType;
    private BillDtoResponse bill;
    private CategoryDisbursement categoryDisbursement ;


}
