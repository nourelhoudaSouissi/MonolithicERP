package com.csidigital.shared.dto.request;


import com.csidigital.dao.entity.BankReconciliation;
import com.csidigital.shared.enumeration.TreasuryType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class CollectionDtoRequest {
    private String category ;
    private Date date ;
    private String description ;
    private TreasuryType treasuryType = TreasuryType.En_COURS;
    private com.csidigital.shared.enumeration.CategoryCollection CategoryCollection ;
    private BankReconciliation bankReconciliation;

    private Long billClientId;


}
