package com.csidigital.shared.dto.request;

import com.csidigital.dao.entity.BankReconciliation;
import com.csidigital.shared.enumeration.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@RequiredArgsConstructor
public class DisbursementDtoRequest {

    private String category;
    private Date date ;
    private String description;
    private TreasuryType treasuryType = TreasuryType.En_COURS;
    private Long billId;
    private CategoryDisbursement categoryDisbursement ;
    private BankReconciliation bankReconciliation;

}
